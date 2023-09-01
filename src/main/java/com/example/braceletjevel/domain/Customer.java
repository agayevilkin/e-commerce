package com.example.braceletjevel.domain;

import com.example.braceletjevel.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "customers")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "password")
    private String password;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> address;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
