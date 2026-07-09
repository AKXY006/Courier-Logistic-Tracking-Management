package com.logistic.courier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="warehouse_id")
	private Integer warehouseId;
	
	@Column(name="warehouse_name")
	@NotBlank(message = "Warehouse name is required")
	private String warehouseName;
	
	@Column(name="warehouse_location")
	@NotBlank(message = "warehouse location is required")
	private String warehouseLocation;
	
	@Column(name="capacity")
	@NotNull(message = "warehouse capacity is required")
	private Integer capacity;
	
	@Column(name="warehouse_phone_number", unique = true, length = 10)
	@NotBlank(message = "warehouse phone number is required")
	private String warehousePhoneNumber;

}
