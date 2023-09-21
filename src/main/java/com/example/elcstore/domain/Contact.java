package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "contact")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "message")
    private String message;

    @Column(name = "call_time_interval")
    private String callTimeInterval;
}
