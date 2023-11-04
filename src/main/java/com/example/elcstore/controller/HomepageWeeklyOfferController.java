package com.example.elcstore.controller;

import com.example.elcstore.dto.request.HomepageWeeklyOfferRequestDto;
import com.example.elcstore.dto.response.HomepageWeeklyOfferResponseDto;
import com.example.elcstore.service.HomepageWeeklyOfferService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/homepage-weekly-offers")
@RequiredArgsConstructor
public class HomepageWeeklyOfferController {

    private final HomepageWeeklyOfferService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create")
    public void create(@Valid @RequestBody HomepageWeeklyOfferRequestDto requestDto) {
        service.createHomepageWeeklyOffer(requestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public HomepageWeeklyOfferResponseDto findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "getAllHomepageWeeklyOffers")
    public List<HomepageWeeklyOfferResponseDto> getAllHomepageWeeklyOffers() {
        return service.getAllHomepageWeeklyOffers();
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void update(@PathVariable UUID id, @Valid @RequestBody HomepageWeeklyOfferRequestDto requestDto) {
        service.updateHomepageWeeklyOffer(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete")
    public void delete(@PathVariable UUID id) {
        service.deleteHomepageWeeklyOffer(id);
    }

}
