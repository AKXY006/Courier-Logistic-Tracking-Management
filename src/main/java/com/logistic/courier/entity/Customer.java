package com.logistic.courier.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private String customerName;
	
	@Column(name="customer_email", unique = true, length = 100)
	private String customerEmail;
	
	@Column(name="customer_phone_number", unique = true, length = 10)
	private String customerPhoneNumber;
	
	@Column(name="customer_address", length = 300)
	private String customerAddress;
	
	

}
