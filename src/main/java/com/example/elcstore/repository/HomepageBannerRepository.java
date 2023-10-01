package com.example.elcstore.repository;

import com.example.elcstore.domain.HomepageBanner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HomepageBannerRepository extends JpaRepository<HomepageBanner, UUID> {
}
