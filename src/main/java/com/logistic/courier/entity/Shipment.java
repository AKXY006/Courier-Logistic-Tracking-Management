package com.logistic.courier.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	
	@Column(name="tracking_number", unique = true)
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
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonBackReference("customer-shipment")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	@JsonBackReference("warehouse-shipment")
	private Warehouse warehouse;

	@ManyToOne
	@JoinColumn(name = "delivery_id")
	@JsonBackReference("agent-shipment")
	private DeliveryAgent deliveryAgent;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id")
	@JsonManagedReference("shipment-payment")
	private Payment payment;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "package_id")
	@JsonManagedReference("shipment-package")
	private Package packageEntity;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	@JsonManagedReference("shipment-tracking")
	private List<TrackingHistory> trackingHistory;
}
