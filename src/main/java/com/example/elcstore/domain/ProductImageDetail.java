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

    @Column(name = "image_id")
    private UUID imageId;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    private ProductOption productOption;

    @Column(name = "order_num")
    private int orderNum;
}
