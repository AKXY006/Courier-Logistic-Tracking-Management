package com.logistic.courier.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.DeliveryAgent;
import com.logistic.courier.exception.DublicateResourceException;
import com.logistic.courier.repository.DeliveryAgentRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class DeliveryAgentService {
	
	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> saveDeliveryAgent(DeliveryAgent deliveryAgent){
		
		Optional<DeliveryAgent> optional = deliveryAgentRepository.findByPhoneNumber(deliveryAgent.getPhoneNumber());
		
		if(optional.isPresent()) {
			throw new DublicateResourceException("Phone Number is Already Exist");
		}
		
       Optional<DeliveryAgent> opt = deliveryAgentRepository.findByVehicleNumber(deliveryAgent.getVehicleNumber());
		
		if(opt.isPresent()) {
			throw new DublicateResourceException("Vehicle Number is Already Exist");
		}
		
		DeliveryAgent dA = deliveryAgentRepository.save(deliveryAgent);
		
		ResponseStructure<DeliveryAgent> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Save DeliverAgent Successfully");
		responseStructure.setData(dA);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
	}

}
