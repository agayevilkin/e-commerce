package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "highlight")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Highlight {

    @Id
    @Column(name = "highlight_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "highlight_definition_id")
    private HighlightDefinition highlightDefinition;
}
