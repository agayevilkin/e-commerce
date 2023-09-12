package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CallTimeIntervalResponseDto {

    private UUID id;
    private String interval;
}
