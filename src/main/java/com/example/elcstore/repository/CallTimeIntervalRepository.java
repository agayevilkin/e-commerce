package com.example.elcstore.repository;

import com.example.elcstore.domain.CallTimeInterval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CallTimeIntervalRepository extends JpaRepository<CallTimeInterval, UUID> {
}
