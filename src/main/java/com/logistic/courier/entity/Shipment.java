package com.logistic.courier.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Shipment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="shipment_id")
	private Integer shipmentId;
	
	@Column(name="tracking_number")
	@NotBlank(message = "Tracking number is required")
	private String trackingNumber;
	
	@Column(name="source")
	@NotBlank(message = "Source is required")
	private String source;
	
	@Column(name="destination")
	@NotBlank(message = "Destination is required")
	private String destination;
	
	@Column(name="weight")
	@NotNull(message = "Weight is required")
	private Integer weight;
	
	@CreationTimestamp
	@Column(name="date_time")
	private LocalDateTime shipmentDateAndTime;
	
	@Column(name="delivery")
	@NotNull(message = "Delivery date is required")
	private LocalDate deliveryDate;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "status is required")
	private ShipmentStatus status;
	
	

}
