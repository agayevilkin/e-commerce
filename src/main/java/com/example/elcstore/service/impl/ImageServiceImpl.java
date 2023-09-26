package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Image;
import com.example.elcstore.domain.util.ImageUtil;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.exception.ImageProcessingException;
import com.example.elcstore.exception.ImageUploadException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.exception.UnsupportedImageTypeException;
import com.example.elcstore.repository.ImageRepository;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

import static com.example.elcstore.exception.ImageProcessingException.FAILED_GET_IMAGE_DIMENSIONS;
import static com.example.elcstore.exception.ImageProcessingException.FAILED_RESIZE_IMAGE;
import static com.example.elcstore.exception.UnsupportedImageTypeException.UNSUPPORTED_IMAGE_TYPE;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.IMAGE_NOT_FOUND;

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
            }
            throw new UnsupportedImageTypeException(UNSUPPORTED_IMAGE_TYPE);
        } catch (IOException | NullPointerException e) {
            //todo specify correctly exception type
            throw new ImageProcessingException(FAILED_RESIZE_IMAGE);
        }
    }

    @Override
    public ImageInfoDto getImageWidthAndHeight(byte[] bytes) {
        try {
            if (isSupportedImageFormat(bytes)) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                BufferedImage originalImage = ImageIO.read(inputStream);
                int width = originalImage.getWidth();
                int height = originalImage.getHeight();
                return ImageInfoDto.builder().width(width).height(height).build();
            } else throw new UnsupportedImageTypeException(UNSUPPORTED_IMAGE_TYPE);

        } catch (IOException | NullPointerException e) {
            //todo specify correctly exception type
            throw new ImageProcessingException(FAILED_GET_IMAGE_DIMENSIONS + e.getMessage());
        }
    }

    private Image createUploadImageObject(byte[] bytes) {
        if (isSupportedImageFormat(bytes)) {
            Image image = new Image();
            image.setImageData(ImageUtil.compressImage(bytes));
            return imageRepository.save(image);
        } else throw new UnsupportedImageTypeException(UNSUPPORTED_IMAGE_TYPE);

    }

    private Image createUpdateImageObject(UUID id, byte[] bytes) {
        if (isSupportedImageFormat(bytes)) {
            Image image = getImageById(id);
            image.setImageData(ImageUtil.compressImage(bytes));
            return imageRepository.save(image);
        } else throw new UnsupportedImageTypeException(UNSUPPORTED_IMAGE_TYPE);

    }

    private boolean isSupportedImageFormat(byte[] file) {
        if (file.length >= 6) {
            if (file[0] == (byte) 0x47 && // 'G'
                    file[1] == (byte) 0x49 && // 'I'
                    file[2] == (byte) 0x46 && // 'F'
                    file[3] == (byte) 0x38 && // '8'
                    (file[4] == (byte) 0x37 || file[4] == (byte) 0x39) && // '7' or '9'
                    file[5] == (byte) 0x61) { // 'a'
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
