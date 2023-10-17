package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "campaign_category",
            joinColumns = {@JoinColumn(name = "campaign_id", referencedColumnName = "campaign_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "category_id")})
    private List<Category> categories;

    // TODO: 9/24/2023 can be add Category or (other function for get all product similar to campaigns)
}
