package com.logistic.courier.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.ShipmentStatus;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{
	
	    //1) Create Shipment -> PostMapping -> save()
	
		//2) Get All Shipment -> GetMapping -> findAll()

		//3) Get By Id -> GetMapping -> getById()
	
	   //4) Get By TrackingNumber -> GetMapping -> getByTrackingNumber()
	   Optional<Shipment> getByTrackingNumber(String trackingNumber);
	   
	   //5) update status -> PatchMapping --> updateBystatus()
	   Optional<Shipment> updateBystatus(ShipmentStatus status);
	   
	   //6) Assigned By Deliver Agent --> GetMapping -> GetById()
	   //Not Sure 
	   
	   //7) Assigned WareHouse
	   
	   //8) Delete shipment -> DeleteMapping -> getById()

	   //9) confuse 
	   
	   //10) confuse
	   
	   //11) confuse
	   
	   //13) Get By Source And Destination -> GetMapping -> getBySourceAndDestination()
	   List<Shipment> getBySourceAndDestination(String source, String destination);
	   
	   //14) Get By deliveryDate -> GetMapping -> getByDeliveryDate()
	   List<Shipment> getByDeliveryDate(String deliveryDate);
	   
	   //15) Get By Pagination And Sorting 
}
