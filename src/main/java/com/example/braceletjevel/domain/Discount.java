package com.example.braceletjevel.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "discount")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "computer_product_id")
    private ComputerProduct product;

    @Column(name = "current_price")
    private String currentPrice;

    @Column(name = "discount_percentage")
    private String discountPercentage;
}
