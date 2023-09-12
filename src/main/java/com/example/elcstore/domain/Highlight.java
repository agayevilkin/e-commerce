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

    /**
     * The `Highlight` class is used to emphasize a specific {@code field} of the `{@link Product}` entity.
     * It is intended to make the purpose of the function stand out.
     * </pre></blockquote><p>
     * Example:
     * <blockquote><pre>
     * 1. '{@link Product}' is a Phone
     * 2. RAM is specific feature of Phone
     * 3. "value" = "8"
     * </pre></blockquote>
     * HighlightDefinition:
     * <blockquote><pre>
     * Note: 'one{@link HighlightDefinition}' can be the same
     * for different '{@link Highlight}(s)'
     *
     * 1. name = "RAM"
     * 2. type = "GB"
     * </pre></blockquote>
     */

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
