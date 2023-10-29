package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @Column(name = "order_detail_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "product_option_id")
    private UUID productOptionId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
