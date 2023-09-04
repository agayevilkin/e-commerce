package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ratings")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "star")
    private int star;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
