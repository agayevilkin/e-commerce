package com.example.elcstore.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Table(name = "payment_results")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResults {

    @Id
    @Column(name = "payment_result_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "reference_number")
    private String referenceNumber;

    @Column(name = "message")
    private String message;
    //other payment details
}
