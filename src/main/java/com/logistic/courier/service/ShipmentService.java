package com.logistic.courier.service;

import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.ShipmentStatus;
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
//		
//		  if (shipments == null || shipments.isEmpty()) {
//		        throw new InvalidInputException("Shipment List Cannot Be Empty");
//		    }
//		
//		for (Shipment shipment : shipments) {
//
//		    if (!customerRepository.findById(shipment.getCustomer().getCustomerId()).isPresent()) {
//		        throw new ResourceNotFoundException("Customer Not Found");
//		    }
//
//		    if (!warehouseRepository.findById(shipment.getWarehouse().getWarehouseId()).isPresent()) {
//		        throw new ResourceNotFoundException("Warehouse Not Found");
//		    }
//
//		    if (!deliveryAgentRepository.findById(shipment.getDeliveryAgent().getDeliveryId()).isPresent()) {
//		        throw new ResourceNotFoundException("Delivery Agent Not Found");
//		    }
//
//		    if (!packageRepository.findById(shipment.getPackageEntity().getPackageId()).isPresent()) {
//		        throw new ResourceNotFoundException("Package Not Found");
//		    }
//
//		    if (!paymentRepository.findById(shipment.getPayment().getPaymentId()).isPresent()) {
//		        throw new ResourceNotFoundException("Payment Not Found");
//		    }
//
//		    }
//		
//
//		List<Shipment> savedShipments = shipmentRepository.saveAll(shipments);
//
//		ResponseStructure<List<Shipment>> response = new ResponseStructure<>();
//		response.setStatusCode(201);
//		response.setMessage("Shipment Saved Successfully");
//		response.setData(savedShipments);
//
     return null;
	}


	    public ResponseEntity<ResponseStructure<List<Shipment>>> findAllShipment() {
		
		List<Shipment> optional = shipmentRepository.findAll();
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("No Shipment found");
		}
		
		ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Shipment found Successfully");
		responseStructure.setData(optional);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);	
	}

		public ResponseEntity<ResponseStructure<Shipment>> findById(Integer id) {
			Optional<Shipment> optional = shipmentRepository.findById(id);
			
			if(optional.isEmpty()) {
				throw new ResourceNotFoundException("Shipment Id Not Found");
			}
			
			ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();
			
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Shipment found Successfully");
			responseStructure.setData(optional.get());
			
			return new ResponseEntity<>(responseStructure,HttpStatus.OK);	
		}

		public ResponseEntity<ResponseStructure<Shipment>> findByTrackingNumber(String trackingNumber) {
			
			Optional<Shipment> optional = shipmentRepository.findByTrackingNumber(trackingNumber);
			
			if(optional.isEmpty()) {
				throw new ResourceNotFoundException("Tracking Number Not Found");
			}
			
			ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();
			
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Shipment found Successfully");
			responseStructure.setData(optional.get());
			
			return new ResponseEntity<>(responseStructure,HttpStatus.OK);	
			
		}


		public ResponseEntity<ResponseStructure<Shipment>> updateShipment(Integer id, ShipmentStatus shipmentStatus) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(Integer shipmentId, Integer deliveryId) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<Shipment>> assignWarehouse(Integer shipmentId, Integer warehouseId) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<String>> deleteById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<List<Shipment>>> findByWarehouseId(Integer warehouseId) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<List<Shipment>>> findByDeliveryAgentId(Integer deliveryId) {
			// TODO Auto-generated method stub
			return null;
		}


		public ResponseEntity<ResponseStructure<List<Shipment>>> findBySourceAndDestination(String source,
				String destination) {
			// TODO Auto-generated method stub
			return null;
		}
		
		


	    
	    
	
	
	
	

}
