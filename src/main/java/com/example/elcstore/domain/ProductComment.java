package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductComment extends Audit {

    @Id
    @Column(name = "product_comment_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "star")
    private int star;

    @Column(name = "content", length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
