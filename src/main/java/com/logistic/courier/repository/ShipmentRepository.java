package com.logistic.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer>{

}
