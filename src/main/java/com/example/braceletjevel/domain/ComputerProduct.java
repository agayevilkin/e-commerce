package com.example.braceletjevel.domain;

import com.example.braceletjevel.domain.enums.Categories;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "computer_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComputerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String detailName;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "categories")
    @Enumerated(EnumType.STRING)
    private Categories categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    @OneToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<ComputerImage> images;
}
