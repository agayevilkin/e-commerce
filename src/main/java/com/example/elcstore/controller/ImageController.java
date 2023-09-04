package com.example.elcstore.controller;

import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;

    @GetMapping("/{id}")
    @Operation(summary = "getImageByName")
    public ResponseEntity<?> getImageByName(@PathVariable("id") UUID id) {
        byte[] image = service.getImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpg"))
                .body(image);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(CREATED)
    @Operation(summary = "upload")
    public ImageResponseDto upload(@RequestParam("image") MultipartFile file) {
        return service.uploadImage(file);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "update")
    public ImageResponseDto update(@PathVariable UUID id, @RequestParam("image") MultipartFile file) {
        return service.updateImage(file, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteImage(id);
    }
}
