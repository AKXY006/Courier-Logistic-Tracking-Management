package com.logistic.courier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{
	

	//1) Create WareHouse -> PostMapping -> save()
	
	//2) Get All WareHouse -> GetMapping -> findAll()

	//3) Get By Id -> GetMapping -> getById()
	
	//4) Get By warehouseLocation -> GetMapping -> getByWarehouseLocation()
	//List<Warehouse> findByWarehouseLocation(String warehouseLocation);
	
	//5) Get By Capacity -> GetMapping -> getByCapacityGreaterThan()
	//List<Warehouse> findByCapacityGreaterThan(Integer capacity);
	
	//6) update  -> PostMapping --> save()
	
	//8) Delete WareHouse -> DeleteMapping -> getById()
	
	
	//Optional<Warehouse> findByWarehousePhoneNumber(String warehousePhoneNumber);
	

}
