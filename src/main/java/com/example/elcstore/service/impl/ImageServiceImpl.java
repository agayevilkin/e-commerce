package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Image;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.exception.*;
import com.example.elcstore.repository.ImageRepository;
import com.example.elcstore.service.ImageService;
import com.example.elcstore.util.ImageUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import static com.example.elcstore.exception.ImageUploadException.FAILED_UPLOAD_IMAGE;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.IMAGE_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    @Override
    public ImageResponseDto uploadImage(MultipartFile file) {
        try {
            Image image = createUploadImageObject(file.getBytes());
            return mapper.map(image, ImageResponseDto.class);
        } catch (IOException e) {
            throw new ImageUploadException(e.getMessage());
        }
    }

    @Override
    public Image uploadImageWithByteArray(byte[] bytes) {
        return createUploadImageObject(bytes);
    }

    @Override
    public ImageResponseDto updateImage(MultipartFile file, UUID id) {
        try {
            Image image = createUpdateImageObject(id, file.getBytes());
            return mapper.map(image, ImageResponseDto.class);
        } catch (IOException e) {
            log.trace(FAILED_UPLOAD_IMAGE, e);
            throw new ImageUploadException(e.getMessage());
        }
    }

    @Override
    public Image updateImageWithByteArray(byte[] bytes, UUID id) {
        return createUpdateImageObject(id, bytes);
    }

    @Transactional
    public byte[] getImage(UUID id) {
        return ImageUtil.decompressImage(getImageById(id).getImageData());
    }

    @Override
    public void deleteImage(UUID id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
        }
    }

    @Override
    public String createImageUrl(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/")
                .path(String.valueOf(id))
                .toUriString();
    }

    public byte[] resizeImage(byte[] bytes, int width, int height) {
        try {
            if (isSupportedImageFormat(bytes)) {

                BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(bytes));

                java.awt.Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

                BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedResizedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

                ByteArrayOutputStream currentImageByte = new ByteArrayOutputStream();
                ImageIO.write(bufferedResizedImage, "jpg", currentImageByte);

                return currentImageByte.toByteArray();
            } else {
                log.trace("Unsupported image type!");
                throw new UnsupportedImageTypeException();
            }

        } catch (IOException | RuntimeException e) {
            //oktodo specify correctly exception type
            log.error("Image resizing has failed:", e);
            throw new ImageResizeException(e.getMessage());
        }
    }

    @Override
    public ImageInfoDto getImageWidthAndHeight(byte[] bytes) {
        if (isSupportedImageFormat(bytes)) {
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                BufferedImage originalImage = ImageIO.read(inputStream);
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();
                return ImageInfoDto.builder().width(width).height(height).build();

            } catch (IOException | RuntimeException e) {
                //oktodo specify correctly exception type
                log.error("Failed to retrieve image dimensions:", e);
                throw new ImageDimensionsRetrievalException(e.getMessage());
            }
        } else {
            throw new UnsupportedImageTypeException();
        }
    }

    private Image createUploadImageObject(byte[] bytes) {
        if (isSupportedImageFormat(bytes)) {
            Image image = new Image();
            image.setImageData(ImageUtil.compressImage(bytes));
            return imageRepository.save(image);
        } else {
            throw new UnsupportedImageTypeException();
        }
    }

    private Image createUpdateImageObject(UUID id, byte[] bytes) {
        if (isSupportedImageFormat(bytes)) {
            Image image = getImageById(id);
            image.setImageData(ImageUtil.compressImage(bytes));
            return imageRepository.save(image);
        } else throw new UnsupportedImageTypeException();
    }

    private boolean isSupportedImageFormat(byte[] file) {
        if (file.length >= 6) {
            if (file[0] == (byte) 0x47 &&
                    file[1] == (byte) 0x49 &&
                    file[2] == (byte) 0x46 &&
                    file[3] == (byte) 0x38 &&
                    (file[4] == (byte) 0x37 || file[4] == (byte) 0x39) &&
                    file[5] == (byte) 0x61) {
                return true; // GIF
            } else if (file[0] == (byte) 0xFF && file[1] == (byte) 0xD8) {
                return true; // JPEG
            } else if (file[0] == (byte) 0x89 && file[1] == (byte) 0x50) {
                return true; // PNG
            }
        }
        return false;
    }

    private Image getImageById(UUID id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND.getMessage()));
    }
}
