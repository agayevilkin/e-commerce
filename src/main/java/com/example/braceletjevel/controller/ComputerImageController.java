package com.example.braceletjevel.controller;

import com.example.braceletjevel.domain.ComputerImage;
import com.example.braceletjevel.domain.Image;
import com.example.braceletjevel.dto.request.ComputerImageRequestDto;
import com.example.braceletjevel.dto.response.ComputerImageResponseDto;
import com.example.braceletjevel.service.ComputerImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/computer-image")
@RequiredArgsConstructor
public class ComputerImageController {


    private final ComputerImageService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ComputerImageResponseDto create(@RequestBody ComputerImageRequestDto requestDto) {
        return service.createImage(requestDto);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(CREATED)
    public ComputerImageResponseDto create(@RequestParam("image") MultipartFile file) throws IOException {
        return service.createImageAsMultipartFile(file);
    }

    @PutMapping("/{id}")
    public ComputerImageResponseDto update(@PathVariable Long id, @RequestBody ComputerImageRequestDto requestDto) {
        return service.update(requestDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
