package com.example.elcstore.repository;

import com.example.elcstore.domain.Store;
import com.example.elcstore.domain.enums.StoreLocationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {

    int countByStoreLocationStatus(StoreLocationStatus storeLocationStatus);

    List<Store> findAllByStoreLocationStatus(StoreLocationStatus storeLocationStatus);
}
