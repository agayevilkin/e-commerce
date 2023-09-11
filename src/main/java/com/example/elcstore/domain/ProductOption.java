package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.StockStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_options")
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {

    @Id
    @Column(name = "product_option_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "stock_status")
    private StockStatus stockStatus;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "product_option_event",
            joinColumns = {@JoinColumn(name = "product_option_id", referencedColumnName = "product_option_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id", referencedColumnName = "event_id")})
    private List<Event> campaigns;

    @OneToMany(mappedBy = "productOption", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductImageDetail> images;

    @Column(name = "preview_image")
    private String previewImage;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
