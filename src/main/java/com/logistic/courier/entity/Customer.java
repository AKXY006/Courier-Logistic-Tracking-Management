package com.logistic.courier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="customer_name")
	@NotBlank(message = "customer name is required")
	private String customerName;
	
	@Column(name="customer_email", unique = true)
	@Email(message = "Enter a valid email")
	@NotBlank(message = "Customer email is required")
	private String customerEmail;
	
	@Column(name="customer_phone_number", unique = true, length = 10)
	@NotBlank(message = "customer phone number is required")
	private String customerPhoneNumber;
	
	@Column(name="customer_address")
	@NotBlank(message = "address is required")
	private String customerAddress;
	

}
