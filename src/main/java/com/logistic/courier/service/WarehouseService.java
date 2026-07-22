package com.logistic.courier.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Warehouse;
import com.logistic.courier.exception.DuplicateResourceException;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.InvalidStatusException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.WarehouseRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class WarehouseService {
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	public ResponseEntity<ResponseStructure<List<Warehouse>>> saveWarehouses(List<Warehouse> warehouses) {

	    if (warehouses == null || warehouses.isEmpty()) {
	        throw new InvalidInputException("Warehouse List Cannot Be Empty");
	    }

	    for (Warehouse warehouse : warehouses) {

	        String phoneNumber = warehouse.getWarehousePhoneNumber();

	        if (phoneNumber == null || phoneNumber.length() != 10) {
	            throw new InvalidInputException("Phone Number Must Be Exactly 10 Digits");
	        }

	        for (int i = 0; i < phoneNumber.length(); i++) {
	            if (!Character.isDigit(phoneNumber.charAt(i))) {
	                throw new InvalidInputException("Phone Number Must Contain Only Digits");
	            }
	        }

	        Optional<Warehouse> optional = warehouseRepository.findByWarehousePhoneNumber(phoneNumber);

	        if (optional.isPresent()) {
	            throw new DuplicateResourceException("Phone Number Already In Use");
	        }
	    }

	    List<Warehouse> savedWarehouses = warehouseRepository.saveAll(warehouses);

	    ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.CREATED.value());
	    responseStructure.setMessage("Warehouses Saved Successfully");
	    responseStructure.setData(savedWarehouses);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}
	
	
	
	
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findAllWarehouse(){
		
		List<Warehouse> warehouses= warehouseRepository.findAll();
		
		if(warehouses.isEmpty()) {
			throw new ResourceNotFoundException("Warehouses not found");
		}
			
		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Warehouse found Successfully");
		responseStructure.setData(warehouses);
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	
     
     public ResponseEntity<ResponseStructure<Warehouse>> findById(Integer warehouseId){
 		Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
 	
 		if(optional.isEmpty()) {
 			throw new ResourceNotFoundException("Warehouse Not Found With Id : " + warehouseId);
 		}
 		
 		ResponseStructure<Warehouse> responseStructure = new ResponseStructure<>();
 		
 		responseStructure.setStatusCode(HttpStatus.OK.value());
 		responseStructure.setMessage("Warehouse Found Successfully");
 		responseStructure.setData(optional.get());
 		
 		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
 		
 	}
     
     
     
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findByLocation(String warehouseLocation){
  		List<Warehouse> warehouses = warehouseRepository.findByWarehouseLocation(warehouseLocation);
  	
  		if(warehouses.isEmpty()) {
  			throw new ResourceNotFoundException("No Warehouse Found At Location : " + warehouseLocation);
  		}
  		
  		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
  		
  		responseStructure.setStatusCode(HttpStatus.OK.value());
  		responseStructure.setMessage("Warehouses Found Successfully");
  		responseStructure.setData(warehouses);
  		
  		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
  		
  	}
     
     
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findByCapacity(Integer capacity){
   		List<Warehouse> warehouses = warehouseRepository.findByCapacity(capacity);
   	
   		if(warehouses.isEmpty()) {
   			throw new ResourceNotFoundException("No warehouse found with capacity "+capacity);
   		}
   		
   		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
   		
   		responseStructure.setStatusCode(HttpStatus.OK.value());
   		responseStructure.setMessage("warehouse found with capacity"+capacity);
   		responseStructure.setData(warehouses);
   		
   		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
   		
   	 }
     
     public ResponseEntity<ResponseStructure<String>> deleteById(Integer warehouseId) {

    	    Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);

    	    if (optional.isEmpty()) {
    	        throw new ResourceNotFoundException("Warehouse Not Found With Id : " + warehouseId);
    	    }

    	    Warehouse warehouse = optional.get();

    	    if (warehouse.getShipments() != null && !warehouse.getShipments().isEmpty()) {
    	        throw new InvalidStatusException("Warehouse Cannot Be Deleted Because Shipments Are Assigned");
    	    }

    	    warehouseRepository.delete(warehouse);

    	    ResponseStructure<String> responseStructure = new ResponseStructure<>();

    	    responseStructure.setStatusCode(HttpStatus.OK.value());
    	    responseStructure.setMessage("Warehouse Deleted Successfully");
    	    responseStructure.setData("Success");

    	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    	}
     
     
	 public ResponseEntity<ResponseStructure<Warehouse>> updateWarehouse(Integer warehouseId, Map<String, Object> updates) {
		Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("warehouse Id not Found");
		}
		Warehouse warehouse = optional.get();
		
		 for (Map.Entry<String, Object> entry : updates.entrySet()) {

	            String key = entry.getKey();
	            Object value = entry.getValue();
	            
	            switch (key) {
				case "warehouseName": warehouse.setWarehouseName((String) value);
				         break;
				case "warehouseLocation" : warehouse.setWarehouseLocation((String) value);
				break;
				
				case "capacity" : warehouse.setCapacity(((Number) value).intValue());
				break;
				
				case "warehousePhoneNumber" :
					  String phoneNumber = (String) value;

			            if (phoneNumber.length() != 10) {
			                throw new InvalidInputException("Phone Number Must Be Exactly 10 Digits");
			            }

			            for (char c : phoneNumber.toCharArray()) {
			                if (!Character.isDigit(c)) {
			                    throw new InvalidInputException("Phone Number Must Contain Only Digits");
			                }
			            }

			            Optional<Warehouse> existingWarehouse = warehouseRepository
			                    .findByWarehousePhoneNumber(phoneNumber);

			            if (existingWarehouse.isPresent() && !existingWarehouse.get().getWarehouseId().equals(warehouseId)) {

			                throw new DuplicateResourceException("Warehouse Phone Number Already Exists");
			            }

			            warehouse.setWarehousePhoneNumber(phoneNumber);
			            break;
					
				default:
					throw new InvalidInputException("Invalid Field : " + key);
				}
		 }
	            
	            Warehouse updatedWarehouse = warehouseRepository.save(warehouse);
	            
	            ResponseStructure<Warehouse> responseStructure = new ResponseStructure<>();
	            
	            responseStructure.setStatusCode(HttpStatus.OK.value());
 	           responseStructure.setMessage("Warehouse updated Successfully");
 	           responseStructure.setData(updatedWarehouse);
 	           
 	           return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	         
	 } 
}
