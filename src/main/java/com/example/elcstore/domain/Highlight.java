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
     * The `Highlight` class is designed to emphasize a specific {@code field} within the `{@link Product}` entity,
     * serving to draw attention to its significance and role in the function.
     * This class is especially useful for making the purpose of the function more prominent and clear.
     * Usage Example:
     * <blockquote><pre>
     * 1. '{@link Product}' represents a phone.
     * 2. RAM is a distinctive feature of phones.
     * 3. "value" is set to "8".
     * </pre></blockquote>
     * <p>
     * Highlight Definition:
     * <blockquote><pre>
     * Note: A single '{@link HighlightDefinition}' can be used across multiple
     * '{@link Highlight}(s)'.
     *
     * 1. Name: "RAM"
     * 2. Type: "GB"
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
