package com.example.elcstore.service.impl;

import com.example.elcstore.domain.ProductYouTube;
import com.example.elcstore.domain.enums.VideoStatus;
import com.example.elcstore.dto.request.ProductYouTubeRequestDto;
import com.example.elcstore.dto.response.ProductYouTubeResponseDto;
import com.example.elcstore.exception.InvalidProductVideoUrlException;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ProductYouTubeRepository;
import com.example.elcstore.service.ProductYouTubeService;
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
public class ProductYouTubeServiceImpl implements ProductYouTubeService {

    private final ProductYouTubeRepository productYouTubeRepository;
    private final ModelMapper mapper;
    private static final String VIDEO_THUMBNAIL_URL = "https://img.youtube.com/vi/%s/maxresdefault.jpg";


    @Override
    public void createProductYouTube(ProductYouTubeRequestDto requestDto) {
        String videoLink = requestDto.getVideoLink();
        ProductYouTube productYouTube = mapper.map(requestDto, ProductYouTube.class);
        productYouTube.setVideoThumbnail(String.format(VIDEO_THUMBNAIL_URL, extractVideoId(videoLink)));
        productYouTubeRepository.save(productYouTube);
    }

    @Override
    public ProductYouTubeResponseDto findById(UUID id) {
        return mapper.map(getProductYouTubeById(id), ProductYouTubeResponseDto.class);
    }

    @Override
    public void updateProductYouTube(UUID id, ProductYouTubeRequestDto requestDto) {
        String videoLink = requestDto.getVideoLink();
        ProductYouTube productYouTube = getProductYouTubeById(id);
        mapper.map(requestDto, productYouTube);
        productYouTube.setVideoThumbnail(String.format(VIDEO_THUMBNAIL_URL, extractVideoId(videoLink)));
        productYouTubeRepository.save(productYouTube);
    }

    @Override
    public List<ProductYouTubeResponseDto> getAllByVideoStatus(VideoStatus videoStatus) {
        return productYouTubeRepository.findAllByVideoStatus(videoStatus)
                .stream()
                .map((productYouTube -> mapper.map(productYouTube, ProductYouTubeResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductYouTubeResponseDto> getAllLatestUploaded() {
        return productYouTubeRepository.findAllByIsNewTrue()
                .stream()
                .map((productYouTube -> mapper.map(productYouTube, ProductYouTubeResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProductYouTube(UUID id) {
        if (checkById(id)) {
            productYouTubeRepository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return productYouTubeRepository.existsById(id);
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

    private ProductYouTube getProductYouTubeById(UUID id) {
        return productYouTubeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PRODUCT_VIDEO_NOT_FOUND));
    }
}
