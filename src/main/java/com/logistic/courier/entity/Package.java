package com.logistic.courier.entity;

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
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Integer packageId;

    @Enumerated(EnumType.STRING)
    @Column(name = "package_type")
    @NotNull(message = "Package type is required")
    private PackageType packageType;

    @Column(name = "fragile")
    @NotNull(message = "Fragile status is required")
    private Boolean fragile;

    @Column(name = "dimension")
    @NotNull(message = "Dimension is required")
    private Double dimension;
    
    @OneToOne
    @JoinColumn(name = "shipment_id")
    @JsonBackReference
    private Shipment shipment;
    
}