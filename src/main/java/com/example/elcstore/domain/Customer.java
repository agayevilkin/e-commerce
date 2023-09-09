package com.example.elcstore.domain;

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

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Address> address;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders;

}
