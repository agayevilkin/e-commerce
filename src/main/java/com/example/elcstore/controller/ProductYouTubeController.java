package com.example.elcstore.controller;

import com.example.elcstore.domain.enums.VideoStatus;
import com.example.elcstore.dto.request.ProductYouTubeRequestDto;
import com.example.elcstore.dto.response.ProductYouTubeResponseDto;
import com.example.elcstore.service.ProductYouTubeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product-youtube")
@Validated
public class ProductYouTubeController {

    private final ProductYouTubeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody ProductYouTubeRequestDto requestDto) {
        service.createProductYouTube(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ProductYouTubeResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody ProductYouTubeRequestDto requestDto) {
        service.updateProductYouTube(id, requestDto);
    }

    @GetMapping("/video-status/all")
    @Operation(summary = "getAllByVideoStatus")
    public List<ProductYouTubeResponseDto> getAllByVideoStatus(@NotNull @RequestParam VideoStatus videoStatus) {
        return service.getAllByVideoStatus(videoStatus);
    }

    @GetMapping("/latest-uploads/all")
    @Operation(summary = "getAllLatestUploaded")
    public List<ProductYouTubeResponseDto> getAllLatestUploaded() {
        return service.getAllLatestUploaded();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteProductYouTube(id);
    }
}
