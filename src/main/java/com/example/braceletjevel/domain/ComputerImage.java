package com.example.braceletjevel.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "computer_images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String imagePath;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "computer_product_id")
    private ComputerProduct product;
}
