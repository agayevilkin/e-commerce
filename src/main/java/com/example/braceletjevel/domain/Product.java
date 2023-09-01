package com.example.braceletjevel.domain;

import com.example.braceletjevel.domain.enums.StockStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Audit {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToOne(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductComment> productComment;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Rating> rating;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_attribute",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id", referencedColumnName = "attribute_id")})
    private List<Attribute> attribute;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Image> images;
}
