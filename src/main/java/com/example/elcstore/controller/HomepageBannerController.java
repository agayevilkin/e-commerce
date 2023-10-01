package com.example.elcstore.controller;

import com.example.elcstore.dto.request.HomepageBannerCreateRequestDto;
import com.example.elcstore.dto.request.HomepageBannerUpdateRequestDto;
import com.example.elcstore.dto.response.HomepageBannerResponseDto;
import com.example.elcstore.service.HomepageBannerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/homepage-banner")
@RequiredArgsConstructor
public class HomepageBannerController {

    private final HomepageBannerService service;

    @PostMapping
//    @PostMapping(consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody HomepageBannerCreateRequestDto requestDto) {
        service.createHomepageBanner(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public HomepageBannerResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllHomepageBanners")
    public List<HomepageBannerResponseDto> getAllHomepageBanners() {
        return service.getAllHomepageBanners();
    }

    @PutMapping("/{id}")
//    @PutMapping(value = {"/{id}"}, consumes = {"multipart/form-data"})
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody HomepageBannerUpdateRequestDto requestDto) {
        service.updateHomepageBanner(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteHomepageBanner(id);
    }


}
