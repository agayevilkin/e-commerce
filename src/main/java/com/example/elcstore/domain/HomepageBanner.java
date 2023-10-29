package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "homepage_banners")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomepageBanner {

    @Id
    @Column(name = "homepage_banner_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "image_id")
    private UUID imageId;

    @Column(name = "link")
    private String link;

    @Column(name = "order_num")
    private int orderNum;
}
