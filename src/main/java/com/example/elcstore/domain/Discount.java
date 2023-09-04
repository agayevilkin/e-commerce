package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "discounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @Column(name = "discount_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "current_price")
    private double currentPrice;

    @Column(name = "discount_percentage")
    private int discountPercentage;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
