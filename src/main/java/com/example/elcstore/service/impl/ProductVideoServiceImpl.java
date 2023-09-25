package com.example.elcstore.service.impl;

import com.example.elcstore.domain.ProductVideo;
import com.example.elcstore.domain.enums.VideoStatus;
import com.example.elcstore.dto.request.ProductVideoRequestDto;
import com.example.elcstore.dto.response.ProductVideoResponseDto;
import com.example.elcstore.exception.InvalidProductVideoUrlException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ProductVideoRepository;
import com.example.elcstore.service.ProductVideoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.InvalidProductVideoUrlException.INVALID_URL;
import static com.example.elcstore.exception.NotFoundException.PRODUCT_VIDEO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductVideoServiceImpl implements ProductVideoService {

    private final ProductVideoRepository productVideoRepository;
    private final ModelMapper mapper;
    private static final String VIDEO_THUMBNAIL_URL = "https://img.youtube.com/vi/%s/maxresdefault.jpg";


    @Override
    public void createProductVideo(ProductVideoRequestDto requestDto) {
        String videoLink = requestDto.getVideoLink();
        ProductVideo productVideo = mapper.map(requestDto, ProductVideo.class);
        productVideo.setVideoThumbnail(String.format(VIDEO_THUMBNAIL_URL, extractVideoId(videoLink)));
        productVideoRepository.save(productVideo);
    }

    @Override
    public ProductVideoResponseDto findById(UUID id) {
        ProductVideo productVideo = productVideoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_VIDEO_NOT_FOUND));
        return mapper.map(productVideo, ProductVideoResponseDto.class);
    }

    @Override
    public void updateProductVideo(UUID id, ProductVideoRequestDto requestDto) {
        String videoLink = requestDto.getVideoLink();
        ProductVideo productVideo = productVideoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_VIDEO_NOT_FOUND));
        mapper.map(requestDto, productVideo);
        productVideo.setVideoThumbnail(String.format(VIDEO_THUMBNAIL_URL, extractVideoId(videoLink)));
        productVideoRepository.save(productVideo);
    }

    @Override
    public List<ProductVideoResponseDto> getAllByVideoStatus(VideoStatus videoStatus) {
        return productVideoRepository.findAllByVideoStatus(videoStatus)
                .stream()
                .map((productVideo -> mapper.map(productVideo, ProductVideoResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductVideoResponseDto> getAllLatestUploaded() {
        return productVideoRepository.findAllByIsNewTrue()
                .stream()
                .map((productVideo -> mapper.map(productVideo, ProductVideoResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductVideo(UUID id) {
        if (checkById(id)) {
            productVideoRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return productVideoRepository.existsById(id);
    }

    private boolean isYouTubeVideoURL(String url) {
        String youtubePattern = "(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)[\\w-]+.*";
        Pattern pattern = Pattern.compile(youtubePattern);
        Matcher matcher = pattern.matcher(url);
        return matcher.matches();
    }

    public String extractVideoId(String url) {
        if (isYouTubeVideoURL(url)) {
            String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%\u200C\u200B2F|%2Fv%2F)[^#\\&\\?\\n]*";

            Pattern compiledPattern = Pattern.compile(pattern);
            Matcher matcher = compiledPattern.matcher(url);

            if (matcher.find()) {
                return matcher.group();
            }
            return null;
        } else {
            throw new InvalidProductVideoUrlException(INVALID_URL);
        }
    }
}
