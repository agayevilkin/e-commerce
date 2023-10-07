package com.example.elcstore.repository;

import com.example.elcstore.domain.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit, UUID> {

    List<Credit> findAllByProduct_Id(UUID productId);
}
