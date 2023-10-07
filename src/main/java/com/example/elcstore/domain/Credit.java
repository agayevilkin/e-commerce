package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "credits")
@NoArgsConstructor
@AllArgsConstructor
public class Credit {

    @Id
    @Column(name = "credit_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "monthly_payment_amount")
    private Double monthlyPaymentAmount;

    @Column(name = "credit_period")
    private Integer creditPeriod;

    @Column(name = "current_price")
    private Double currentPrice;

    @Column(name = "initial_payment_amount")
    private Double initialPaymentAmount;

    @Column(name = "interest")
    private Double interest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
