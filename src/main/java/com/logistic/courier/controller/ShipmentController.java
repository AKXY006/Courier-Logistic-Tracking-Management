package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.Shipment;
import com.logistic.courier.service.ShipmentService;
import com.logistic.courier.util.ResponseStructure;

@RestController
@RequestMapping("shipment")
public class ShipmentController {
	
	@Autowired
	private ShipmentService shipmentService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<List<Shipment>>>  saveAllShipment(@RequestBody List<Shipment> shipments){
		return shipmentService.saveAllShipment(shipments);
	}
	
	s
	

}
