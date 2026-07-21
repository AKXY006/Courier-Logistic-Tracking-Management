package com.logistic.courier.controller;

import java.time.LocalDate;
import java.util.List;

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

import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.ShipmentStatus;
import com.logistic.courier.service.ShipmentService;
import com.logistic.courier.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
	
	@Autowired
	private ShipmentService shipmentService;
	
	
	@PostMapping
//	public ResponseEntity<ResponseStructure<List<Shipment>>>  saveAllShipment(@Valid @RequestBody List<Shipment> shipments){
//		return shipmentService.saveAllShipment(shipments);
//	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Shipment>>>  findAllShipment(){
		return shipmentService.findAllShipment();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Shipment>>  findById(@PathVariable Integer id){
		return shipmentService.findById(id);
	}
	
	@GetMapping("/tracking/{trackingNumber}")
	public ResponseEntity<ResponseStructure<Shipment>>  findByTrackingNumber(@PathVariable String trackingNumber){
		return shipmentService.findByTrackingNumber(trackingNumber);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseStructure<Shipment>>  updateShipmentStatus(@PathVariable Integer id, @RequestBody ShipmentStatus shipmentStatus){
		return shipmentService.updateShipment(id,shipmentStatus);
	}
	
	 @PatchMapping("/{shipmentId}/agent/{deliveryId}")
	 public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(@PathVariable Integer shipmentId,@PathVariable Integer deliveryId) {
	        return shipmentService.assignDeliveryAgent(shipmentId, deliveryId);
	 }
	 
	 @PatchMapping("/{shipmentId}/warehouse/{warehouseId}")
	 public ResponseEntity<ResponseStructure<Shipment>> assignWarehouse(@PathVariable Integer shipmentId,@PathVariable Integer warehouseId) {
	        return shipmentService.assignWarehouse(shipmentId, warehouseId);
	 }
	 
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>>  deleteById(@PathVariable Integer id){
		return shipmentService.deleteById(id);
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByCustomerId(@PathVariable Integer customerId){
	    return shipmentService.findById(customerId);
	}

	@GetMapping("/warehouse/{warehouseId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByWarehouseId(@PathVariable Integer warehouseId){
	    return shipmentService.findByWarehouseId(warehouseId);
	}

	@GetMapping("/agent/{deliveryId}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByDeliveryAgentId(@PathVariable Integer deliveryId){
	    return shipmentService.findByDeliveryAgentId(deliveryId);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByStatus(@PathVariable ShipmentStatus status){
	    return shipmentService.findById(status);
	}
	
	
	@GetMapping("/route/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Shipment>>>  findBySourceAndDestination(@PathVariable String source, @PathVariable String destination){
		return shipmentService.findBySourceAndDestination(source,destination);
	}
	
	@GetMapping("/date/{deliveryDate}")
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByDeliveryDate(@PathVariable LocalDate deliveryDate){
		return shipmentService.findByDeliveryAgentId(deliveryDate);
	}
	
	@GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<ResponseStructure<List<Shipment>>> findByPagination(@PathVariable int pageNumber,@PathVariable int pageSize) {
        return shipmentService.findBySourceAndDestination(pageNumber, pageSize);
    }
	
	

}
