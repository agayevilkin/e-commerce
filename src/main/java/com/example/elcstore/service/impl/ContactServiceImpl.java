package com.example.elcstore.service.impl;

import com.example.elcstore.domain.CallTimeInterval;
import com.example.elcstore.domain.Contact;
import com.example.elcstore.dto.request.ContactRequestDto;
import com.example.elcstore.dto.response.ContactResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.CallTimeIntervalRepository;
import com.example.elcstore.repository.ContactRepository;
import com.example.elcstore.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CALL_TIME_INTERVAL_NOT_FOUND;
import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CONTACT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final CallTimeIntervalRepository callTimeIntervalRepository;
    private final ModelMapper mapper;

    @Override
    public void createContact(ContactRequestDto requestDto) {
        CallTimeInterval callTimeInterval = callTimeIntervalRepository.findById(requestDto.getCallTimeIntervalId())
                .orElseThrow(() -> new NotFoundException(CALL_TIME_INTERVAL_NOT_FOUND.getMessage()));
        Contact contact = mapper.map(requestDto, Contact.class);
        contact.setCallTimeInterval(callTimeInterval);
        contactRepository.save(contact);
    }

    @Override
    public ContactResponseDto findById(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTACT_NOT_FOUND.getMessage()));
        return mapper.map(contact, ContactResponseDto.class);
    }

    @Override
    public void updateContact(UUID id, ContactRequestDto requestDto) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTACT_NOT_FOUND.getMessage()));
        CallTimeInterval callTimeInterval = callTimeIntervalRepository.findById(requestDto.getCallTimeIntervalId())
                .orElseThrow(() -> new NotFoundException(CALL_TIME_INTERVAL_NOT_FOUND.getMessage()));
        mapper.map(requestDto, callTimeInterval);
        contact.setCallTimeInterval(callTimeInterval);
        contactRepository.save(contact);
    }

    @Override
    public void deleteContact(UUID id) {
        if (existsById(id)) {
            contactRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return contactRepository.existsById(id);
    }
}
