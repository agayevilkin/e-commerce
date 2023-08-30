package com.example.braceletjevel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(name = "content", length = 1000)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;
}
