package com.logistic.courier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Warehouse;
import com.logistic.courier.exception.DublicateResourceException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.WarehouseRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class WarehouseService {
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	public ResponseEntity<ResponseStructure<Warehouse>> saveWarehouse(Warehouse warehouse){
		Optional<Warehouse> optional = warehouseRepository.findByWarehousePhoneNumber(warehouse.getWarehousePhoneNumber());
		
		if(optional.isPresent()) {
			throw new DublicateResourceException("Phone NUmber Already In Use");
		}
		
		Warehouse warehouse2 = warehouseRepository.save(warehouse);
		
		ResponseStructure<Warehouse> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Warehouse Saved Successfully");
		responseStructure.setData(warehouse2);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
	
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findAllWarehouse(){
		
		List<Warehouse> warehouses= warehouseRepository.findAll();
		
		if(warehouses.isEmpty()) {
			throw new ResourceNotFoundException("No Warehouse found");
		}
			
		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Warehouse found Successfully");
		responseStructure.setData(warehouses);
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	
     
     public ResponseEntity<ResponseStructure<Warehouse>> findById(Integer warehouseId){
 		Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
 	
 		if(optional.isPresent()) {
 			throw new DublicateResourceException("Id not found");
 		}
 		
 		ResponseStructure<Warehouse> responseStructure = new ResponseStructure<>();
 		
 		responseStructure.setStatusCode(HttpStatus.OK.value());
 		responseStructure.setMessage("Id found Successfully");
 		responseStructure.setData(optional.get());
 		
 		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
 		
 	}
     
     
     
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findByLocationEntity(String warehouseLocation){
  		List<Warehouse> optional = warehouseRepository.findByWarehouseLocation(warehouseLocation);
  	
  		if(optional.isEmpty()) {
  			throw new ResourceNotFoundException("Location Not found");
  		}
  		
  		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
  		
  		responseStructure.setStatusCode(HttpStatus.OK.value());
  		responseStructure.setMessage("Id found Successfully");
  		responseStructure.setData(optional);
  		
  		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
  		
  	}
     
     
     public ResponseEntity<ResponseStructure<List<Warehouse>>> findByCapacityGreaterThan(Integer capacity){
   		List<Warehouse> optional = warehouseRepository.findByCapacityGreaterThan(capacity);
   	
   		if(optional.isEmpty()) {
   			throw new ResourceNotFoundException("No warehouse found with capacity greaterthan "+capacity);
   		}
   		
   		ResponseStructure<List<Warehouse>> responseStructure = new ResponseStructure<>();
   		
   		responseStructure.setStatusCode(HttpStatus.OK.value());
   		responseStructure.setMessage("warehouse found with capacity greaterthan "+capacity);
   		responseStructure.setData(optional);
   		
   		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
   		
   	}
     
     
     public ResponseEntity<ResponseStructure<String>> deleteById(Integer warehouseId){
  		Optional<Warehouse> optional = warehouseRepository.findById(warehouseId);
  	
  		if(optional.isPresent()) {
  			throw new DublicateResourceException("Id not found");
  		}
  		
  		warehouseRepository.deleteById(warehouseId);
  		ResponseStructure<String> responseStructure = new ResponseStructure<>();
  		
  		responseStructure.setStatusCode(HttpStatus.OK.value());
  		responseStructure.setMessage("Id delete Successfully");
  		responseStructure.setData("Success");
  		
  		return new ResponseEntity<>(responseStructure,HttpStatus.OK);	
  	}

}
