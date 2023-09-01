package com.example.braceletjevel.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "attribute_definitions")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDefinition {
    @Id
    @Column(name = "attribute_definition_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;
}
