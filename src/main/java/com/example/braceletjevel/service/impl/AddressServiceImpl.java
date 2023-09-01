package com.example.braceletjevel.service.impl;

import com.example.braceletjevel.domain.Address;
import com.example.braceletjevel.domain.Customer;
import com.example.braceletjevel.dto.request.AddressRequestDto;
import com.example.braceletjevel.dto.response.AddressResponseDto;
import com.example.braceletjevel.exception.NotFoundException;
import com.example.braceletjevel.repository.AddressRepository;
import com.example.braceletjevel.repository.CustomerRepository;
import com.example.braceletjevel.service.AddressService;
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
