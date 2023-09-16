package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Image;
import com.example.elcstore.domain.util.ImageUtil;
import com.example.elcstore.dto.ImageInfoDto;
import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ImageRepository;
import com.example.elcstore.service.ImageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.IMAGE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ModelMapper mapper;

    @SneakyThrows
    @Override
    public ImageResponseDto uploadImage(MultipartFile file) {
        Image image = createUploadImageObject(file.getBytes());
        //        responseDto.setImagePath(createImageUrl(image.getId()));
        return mapper.map(image, ImageResponseDto.class);
    }

    @Override
    public Image uploadImageWithByteArray(byte[] imageData) {
        return createUploadImageObject(imageData);
    }

    @SneakyThrows
    @Override
    public ImageResponseDto updateImage(MultipartFile file, UUID id) {
        Image image = createUpdateImageObject(id, file.getBytes());
        ImageResponseDto responseDto = mapper.map(image, ImageResponseDto.class);
        responseDto.setImagePath(createImageUrl(image.getId()));
        return responseDto;
    }

    @Override
    public Image updateImageWithByteArray(byte[] imageData, UUID id) {
        return createUpdateImageObject(id, imageData);
    }

    @Transactional
    public byte[] getImage(UUID id) {
        Image dbImage = imageRepository.findById(id).orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND.getMessage()));
        return ImageUtil.decompressImage(dbImage.getImageData());
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

    @SneakyThrows
    public byte[] resizeImage(byte[] image, int width, int height) {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(image));
        java.awt.Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);

        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedResizedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

        ByteArrayOutputStream currentImageByte = new ByteArrayOutputStream();
        ImageIO.write(bufferedResizedImage, "jpg", currentImageByte);

        return currentImageByte.toByteArray();
    }

    @SneakyThrows
    @Override
    public ImageInfoDto getImageWidthAndHeight(byte[] imageBytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
        BufferedImage originalImage = ImageIO.read(inputStream);

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        return ImageInfoDto.builder().width(width).height(height).build();
    }

    private Image createUploadImageObject(byte[] file) {
        Image image = new Image();
        image.setImageData(ImageUtil.compressImage(file));
        return imageRepository.save(image);
    }

    private Image createUpdateImageObject(UUID id, byte[] bytes) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException(IMAGE_NOT_FOUND.getMessage()));
        image.setImageData(ImageUtil.compressImage(bytes));
        return imageRepository.save(image);
    }
}
