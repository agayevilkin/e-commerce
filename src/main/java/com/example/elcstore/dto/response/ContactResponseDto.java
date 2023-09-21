package com.example.elcstore.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ContactResponseDto {

    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String company;
    private String message;
    private String callTimeInterval;
}
