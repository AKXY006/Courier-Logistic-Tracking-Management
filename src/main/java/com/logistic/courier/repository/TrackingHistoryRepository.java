package com.logistic.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.TrackingStatus;


public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer>{
	
	//1) Get All TrackingHistory -> GetMapping -> findAll()
	
	//2) Get By Id -> GetMapping -> getById()
	
	//3) Get By TrackingNumber ->GetMapping -> getByTrackingNumber()
    //List<TrackingHistory> findByTrackingNumber(String trackingNumber);    
	
	//4) Get By TrackingStatus ->GetMapping -> getByTrackingStatus()
    //List<TrackingHistory> findByTrackingStatus(TrackingStatus trackingStatus);

}
