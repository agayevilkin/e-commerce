package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "attributes")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attribute {

    @Id
    @Column(name = "attribute_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "attribute_definition_id")
    private AttributeDefinition attributeDefinition;
}
