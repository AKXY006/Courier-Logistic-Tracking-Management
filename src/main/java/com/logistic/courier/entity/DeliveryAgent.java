package com.logistic.courier.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
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
public class DeliveryAgent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Integer deliveryId;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "phone_number", unique = true, length = 10)
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @Column(name = "vehicle_number", unique = true)
    @NotBlank(message = "Vehicle number is required")
    private String vehicleNumber;

    @Column(name = "availability_status")
    @NotNull(message = "Availability status is required")
    private Boolean availabilityStatus;

    @Column(name = "rating")
    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Float rating;
    
    @OneToMany(mappedBy = "deliveryAgent", cascade = CascadeType.ALL)
    @JsonManagedReference("agent-shipment")
    private List<Shipment> shipments;

}