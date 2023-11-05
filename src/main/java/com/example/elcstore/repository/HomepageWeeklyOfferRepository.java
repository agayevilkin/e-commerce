package com.example.elcstore.repository;

import com.example.elcstore.domain.HomepageWeeklyOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface HomepageWeeklyOfferRepository extends JpaRepository<HomepageWeeklyOffer, UUID> {

    List<HomepageWeeklyOffer> findAllByDeadlineAfter(LocalDateTime currentTime);
    List<HomepageWeeklyOffer> findAllByProductOptionId(UUID productOptionId);
    boolean existsByProductOptionId(UUID id);
}
