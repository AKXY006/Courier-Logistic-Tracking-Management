package com.logistic.courier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.exception.DublicateResourceException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.CustomerRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer){
		Optional<Customer> optional = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
		
		if(optional.isPresent()) {
			throw new DublicateResourceException("Email Already In Use");
		}
		
		Optional<Customer> opt= customerRepository.findByCustomerPhoneNumber(customer.getCustomerPhoneNumber());
		
		if(opt.isPresent()) {
			throw new DublicateResourceException("Mobile Number Already In Use");
		}
		
		Customer cus = customerRepository.save(customer);
		
		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Employee Saved");
		responseStructure.setData(cus);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
	
	
        public ResponseEntity<ResponseStructure<List<Customer>>> saveAllCustomer(List<Customer> customers){
        	
        for(Customer cust : customers) {
		
		if(customerRepository.findByCustomerEmail(cust.getCustomerEmail()).isPresent()) {
			throw new DublicateResourceException("Email Already In Use");
		}
		if(customerRepository.findByCustomerPhoneNumber(cust.getCustomerPhoneNumber()).isPresent()) {
			throw new DublicateResourceException("Phone Number Already In Use");
		}
        }
		
		List<Customer> cus = customerRepository.saveAll(customers);
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All Customer Saved successfully");
		responseStructure.setData(cus);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
        
        
        
        public ResponseEntity<ResponseStructure<Customer>> findById(Integer customerId){
    		Optional<Customer> optional = customerRepository.findById(customerId);
    		
    		if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid CustomerId"+customerId);
    		}
    		
    		Customer customer = optional.get();
    		
    		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    		
    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Customer found Successfully");
    		responseStructure.setData(customer);
    		
    		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    		
    	}
        
        
        public ResponseEntity<ResponseStructure<Customer>> findByEmail(Integer customerEmail){
    		Optional<Customer> optional = customerRepository.findById(customerEmail);
    		
    		if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid Customer Email"+customerEmail);
    		}
    		
    		Customer customer = optional.get();
    		
    		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    		
    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Customer found Successfully");
    		responseStructure.setData(customer);
    		
    		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    		
    	}
        
        
	
	
	

}
