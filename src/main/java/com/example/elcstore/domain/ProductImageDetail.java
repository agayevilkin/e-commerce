package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_image_detail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDetail {

    @Id
    @Column(name = "product_image_detail_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
