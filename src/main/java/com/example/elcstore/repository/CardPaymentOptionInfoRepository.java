package com.example.elcstore.repository;

import com.example.elcstore.domain.CardPaymentOptionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CardPaymentOptionInfoRepository extends JpaRepository<CardPaymentOptionInfo, UUID> {
}
