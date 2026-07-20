package com.logistic.courier.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Shipment;
import com.logistic.courier.entity.ShipmentStatus;


public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{


	   Optional<Shipment> findByTrackingNumber(String trackingNumber);
	   
	   List<Shipment> findByCustomerCustomerId(Integer customerId);

	    List<Shipment> findByWarehouseWarehouseId(Integer warehouseId);

	    List<Shipment> findByDeliveryAgentDeliveryId(Integer deliveryId);

	    List<Shipment> findByStatus(ShipmentStatus status);

	    List<Shipment> findBySourceAndDestination(String source, String destination);

	    List<Shipment> findByDeliveryDate(LocalDate deliveryDate);
	  
	   
}
