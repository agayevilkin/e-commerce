package com.example.elcstore.repository;

import com.example.elcstore.domain.HomepageWeeklyOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomepageWeeklyOfferRepository extends JpaRepository<HomepageWeeklyOffer, UUID> {
}
