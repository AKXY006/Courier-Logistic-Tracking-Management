package com.logistic.courier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	//1) Create Customer -> PostMapping -> save()
	
	//2) Get All Customer -> GetMapping -> findAll()

	//3) Get By Id -> GetMapping -> findById()
	
	//4) Get By Email -> GetMapping -> getByCustomerEmail()
	Optional<Customer> findByCustomerEmail(String customerEmail);
	
	//5) Update -> Patch/Put --> saveAll()
	
	//6) Delete -> DeleteMapping -> deleteById()
	
	//7) Get By contact -> GetMapping -> getByCuvstomerPhoneNumber()
	Optional<Customer> findByCustomerPhoneNumber(String customerPhoneNumber);
		
	//8) Get By Pagination & Sorting 
	
}
