package com.example.elcstore.repository;

import com.example.elcstore.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

    boolean existsByCategoriesId(UUID id);
}
