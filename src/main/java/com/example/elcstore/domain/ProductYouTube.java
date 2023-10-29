package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.VideoTypeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_youtube_videos")
@NoArgsConstructor
@AllArgsConstructor
public class ProductYouTube {

    @Id
    @Column(name = "product_youtube_video_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "video_link")
    private String videoLink;

    @Column(name = "video_thumbnail")
    private String videoThumbnail;

    @Column(name = "video_type_status")
    @Enumerated(EnumType.STRING)
    private VideoTypeStatus videoTypeStatus;

    @Column(name = "is_new")
    private Boolean isNew;
}
