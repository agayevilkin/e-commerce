package com.example.braceletjevel.service;

import com.example.braceletjevel.dto.request.ComputerImageRequestDto;
import com.example.braceletjevel.dto.response.ComputerImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ComputerImageService {
    ComputerImageResponseDto createImage(ComputerImageRequestDto requestDto);

    void delete(Long id);

    ComputerImageResponseDto createImageAsMultipartFile(MultipartFile file) throws IOException;

    ComputerImageResponseDto update(ComputerImageRequestDto requestDto, Long id);
}
