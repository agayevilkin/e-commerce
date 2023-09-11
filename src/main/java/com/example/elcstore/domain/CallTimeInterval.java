package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "call_time_interval")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallTimeInterval {

    @Id
    @Column(name = "call_time_interval_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "interval")
    private String interval;

    @ManyToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;
}
