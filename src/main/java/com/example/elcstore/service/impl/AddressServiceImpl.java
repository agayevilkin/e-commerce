package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Address;
import com.example.elcstore.domain.Customer;
import com.example.elcstore.dto.request.AddressRequestDto;
import com.example.elcstore.dto.response.AddressResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.AddressRepository;
import com.example.elcstore.repository.CustomerRepository;
import com.example.elcstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    @Override
    public void createAddress(AddressRequestDto requestDto) {
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found!"));
        Address address = mapper.map(requestDto, Address.class);
        address.setCustomer(customer);
        repository.save(address);
    }

    @Override
    public AddressResponseDto findById(UUID id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public void updateAddress(UUID id, AddressRequestDto requestDto) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found!"));
        Customer customer = customerRepository.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found!"));
        mapper.map(requestDto, address);
        address.setCustomer(customer);
        repository.save(address);
    }

    @Override
    public void deleteAddress(UUID id) {
        if (checkById(id)) {
            repository.deleteById(id);
        }
    }

    private boolean checkById(UUID id) {
        return repository.existsById(id);
    }

}
