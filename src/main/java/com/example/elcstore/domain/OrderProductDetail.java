package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_product_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductDetail {

    @Id
    @Column(name = "order_product_details_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    //todo fix product logic  (maybe this can be. product Id and Product Option Id)
    private Product product;

    @Column(name = "product_piece")
    private int productPiece;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
