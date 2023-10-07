package com.example.elcstore.controller;

import com.example.elcstore.dto.request.CardOptionInfoCreateDto;
import com.example.elcstore.dto.request.CardOptionInfoUpdateDto;
import com.example.elcstore.dto.response.CardOptionInfoResponseDto;
import com.example.elcstore.service.CardPaymentOptionInfoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card-payment-option-info")
@RequiredArgsConstructor
public class CardPaymentOptionInfoController {

    private final CardPaymentOptionInfoService service;

//    @PostMapping
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create") //@ModelAttribute
    public void create(@Valid @ModelAttribute CardOptionInfoCreateDto requestDto) {
        service.createCardPaymentOptionInfo(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public CardOptionInfoResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllCardPaymentOptionInfo")
    public List<CardOptionInfoResponseDto> getAllCardPaymentOptionInfo() {
        return service.getAllCardPaymentOptionInfo();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody CardOptionInfoUpdateDto requestDto) {
        service.updateCardPaymentOptionInfo(id, requestDto);
    }

    @PutMapping(value = "/card-slider-image/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "updateCardPaymentOptionInfoImage")
    public void updateCardPaymentOptionInfoImage(@PathVariable UUID id, @RequestParam("image") MultipartFile file) {
        service.updateCardPaymentOptionInfoImage(id, file);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteCardPaymentOptionInfo(id);
    }
}
