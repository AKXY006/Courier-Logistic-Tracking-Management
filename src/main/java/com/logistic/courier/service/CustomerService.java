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
import com.logistic.courier.exception.DuplicateResourceException;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.CustomerRepository;
import com.logistic.courier.repository.ShipmentRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	//1)
	    public ResponseEntity<ResponseStructure<List<Customer>>> saveCustomer(List<Customer> customers){
	    	
	    for(Customer customer : customers) {
		Optional<Customer> optional = customerRepository.findByCustomerEmail(customer.getCustomerEmail());
		
		if(optional.isPresent()) {
			throw new DuplicateResourceException("Email Already In Use");
		}
		
		Optional<Customer> opt= customerRepository.findByCustomerPhoneNumber(customer.getCustomerPhoneNumber());
		
		if(opt.isPresent()) {
			throw new DuplicateResourceException("Mobile Number Already In Use");
		 }
		
	    }
		
		List<Customer> cus = customerRepository.saveAll(customers);
		
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Customer Saved Successfully");
		responseStructure.setData(cus);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
		
	}
	
	    //2)
        public ResponseEntity<ResponseStructure<List<Customer>>> findAllCustomer(){
        	
            List<Customer> customers = customerRepository.findAll();

            if (customers.isEmpty()) {
                throw new ResourceNotFoundException("No Customers Found");
            }
		ResponseStructure<List<Customer>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("All Customer Saved successfully");
		responseStructure.setData(customers);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
		
	}
        
        
        //3)
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
        
        //4)
        public ResponseEntity<ResponseStructure<Customer>> findByEmail(String customerEmail){
    		Optional<Customer> optional = customerRepository.findByCustomerEmail(customerEmail);
    		
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

    	            case "customerEmail": cust.setCustomerEmail((String) value);
    	                break;

    	            case "customerPhoneNumber":cust.setCustomerPhoneNumber((String) value);
    	                break;

    	            case "customerAddress":cust.setCustomerAddress((String) value);
    	                break;

    	            default: throw new InvalidInputException("Invalid Field : " + key);
    	            }
    		  }
    	           Customer customer = customerRepository.save(cust);
    	           
    	           ResponseStructure<Customer> responseStructure = new ResponseStructure<>();
    	           
    	           responseStructure.setStatusCode(HttpStatus.OK.value());
    	           responseStructure.setMessage("Update Customer Successfully");
    	           responseStructure.setData(cust);
    	           
    	           return new ResponseEntity<>(responseStructure,HttpStatus.OK);
        }
        
        //6)
         public ResponseEntity<ResponseStructure<String>> deletById(Integer customerId){
        	 

        		Optional<Customer> optional = customerRepository.findById(customerId);

        		if(optional.isEmpty()) {
        			throw new ResourceNotFoundException("Invalid Customer Id : " + customerId);
        		}

//        		if(shipmentRepository.existsByCustomerCustomerId(customerId)) {
//        			throw new ActiveStatusException("Customer cannot be deleted because orders exist.");
//        		}
        	 ResponseStructure<String> response = new ResponseStructure<>();

             response.setStatusCode(HttpStatus.OK.value());
             response.setMessage("Customer Record Deleted Successfully");
             response.setData("Success");

             return ResponseEntity.ok(response);
        	 
        	 
         }
        
         
         //7)
         
         public ResponseEntity<ResponseStructure<Customer>> findByCustomerContactNumber(String customerPhonenumber){
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
             public ResponseStructure<Page<Customer>> getPageByPagination(int pageNumber, int pageSize) {
    		    Page<Customer> page = customerRepository.findAll(PageRequest.of(pageNumber, pageSize));
    	        ResponseStructure<Page<Customer>> res = new ResponseStructure<>();

    	        if (page.isEmpty()) {
    	            throw new ResourceNotFoundException("Data Not Available To Be Display");
    	        }else {
    	            res.setStatusCode(HttpStatus.OK.value());
    	            res.setMessage("Data Retrieved Successfully");
    	            res.setData(page);
    	            return res;
    	        }
             }

	}	  
        
        
	
	
	


