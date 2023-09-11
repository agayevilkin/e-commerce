package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "description")
    private String description;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "ending_date")
    private LocalDate endingDate;

    @Column(name = "image_id")
    private UUID imageId;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "preview_image_path")
    private String previewImagePath;
}
