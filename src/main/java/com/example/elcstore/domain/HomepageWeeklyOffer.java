package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "homepage_weekly_offer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomepageWeeklyOffer {

    @Id
    @Column(name = "homepage_weekly_offer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "deadline")
    private LocalDateTime deadline;

    // TODO: 10/26/2023 add this and delete productoption field
    @Column(name = "product_option_id")
    private UUID productOptionId;
}
