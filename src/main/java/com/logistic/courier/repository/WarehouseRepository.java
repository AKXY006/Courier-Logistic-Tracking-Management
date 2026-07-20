package com.logistic.courier.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{
	
	Optional<Warehouse> findByWarehousePhoneNumber(String warehousePhoneNumber);

	List<Warehouse> findByWarehouseLocation(String warehouseLocation);

	List<Warehouse> findByCapacity(Integer capacity);

}
