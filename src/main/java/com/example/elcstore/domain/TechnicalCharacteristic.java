package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "technical_characteristics")
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalCharacteristic {

    @Id
    @Column(name = "tc_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "characteristic_name")
    private String characteristicName;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "technical_characteristic_title_id")
    private TechnicalCharacteristicTitle title;
}
