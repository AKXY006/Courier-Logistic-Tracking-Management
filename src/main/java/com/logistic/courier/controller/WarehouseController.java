package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.Warehouse;
import com.logistic.courier.service.WarehouseService;
import com.logistic.courier.util.ResponseStructure;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@PostMapping
	public RequestEntity<ResponseStructure<List<Warehouse>>> saveAll(@RequestBody List<Warehouse> warehouses){
		return WarehouseService.saveWarehouses(warehouses);
	}

}
