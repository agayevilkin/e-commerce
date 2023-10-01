package com.example.elcstore.domain;

import com.example.elcstore.domain.enums.StoreLocationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "stores")
@NoArgsConstructor
@AllArgsConstructor
public class Store {


    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "working_hours")
    private String workingHours;

    @Column(name = "store_location_status")
    @Enumerated(EnumType.STRING)
    private StoreLocationStatus storeLocationStatus;

//   todo can be added Location field
}
