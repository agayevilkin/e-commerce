package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "highlight_definitions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HighlightDefinition {

    @Id
    @Column(name = "highlight_definition_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

}
