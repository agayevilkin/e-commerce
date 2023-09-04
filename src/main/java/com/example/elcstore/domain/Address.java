package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.District;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_name")
    private String address;

    @Column(name = "address_title")
    private String addressTitle;

    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    private District district;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
