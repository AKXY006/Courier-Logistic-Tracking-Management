package com.logistic.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.TrackingHistory;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer>{

}
