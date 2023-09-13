package com.example.elcstore.repository;

import com.example.elcstore.domain.TechnicalCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalCharacteristicRepository extends JpaRepository<TechnicalCharacteristic, UUID> {
}
