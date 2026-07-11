package com.logistic.courier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Warehouse;
import com.logistic.courier.exception.DublicateResourceException;
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

}
