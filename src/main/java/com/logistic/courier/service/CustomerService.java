package com.logistic.courier.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.exception.ActiveStatusException;
import com.logistic.courier.exception.DuplicateResourceException;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.CustomerRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	

	
	//1)
	    public ResponseEntity<ResponseStructure<List<Customer>>> saveCustomer(List<Customer> customers){
	    	
	    for(Customer customer : customers) {
		Optional<Customer> optional = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
		
		if(optional.isPresent()) {
			throw new DuplicateResourceException("Email Already In Use");
		}
		
		String phoneNumber = customer.getCustomerPhoneNumber();

        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new InvalidInputException("Phone Number Must Be Exactly 10 Digits");
        }
        
        Optional<Customer> opt = customerRepository.findByCustomerPhoneNumber(phoneNumber);

        if (opt.isPresent()) {
            throw new DuplicateResourceException("Phone Number Already In Use");
        }
		
	    }
		
		List<Customer> savedCustomers = customerRepository.saveAll(customers);
		
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Customers Saved Successfully");
		responseStructure.setData(savedCustomers);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
		
	}
	
	    //2)
        public ResponseEntity<ResponseStructure<List<Customer>>> findAllCustomer(){
        	
            List<Customer> customers = customerRepository.findAll();

            if (customers.isEmpty()) {
                throw new ResourceNotFoundException("No Customers Found");
            }
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All Customers Fetched Successfully");
		responseStructure.setData(customers);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
        
        
        //3)
        public ResponseEntity<ResponseStructure<Customer>> findById(Integer customerId){
    		Optional<Customer> optional = customerRepository.findById(customerId);
    		
    		if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Customer Not Found With Id "+customerId);
    		}
    		
    		Customer customer = optional.get();
    		
    		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    		
    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Customer Found Successfully");
    		responseStructure.setData(customer);
    		
    		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    		
    	}
        
        //4)
        public ResponseEntity<ResponseStructure<Customer>> findByEmail(String customerEmail){
    		Optional<Customer> optional = customerRepository.findByCustomerEmail(customerEmail);
    		
    		if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid Customer Email "+customerEmail);
    		}
    		
    		Customer customer = optional.get();
    		
    		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    		
    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Customer found Successfully");
    		responseStructure.setData(customer);
    		
    		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
    		
    	}
        
        //5)
        public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Integer customerId, Map<String, Object> updates){
             Optional<Customer> optional = customerRepository.findById(customerId);
    		  if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid CustomerId"+customerId);
    		}
    		  
    		  Customer cust = optional.get();
    		   
    		  for (Map.Entry<String, Object> entry : updates.entrySet()) {

    	            String key = entry.getKey();
    	            Object value = entry.getValue();

    	            switch (key) {

    	            case "customerName": cust.setCustomerName((String) value);
    	                break;

    	            case "customerEmail": 
    	            	
    	            	 Optional<Customer> email = customerRepository.findByCustomerEmail((String) value);

    	            	    if (email.isPresent() && !email.get().getCustomerId().equals(customerId)) {
    	            	        throw new DuplicateResourceException("Email Already In Use");
    	            	    }
    	            	    
    	            	cust.setCustomerEmail((String) value);
    	                break;

    	            case "customerPhoneNumber":
    	            	
    	            	  String phone = (String) value;

    	                  if (phone.length() != 10) {
    	                      throw new InvalidInputException("Phone Number Must Be 10 Digits");
    	                  }

    	                  Optional<Customer> contact = customerRepository.findByCustomerPhoneNumber(phone);

    	                  if (contact.isPresent() && !contact.get().getCustomerId().equals(customerId)) {
    	                      throw new DuplicateResourceException("Phone Number Already In Use");
    	                  }
    	            	
    	            	cust.setCustomerPhoneNumber((String) value);
    	                break;

    	            case "customerAddress":cust.setCustomerAddress((String) value);
    	                break;

    	            default: throw new InvalidInputException("Invalid Field : " + key);
    	            }
    		  }
    	          Customer saveCustomer = customerRepository.save(cust);
    	           
    	           ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    	           
    	           responseStructure.setStatusCode(HttpStatus.OK.value());
    	           responseStructure.setMessage("Update Customer Successfully");
    	           responseStructure.setData(saveCustomer);
    	           
    	           return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
        
        //6)
         public ResponseEntity<ResponseStructure<String>> deletById(Integer customerId){
        	 

        		Optional<Customer> optional = customerRepository.findById(customerId);

        		if(optional.isEmpty()) {
        			throw new ResourceNotFoundException("Invalid Customer Id : " + customerId);
        		}
        			
        	 customerRepository.deleteById(customerId);
        	 ResponseStructure<String> response = new ResponseStructure<>();

             response.setStatusCode(HttpStatus.OK.value());
             response.setMessage("Customer Record Deleted Successfully");
             response.setData("Success");

             return ResponseEntity.ok(response);
        	 
         }
        
         
         //7)
         
         public ResponseEntity<ResponseStructure<Customer>> findByCustomerPhoneNumber(String customerPhonenumber){
     		Optional<Customer> optional = customerRepository.findByCustomerPhoneNumber(customerPhonenumber);
     		
     		if(optional.isEmpty()) {
     			throw new ResourceNotFoundException("Invalid Customer PhoneNumber"+customerPhonenumber);
     		}
     		
     		Customer customer = optional.get();
     		
     		ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
     		
     		responseStructure.setStatusCode(HttpStatus.OK.value());
     		responseStructure.setMessage("Customer found Successfully");
     		responseStructure.setData(customer);
     		
     		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
     		
     	}

         //8)
         public ResponseEntity<ResponseStructure<Page<Customer>>> getPageByPagination(int pageNumber, int pageSize) {

        	    if (pageNumber < 1) {
        	        throw new InvalidInputException("Page number must be greater than or equal to 1");
        	    }

        	    Page<Customer> page = customerRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));

        	    if (page.isEmpty()) {
        	        throw new ResourceNotFoundException("Data Not Available To Be Display");
        	    }

        	    ResponseStructure<Page<Customer>> responseStructure = new ResponseStructure<>();

        	    responseStructure.setStatusCode(HttpStatus.OK.value());
        	    responseStructure.setMessage("Data Retrieved Successfully");
        	    responseStructure.setData(page);

        	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
        	}
}

                
        
