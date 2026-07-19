package com.logistic.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.CustomerRepository;
import com.logistic.courier.repository.DeliveryAgentRepository;
import com.logistic.courier.repository.PackageRepository;
import com.logistic.courier.repository.PaymentRepository;
import com.logistic.courier.repository.ShipmentRepository;
import com.logistic.courier.repository.TrackingHistoryRepository;
import com.logistic.courier.repository.WarehouseRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class ShipmentService {
	
	@Autowired
	private ShipmentRepository shipmentRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private DeliveryAgentRepository deliveryAgentRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;

	public ResponseEntity<ResponseStructure<List<Shipment>>> saveAllShipment(List<Shipment> shipments) {
		
		for (Shipment shipment : shipments) {

		    if (!customerRepository.findById(shipment.getCustomer().getCustomerId()).isPresent()) {
		        throw new ResourceNotFoundException("Customer Not Found");
		    }

		    if (!warehouseRepository.findById(shipment.getWarehouse().getWarehouseId()).isPresent()) {
		        throw new ResourceNotFoundException("Warehouse Not Found");
		    }

		    if (!deliveryAgentRepository.findById(shipment.getDeliveryAgent().getDeliveryId()).isPresent()) {
		        throw new ResourceNotFoundException("Delivery Agent Not Found");
		    }

		    if (!packageRepository.findById(shipment.getPackage1().getPackageId()).isPresent()) {
		        throw new ResourceNotFoundException("Package Not Found");
		    }

		    if (!paymentRepository.findById(shipment.getPayment().getPaymentId()).isPresent()) {
		        throw new ResourceNotFoundException("Payment Not Found");
		    }

//		    for (TrackingHistory history : shipment.getTrackingHistory()) {
//
//		        if (!trackingHistoryRepository.findById(history.getTrackingId()).isPresent()) {
//		            throw new ResourceNotFoundException("Tracking History Not Found");
//		        }
//		    }
		}

		List<Shipment> savedShipments = shipmentRepository.saveAll(shipments);

		ResponseStructure<List<Shipment>> response = new ResponseStructure<>();
		response.setStatusCode(201);
		response.setMessage("Shipment Saved Successfully");
		response.setData(savedShipments);

		return ResponseEntity.status(201).body(response);
		
		
		
		
	}
	
	

}
