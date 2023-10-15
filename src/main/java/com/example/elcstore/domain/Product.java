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
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private boolean status;

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Discount discount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "category_id")})
    private List<Category> categories;

    // TODO: 9/18/2023 EAGER for now but will change to LAZY and use @Transactional or other methods
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highlight_id")
    private Highlight highlight;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_event",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "event_id")})
    private List<Event> events;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_technical_characteristic",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "tc_id", referencedColumnName = "tc_id")})
    private List<TechnicalCharacteristic> technicalCharacteristic;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductComment> productComment;

    // TODO: 10/15/2023 make LAZY and test related apis
    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductOption> productOptions;
}
