package com.example.braceletjevel.controller;

import com.example.braceletjevel.dto.request.ImageRequestDto;
import com.example.braceletjevel.dto.response.ImageResponseDto;
import com.example.braceletjevel.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {


    private final ImageService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> getImageByName(@PathVariable("id") Long id) {
        byte[] image = service.getImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .body(image);
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @ResponseStatus(CREATED)
    public ImageResponseDto create(@ModelAttribute ImageRequestDto requestDto) {
        return service.createImage(requestDto);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageResponseDto update(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        return service.update(file, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteImage(@PathVariable Long id) {
        service.delete(id);
    }
}
