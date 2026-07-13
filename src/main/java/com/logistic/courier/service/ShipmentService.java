package com.logistic.courier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistic.courier.repository.ShipmentRepository;

@Service
public class ShipmentService {
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	

}
