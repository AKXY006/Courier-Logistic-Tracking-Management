package com.logistic.courier.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.entity.DeliveryAgent;
import com.logistic.courier.exception.DuplicateResourceException;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.DeliveryAgentRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class DeliveryAgentService {
	
	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> saveDeliveryAgent(List<DeliveryAgent> deliveryAgents){
		
		for(DeliveryAgent deliveryAgent: deliveryAgents) {
		Optional<DeliveryAgent> optional = deliveryAgentRepository.findByPhoneNumber(deliveryAgent.getPhoneNumber());
		
		if(optional.isPresent()) {
			throw new DuplicateResourceException("Phone Number is Already Exist");
		}
		
       Optional<DeliveryAgent> opt = deliveryAgentRepository.findByVehicleNumber(deliveryAgent.getVehicleNumber());
		
		if(opt.isPresent()) {
			throw new DuplicateResourceException("Vehicle Number is Already Exist");
		}
		
		}
		
		List<DeliveryAgent> saveDeliveryAgents = deliveryAgentRepository.saveAll(deliveryAgents);
		
		ResponseStructure<List<DeliveryAgent>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Save DeliverAgent Successfully");
		responseStructure.setData(saveDeliveryAgents);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
	}

	
	
	public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> findAllDeliveryAgent(){
		
		List<DeliveryAgent> dAgents= deliveryAgentRepository.findAll();
		
		if(dAgents.isEmpty()) {
			throw new ResourceNotFoundException("No delivery agent");
		}
		
		List<DeliveryAgent> deliveryAgents=deliveryAgentRepository.findAll();
		
		ResponseStructure<List<DeliveryAgent>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All delivery Agent");
		responseStructure.setData(deliveryAgents);
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<DeliveryAgent>> findById(Integer deliveryId){
		
		Optional<DeliveryAgent> optional= deliveryAgentRepository.findById(deliveryId);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("No delivery agent");
		}
		
		ResponseStructure<DeliveryAgent> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("delivery Agent found successfully");
		responseStructure.setData(optional.get());
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	
       public ResponseEntity<ResponseStructure<DeliveryAgent>> findDeliveryAgentByVehicleNumber(String vehicleNumber){
		
		Optional<DeliveryAgent> optional= deliveryAgentRepository.findByVehicleNumber(vehicleNumber);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("No delivery agent");
		}
		
		ResponseStructure<DeliveryAgent> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("All delivery Agent");
		responseStructure.setData(optional.get());
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
       
       public ResponseEntity<ResponseStructure<DeliveryAgent>> findDeliveryAgentByphoneNumber(String phoneNumber){
   		
   		Optional<DeliveryAgent> optional= deliveryAgentRepository.findByPhoneNumber(phoneNumber);
   		
   		if(optional.isEmpty()) {
   			throw new ResourceNotFoundException("No delivery agent");
   		}
   		
   		ResponseStructure<DeliveryAgent> responseStructure = new ResponseStructure<>();
   		
   		responseStructure.setStatusCode(HttpStatus.OK.value());
   		responseStructure.setMessage("All delivery Agent");
   		responseStructure.setData(optional.get());
   		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
   	}
       
       public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> findDeliveryAgentByRatingGreaterThan(Float rating){

    		List<DeliveryAgent> list = deliveryAgentRepository.findByRatingGreaterThan(rating);

    		if(list.isEmpty()) {
    			throw new ResourceNotFoundException("No Delivery Agent Found");
    		}

    		ResponseStructure<List<DeliveryAgent>> responseStructure = new ResponseStructure<>();

    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Delivery Agents Found Successfully");
    		responseStructure.setData(list);

    		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    	}
       
       
       public ResponseEntity<ResponseStructure<DeliveryAgent>> updateDeliveryAgent(Integer deliveryId, Map<String, Object> updates){
    		Optional<DeliveryAgent> optional = deliveryAgentRepository.findById(deliveryId);

    		if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid Delivery Agent Id " + deliveryId);
    		}

    		DeliveryAgent deliveryAgent = optional.get();
    		for(Map.Entry<String, Object> entry : updates.entrySet()) {

    			String key = entry.getKey();
    			Object value = entry.getValue();

    			switch(key) {

    			case "name":deliveryAgent.setName((String) value);
    				break;

    			case "phoneNumber":deliveryAgent.setPhoneNumber((String) value);
    				break;

    			case "vehicleNumber":deliveryAgent.setVehicleNumber((String) value);
    				break;

    			case "availabilityStatus":deliveryAgent.setAvailabilityStatus((Boolean) value);
    				break;

    			case "rating":deliveryAgent.setRating(Float.parseFloat(value.toString()));
    				break;

    			default:
    				throw new InvalidInputException("Invalid Field : " + key);
    			}
    		}

    		DeliveryAgent agent = deliveryAgentRepository.save(deliveryAgent);

    		ResponseStructure<DeliveryAgent> responseStructure = new ResponseStructure<>();

    		responseStructure.setStatusCode(HttpStatus.OK.value());
    		responseStructure.setMessage("Delivery Agent Updated Successfully");
    		responseStructure.setData(agent);

    		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    	}
       
	
	
       public ResponseEntity<ResponseStructure<String>> deleteById(Integer deliveryId){
    		  Optional<DeliveryAgent> optional = deliveryAgentRepository.findById(deliveryId);

    		 if(optional.isEmpty()) {
    			throw new ResourceNotFoundException("Invalid Delivery Agent Id " + deliveryId);
    		}

    	      deliveryAgentRepository.deleteById(deliveryId);

    		  ResponseStructure<String> responseStructure = new ResponseStructure<>();

    		 responseStructure.setStatusCode(HttpStatus.OK.value());
    		 responseStructure.setMessage("Delivery Agent Deleted Successfully");
    		 responseStructure.setData("Success");

    		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
    	}
	
	   
       

}
