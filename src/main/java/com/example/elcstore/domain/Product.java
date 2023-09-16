package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

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
    private String name; // iPhone 14 Pro Max

    @Column(name = "price")
    private double price;

    @OneToOne(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_highlight",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "highlight_id", referencedColumnName = "highlight_id")})
    private List<Highlight> highlight;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_technical_characteristic",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "tc_id", referencedColumnName = "tc_id")})
    private List<TechnicalCharacteristic> technicalCharacteristic;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductComment> productComment;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductOption> productOptions;
}
