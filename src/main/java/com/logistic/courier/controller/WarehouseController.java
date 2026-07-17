package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

}
