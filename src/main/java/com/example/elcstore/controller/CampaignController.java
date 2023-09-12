package com.example.elcstore.controller;

import com.example.elcstore.dto.request.CampaignRequestDto;
import com.example.elcstore.dto.response.CampaignResponseDto;
import com.example.elcstore.dto.response.ImageResponseDto;
import com.example.elcstore.service.CampaignService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody CampaignRequestDto requestDto) {
        service.createCampaign(requestDto);
    }
    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public CampaignResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody CampaignRequestDto requestDto) {
        service.updateCampaign(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteCampaign(id);
    }
}
