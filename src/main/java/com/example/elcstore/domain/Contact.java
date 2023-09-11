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

    @OneToMany(mappedBy = "contact", orphanRemoval = true)
    private List<CallTimeInterval> callTimeInterval;


//    user in dto
//    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
//    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number must contain only digits and optional leading '+'")
//    private String phoneNumber;
}
