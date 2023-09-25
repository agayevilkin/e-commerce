package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.VideoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_videos")
@NoArgsConstructor
@AllArgsConstructor
public class ProductVideo {

    @Id
    @Column(name = "product_video_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "video_thumbnail")
    private String videoThumbnail;

    @Column(name = "video_status")
    @Enumerated(EnumType.STRING)
    private VideoStatus videoStatus;

    @Column(name = "is_new")
    private Boolean isNew;
}
