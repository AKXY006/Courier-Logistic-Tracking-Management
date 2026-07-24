package com.logistic.courier.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.Warehouse;
import com.logistic.courier.service.WarehouseService;
import com.logistic.courier.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<List<Warehouse>>> saveAll(@RequestBody @Valid List<Warehouse> warehouses){
		return warehouseService.saveWarehouses(warehouses);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Warehouse>>> findAllWarehouse(){
		return warehouseService.findAllWarehouse();
	}
	
	@GetMapping("/{warehouseId}")
	public ResponseEntity<ResponseStructure<Warehouse>>  findById(@PathVariable Integer warehouseId){
		return warehouseService.findById(warehouseId);
	}
	
	@GetMapping("/location/{warehouseLocation}")
	public ResponseEntity<ResponseStructure<List<Warehouse>>>  findByLocation(@PathVariable String warehouseLocation){
		return warehouseService.findByLocation(warehouseLocation);
	}
	
	@GetMapping("/capacity/{capacity}")
	public ResponseEntity<ResponseStructure<List<Warehouse>>>  findByCapacity(@PathVariable Integer capacity){
		return warehouseService.findByCapacity(capacity);
	}
	
	@DeleteMapping("/{warehouseId}")
	public ResponseEntity<ResponseStructure<String>> deleteWarehouse(@PathVariable Integer warehouseId){
		return warehouseService.deleteById(warehouseId);
	}
	
	@PatchMapping("/{warehouseId}")
	public ResponseEntity<ResponseStructure<Warehouse>> updateWarehouse(@PathVariable Integer warehouseId, @RequestBody Map<String, Object> updates){
		return warehouseService.updateWarehouse(warehouseId,updates);
	}
}
