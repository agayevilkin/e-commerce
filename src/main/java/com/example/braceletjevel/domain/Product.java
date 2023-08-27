package com.example.braceletjevel.domain;

import com.example.braceletjevel.domain.enums.StockStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private String price;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "color_id")
    private Color color;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Discount discount;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Rating> rating;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductComment> productComment;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_attribute",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id", referencedColumnName = "id")})
    private List<Attribute> attribute;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;
}
