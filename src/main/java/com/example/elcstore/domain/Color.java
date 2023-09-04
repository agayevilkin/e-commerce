package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "colors")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @Column(name = "color_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "color_code")
    private String colorCode;
}
