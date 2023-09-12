package com.example.elcstore.service;

import com.example.elcstore.dto.request.ContactRequestDto;
import com.example.elcstore.dto.response.ContactResponseDto;

import java.util.UUID;

public interface ContactService {
    void createContact(ContactRequestDto requestDto);

    ContactResponseDto findById(UUID id);

    void updateContact(UUID id, ContactRequestDto requestDto);

    void deleteContact(UUID id);
}
