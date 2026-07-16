package com.logistic.courier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findByCustomerEmail(String customerEmail);

	Optional<Customer> findByCustomerPhoneNumber(String customerPhoneNumber);
	
}
