package com.logistic.courier.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.DeliveryAgent;
import com.logistic.courier.service.DeliveryAgentService;
import com.logistic.courier.util.ResponseStructure;


@RestController
@RequestMapping("/deliveryagent")
public class DeliveryAgentController {
	
	@Autowired
	private DeliveryAgentService deliveryAgentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>>  saveDeliveryAgent(@RequestBody List<DeliveryAgent> deliveryAgents){
		return deliveryAgentService.saveDeliveryAgent(deliveryAgents);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>>  findAllDeliveryAgent(){
		return deliveryAgentService.findAllDeliveryAgent();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>>  findById(@PathVariable Integer id){
		return deliveryAgentService.findById(id);
	}
	
	@GetMapping("/vehiclenumber/{vehiclenumber}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> findByVehicleNumber(@PathVariable String vehiclenumber){
		return deliveryAgentService.findDeliveryAgentByVehicleNumber(vehiclenumber);
	}
	
	@GetMapping("/contact/{contactNumber}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>> findByContactNumber(@PathVariable String contactNumber){
		return deliveryAgentService.findDeliveryAgentByphoneNumber(contactNumber);
	}
	
	@GetMapping("/rating/{rating}")
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>>  findByRatingGreaterThan(@PathVariable Float rating){
		return deliveryAgentService.findDeliveryAgentByRatingGreaterThan(rating);
	}
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<DeliveryAgent>>  updateDeliveryAgent(@PathVariable Integer id, @RequestBody Map<String, Object> updates){
		return deliveryAgentService.updateDeliveryAgent(id, updates);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Integer id){
		return deliveryAgentService.deleteById(id);
	}
	
	

}
