package com.logistic.courier.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logistic.courier.entity.DeliveryAgent;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer>{
	
	  	Optional<DeliveryAgent> findByVehicleNumber(String vehicleNumber);
	
		Optional<DeliveryAgent> findByPhoneNumber(String phoneNumber);

	    List<DeliveryAgent> findByRating(Float rating);
		
}
