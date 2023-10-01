package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "campaigns")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @Column(name = "campaign_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "starting_date")
    private LocalDateTime startingDate;

    @Column(name = "ending_date")
    private LocalDateTime endingDate;

    @Column(name = "image_id")
    private UUID imageId;

    @Column(name = "thumbnail_image_id")
    private UUID thumbnailImageId;

    // TODO: 9/24/2023 can be add Category or (other function for get all product similar to campaigns)
}
