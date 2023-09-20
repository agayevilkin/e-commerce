package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.CommentStatus;
import com.example.elcstore.domain.enums.StockStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

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

    @Column(name = "author_full_name")
    private String authorFullName;

    @Column(name = "star")
    private int star;

    @Column(name = "content", length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "comment_status")
    private CommentStatus commentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
