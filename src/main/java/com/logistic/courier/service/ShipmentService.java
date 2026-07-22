package com.logistic.courier.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Customer;
import com.logistic.courier.entity.DeliveryAgent;
import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.Payment;
import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.ShipmentStatus;
import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.Warehouse;

import com.logistic.courier.exception.ActiveStatusException;
import com.logistic.courier.exception.DuplicateResourceException;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.ResourceNotFoundException;

import com.logistic.courier.repository.CustomerRepository;
import com.logistic.courier.repository.DeliveryAgentRepository;
import com.logistic.courier.repository.ShipmentRepository;
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
	private WarehouseRepository warehouseRepository;
	
	public ResponseEntity<ResponseStructure<Shipment>> saveShipment(Shipment shipment) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findByTrackingNumber(shipment.getTrackingNumber());

	    if (optionalShipment.isPresent()) {
	        throw new DuplicateResourceException("Tracking Number Already Exists.");
	    }

	    if (shipment.getCustomer() == null) {
	        throw new InvalidInputException("Customer is mandatory.");
	    }

	    if (shipment.getCustomer().getCustomerId() == null) {
	        throw new InvalidInputException("Customer Id is mandatory.");
	    }

	    Optional<Customer> optionalCustomer =
	            customerRepository.findById(shipment.getCustomer().getCustomerId());

	    if (!optionalCustomer.isPresent()) {
	        throw new ResourceNotFoundException("Customer Not Found.");
	    }

	    if (shipment.getWarehouse() == null) {
	        throw new InvalidInputException("Warehouse is mandatory.");
	    }

	    if (shipment.getWarehouse().getWarehouseId() == null) {
	        throw new InvalidInputException("Warehouse Id is mandatory.");
	    }

	    Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(shipment.getWarehouse().getWarehouseId());

	    if (!optionalWarehouse.isPresent()) {
	        throw new ResourceNotFoundException("Warehouse Not Found.");
	    }

	    if (shipment.getDeliveryAgent() == null) {
	        throw new InvalidInputException("Delivery Agent is mandatory.");
	    }

	    if (shipment.getDeliveryAgent().getDeliveryId() == null) {
	        throw new InvalidInputException("Delivery Agent Id is mandatory.");
	    }

	    Optional<DeliveryAgent> optionalAgent = deliveryAgentRepository.findById(shipment.getDeliveryAgent().getDeliveryId());

	    if (!optionalAgent.isPresent()) {
	        throw new ResourceNotFoundException("Delivery Agent Not Found.");
	    }

	    DeliveryAgent deliveryAgent = optionalAgent.get();

	    if (!deliveryAgent.getAvailabilityStatus()) {
	        throw new ActiveStatusException("Delivery Agent is not available.");
	    }

	    if (shipment.getPackageEntity() == null) {
	        throw new InvalidInputException("Package Details are mandatory.");
	    }

	    if (shipment.getPackageEntity().getPackageType() == null) {
	        throw new InvalidInputException("Package Type is mandatory.");
	    }

	    if (shipment.getPackageEntity().getFragile() == null) {
	        throw new InvalidInputException("Fragile field is mandatory.");
	    }

	    if (shipment.getPackageEntity().getDimension() == null) {
	        throw new InvalidInputException("Dimension is mandatory.");
	    }

	    if (shipment.getPayment() == null) {
	        throw new InvalidInputException("Payment Details are mandatory.");
	    }

	    if (shipment.getPayment().getPaymentType() == null) {
	        throw new InvalidInputException("Payment Type is mandatory.");
	    }

	    if (shipment.getPayment().getPaymentStatus() == null) {
	        throw new InvalidInputException("Payment Status is mandatory.");
	    }

	    if (shipment.getTrackingHistory() == null || shipment.getTrackingHistory().isEmpty()) {
	        throw new InvalidInputException("At least one Tracking History is required.");
	    }

	    double amount = 0;

	    if (shipment.getWeight() <= 5) {
	        amount = 200;
	    } else if (shipment.getWeight() <= 10) {
	        amount = 400;
	    } else if (shipment.getWeight() <= 20) {
	        amount = 700;
	    } else {
	        amount = shipment.getWeight() * 50;
	    }

	    if (shipment.getPackageEntity().getFragile()) {
	        amount += 100;
	    }

	    shipment.getPayment().setAmount(amount);

	    shipment.setCustomer(optionalCustomer.get());
	    shipment.setWarehouse(optionalWarehouse.get());
	    shipment.setDeliveryAgent(deliveryAgent);

	    Payment payment = shipment.getPayment();
	    payment.setShipment(shipment);

	    Package packageEntity = shipment.getPackageEntity();
	    packageEntity.setShipment(shipment);

	    List<TrackingHistory> trackingHistoryList = shipment.getTrackingHistory();

	    for (int i = 0; i < trackingHistoryList.size(); i++) {
	        TrackingHistory trackingHistory = trackingHistoryList.get(i);
	        trackingHistory.setShipment(shipment);
	    }

	    Shipment savedShipment = shipmentRepository.save(shipment);

	    ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.CREATED.value());
	    responseStructure.setMessage("Shipment Created Successfully.");
	    responseStructure.setData(savedShipment);

	    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findAllShipment() {
		List<Shipment> allShipment = shipmentRepository.findAll();
		
		if(allShipment.isEmpty()) {
			throw new ResourceNotFoundException("Shipment Not Exist");
		}
		
		ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Shipment found successfully");
		responseStructure.setData(allShipment);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<Shipment>> findById(Integer id) {
		
		Optional<Shipment> optional = shipmentRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Shipment id not found");
		}
		
        ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Shipment found successfully");
		responseStructure.setData(optional.get());
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Shipment>> findByTrackingNumber(String trackingNumber) {
       Optional<Shipment> optional = shipmentRepository.findByTrackingNumber(trackingNumber);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Shipment Tracking number not found");
		}
		
        ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Shipment found successfully");
		responseStructure.setData(optional.get());
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Shipment>> updateShipment(Integer id, ShipmentStatus shipmentStatus) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(id);

	    if (optionalShipment.isEmpty()) {
	        throw new ResourceNotFoundException("Shipment Not Found.");
	    }

	    if (shipmentStatus == null) {
	        throw new InvalidInputException("Shipment Status is mandatory.");
	    }

	    Shipment shipment = optionalShipment.get();

	    if (shipment.getStatus() == shipmentStatus) {
	        throw new DuplicateResourceException("Shipment is already in " + shipmentStatus + " status.");
	    }

	    shipment.setStatus(shipmentStatus);

	    Shipment updatedShipment = shipmentRepository.save(shipment);

	    ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Shipment Status Updated Successfully.");
	    responseStructure.setData(updatedShipment);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Shipment>> assignDeliveryAgent(Integer shipmentId, Integer deliveryId) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(shipmentId);

	    if (optionalShipment.isEmpty()) {
	        throw new ResourceNotFoundException("Shipment Not Found.");
	    }

	    Optional<DeliveryAgent> optionalDeliveryAgent = deliveryAgentRepository.findById(deliveryId);

	    if (optionalDeliveryAgent.isEmpty()) {
	        throw new ResourceNotFoundException("Delivery Agent Not Found.");
	    }

	    Shipment shipment = optionalShipment.get();
	    DeliveryAgent deliveryAgent = optionalDeliveryAgent.get();

	    if (!deliveryAgent.getAvailabilityStatus()) {
	        throw new ActiveStatusException("Delivery Agent is not available.");
	    }

	    shipment.setDeliveryAgent(deliveryAgent);

	    Shipment updatedShipment = shipmentRepository.save(shipment);

	    ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Delivery Agent Assigned Successfully.");
	    responseStructure.setData(updatedShipment);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Shipment>> assignWarehouse(Integer shipmentId, Integer warehouseId) {

	    Optional<Shipment> optionalShipment = shipmentRepository.findById(shipmentId);

	    if (optionalShipment.isEmpty()) {
	        throw new ResourceNotFoundException("Shipment Not Found.");
	    }

	    Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);

	    if (optionalWarehouse.isEmpty()) {
	        throw new ResourceNotFoundException("Warehouse Not Found.");
	    }

	    Shipment shipment = optionalShipment.get();
	    Warehouse warehouse = optionalWarehouse.get();

	    if (shipment.getWarehouse() != null
	            && shipment.getWarehouse().getWarehouseId().equals(warehouseId)) {
	        throw new DuplicateResourceException("Warehouse is already assigned to this shipment.");
	    }

	    shipment.setWarehouse(warehouse);

	    Shipment updatedShipment = shipmentRepository.save(shipment);

	    ResponseStructure<Shipment> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Warehouse Assigned Successfully.");
	    responseStructure.setData(updatedShipment);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(Integer id) {
		
           Optional<Shipment> optional = shipmentRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Shipment id not found");
		}
		
		   shipmentRepository.deleteById(id);
		   
        ResponseStructure<String> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Shipment deleted successfully");
		responseStructure.setData("success");
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
      
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findCustomerById(Integer customerId) {
		 List<Shipment> shipmentList = shipmentRepository.findByCustomerCustomerId(customerId);

		    if (shipmentList.isEmpty()) {
		        throw new ResourceNotFoundException("No Shipment Found For Customer Id : " + customerId);
		    }

		    ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Shipments Found Successfully.");
		    responseStructure.setData(shipmentList);

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findByWarehouseId(Integer warehouseId) {
		List<Shipment> shipments = shipmentRepository.findByWarehouseWarehouseId(warehouseId);
		
		if(shipments.isEmpty()) {
			throw new ResourceNotFoundException("no shipment found for warehouse id");
		}
		
		 ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Shipments Found Successfully.");
		    responseStructure.setData(shipments);

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		
		
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findByDeliveryAgentId(Integer deliveryId) {
		List<Shipment> shipments = shipmentRepository.findByDeliveryAgentDeliveryId(deliveryId);
		
		if(shipments.isEmpty()) {
			throw new ResourceNotFoundException("no shipment found for delivery agent id");
		}
		
		 ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Shipments Found Successfully.");
		    responseStructure.setData(shipments);

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findByStatus(ShipmentStatus status) {
	 List<Shipment> shipments = shipmentRepository.findByStatus(status);
	 
	 if(shipments.isEmpty()) {
			throw new ResourceNotFoundException("no shipment found for status "+ status);
		}
		
		 ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Shipments Found Successfully.");
		    responseStructure.setData(shipments);

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findBySourceAndDestination(String source, String destination) {

	    List<Shipment> shipments = shipmentRepository.findBySourceAndDestination(source, destination);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException("No Shipment Found From " + source + " To " + destination);
	    }

	    ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Shipments Found Successfully.");
	    responseStructure.setData(shipments);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Shipment>>> findByDeliveryDate(LocalDate deliveryDate) {

	    List<Shipment> shipments = shipmentRepository.findByDeliveryDate(deliveryDate);

	    if (shipments.isEmpty()) {
	        throw new ResourceNotFoundException("No Shipment Found For Delivery Date : " + deliveryDate);
	    }

	    ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Shipments Found Successfully.");
	    responseStructure.setData(shipments);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<List<Shipment>>> findByPagination(int pageNumber, int pageSize) {

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    Page<Shipment> page = shipmentRepository.findAll(pageable);

	    if (page.isEmpty()) {
	        throw new ResourceNotFoundException("No Shipment Found.");
	    }

	    ResponseStructure<List<Shipment>> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Shipments Retrieved Successfully.");
	    responseStructure.setData(page.getContent());

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
}