package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "card_payment_option_info")
@NoArgsConstructor
@AllArgsConstructor
public class CardPaymentOptionInfo {

    @Id
    @Column(name = "card_payment_option_info_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "image_id")
    private UUID imageId;
}
