package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Contact;
import com.example.elcstore.dto.request.ContactRequestDto;
import com.example.elcstore.dto.response.ContactResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.ContactRepository;
import com.example.elcstore.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.messages.NotFoundExceptionMessages.CONTACT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    private final ModelMapper mapper;

    @Override
    public void createContact(ContactRequestDto requestDto) {
        contactRepository.save(mapper.map(requestDto, Contact.class));
    }

    @Override
    public ContactResponseDto findById(UUID id) {
        return mapper.map(getContactById(id), ContactResponseDto.class);
    }

    @Override
    public List<ContactResponseDto> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map((contact -> mapper.map(contact, ContactResponseDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateContact(UUID id, ContactRequestDto requestDto) {
        Contact contact = getContactById(id);
        mapper.map(requestDto, contact);
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

    private Contact getContactById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(CONTACT_NOT_FOUND.getMessage()));
    }
}
