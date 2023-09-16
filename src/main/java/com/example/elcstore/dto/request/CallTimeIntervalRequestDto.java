package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class CallTimeIntervalRequestDto {

    @NotEmpty
    private String interval;
}
