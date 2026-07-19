package com.logistic.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.TrackingStatus;


public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer>{

    List<TrackingHistory> findByTrackingNumber(String trackingNumber);    

    List<TrackingHistory> findByTrackingStatus(TrackingStatus trackingStatus);

}
