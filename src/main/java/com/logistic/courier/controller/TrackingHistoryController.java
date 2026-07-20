package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.TrackingStatus;
import com.logistic.courier.service.TrackingHistoryService;
import com.logistic.courier.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trackinghistory")
public class TrackingHistoryController {
	
	@Autowired
	private TrackingHistoryService trackingHistoryService;
	
	@PostMapping
	public ResponseEntity< ResponseStructure<List<TrackingHistory>>>  saveAll(@Valid @RequestBody List<TrackingHistory> trackingHistories){
		return trackingHistoryService.saveAllTrackingHistory(trackingHistories);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findAllHistory(){
		return trackingHistoryService.findAllHistory();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<TrackingHistory>> findById(@PathVariable Integer id){
		return trackingHistoryService.findById(id);
	}
	
	@GetMapping("/status/{trackingStatus}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findByStatus(@PathVariable TrackingStatus trackingStatus) {
	    return trackingHistoryService.findByStatus(trackingStatus);
	}
	
	@GetMapping("/tracking/{trackingNumber}")
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findByTrackingNumber(@PathVariable String trackingNumber) {
	    return trackingHistoryService.findByTrackingNumber(trackingNumber);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Integer id) {
	    return trackingHistoryService.deleteById(id);
	}
}
