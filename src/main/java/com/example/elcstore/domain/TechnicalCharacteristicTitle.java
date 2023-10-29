package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "technical_characteristic_titles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCharacteristicTitle {

    @Id
    @Column(name = "technical_characteristic_title_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

}
