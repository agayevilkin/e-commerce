package com.example.elcstore.repository;

import com.example.elcstore.domain.TechnicalCharacteristicTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicalCharacteristicTitleRepository extends JpaRepository<TechnicalCharacteristicTitle, UUID> {
}
