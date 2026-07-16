package com.logistic.courier.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @Column(name = "amount")
    @NotNull(message = "Amount is required")
    private Double amount;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment type is required")
    private PaymentType paymentType;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Payment status is required")
    private PaymentStatus paymentStatus;

    @CreationTimestamp
    @Column(name = "payment_date_time")
    private LocalDateTime paymentDateAndTime;
    
    @OneToOne
    @JoinColumn(name = "shipment_id")
    @JsonBackReference
    private Shipment shipment;
}