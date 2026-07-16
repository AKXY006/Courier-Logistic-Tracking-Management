package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.service.CustomerService;
import com.logistic.courier.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> save(@RequestBody List<Customer> customers){
		return customerService.saveCustomer(customers);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Customer>>> findAll() {
	    return customerService.findAllCustomer();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>>  findById(@PathVariable Integer id){
		return customerService.findById(id);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseStructure<Customer>>  findByEmail(@PathVariable String email){
		return customerService.findByEmail(email);
	}
	
//	@PatchMapping
//	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer){
//		return customerService.
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>  deleteById(@PathVariable Integer id){
		return customerService.deletById(id);
	}
	
	
	
	
	
}
