package com.example.elcstore.service.impl;

import com.example.elcstore.domain.Store;
import com.example.elcstore.domain.enums.StoreLocationStatus;
import com.example.elcstore.dto.request.StoreRequestDto;
import com.example.elcstore.dto.response.StoreResponseDto;
import com.example.elcstore.exception.NotFoundException;
import com.example.elcstore.repository.StoreRepository;
import com.example.elcstore.service.StoreService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.elcstore.exception.NotFoundException.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper mapper;

    @Override
    public void createStore(StoreRequestDto requestDto) {
        storeRepository.save(mapper.map(requestDto, Store.class));
    }

    @Override
    public StoreResponseDto findStoreById(UUID id) {
        return mapper.map(getStoreById(id), StoreResponseDto.class);
    }

    @Override
    public List<StoreResponseDto> getAllStoresByStoreLocationStatus(@NotNull StoreLocationStatus storeLocationStatus) {
        return storeRepository.findAllByStoreLocationStatus(storeLocationStatus)
                .stream()
                .map((store) -> mapper.map(store, StoreResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateStore(UUID id, StoreRequestDto requestDto) {
        Store store = getStoreById(id);
        mapper.map(requestDto, store);
        storeRepository.save(store);
    }

    @Override
    public void deleteStore(UUID id) {
        if (existsById(id)) {
            storeRepository.deleteById(id);
        }
    }

    private boolean existsById(UUID id) {
        return storeRepository.existsById(id);
    }


    @Override
    public int getCountOfStoresByLocationStatus(StoreLocationStatus storeLocationStatus) {
        return storeRepository.countByStoreLocationStatus(storeLocationStatus);
    }

    private Store getStoreById(UUID id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(STORE_NOT_FOUND));
    }
}
