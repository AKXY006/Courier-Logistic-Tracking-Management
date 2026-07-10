package com.logistic.courier.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logistic.courier.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer>{
	
	    //1) Create DeliveryAgent -> PostMapping -> save()
	
		//2) Get All DeliveryAgent -> GetMapping -> findAll()

		//3) Get By Id -> GetMapping -> getById()
		
		//4) Get By vehicleNumber -> GetMapping -> getByvehicleNumber()
		Optional<DeliveryAgent> getByVehicleNumber(String vehicaleNumber);
		
		//5) Get By phoneNumber -> GetMapping -> getByphoneNumber()
		Optional<DeliveryAgent> getByPhoneNumber(String phoneNumber);
		
		//6) Get By ratingGreaterThan -> GetMapping -> getByRatingGreaterThan()
	    List<DeliveryAgent> getByRatingGreaterOptionalThan(String rating);
		
		//7) Update -> Patch/Put --> saveAll()
		
		//8) Delete -> DeleteMapping -> deleteById()
		
		//9) update Agent -> PatchMapping -> saveAll()


}
