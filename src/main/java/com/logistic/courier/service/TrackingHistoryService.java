package com.logistic.courier.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.TrackingStatus;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.TrackingHistoryRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class TrackingHistoryService {
	
	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> saveAllTrackingHistory(List<TrackingHistory> trackingHistories) {
		List<TrackingHistory> saveHistories = trackingHistoryRepository.saveAll(trackingHistories);
		
		ResponseStructure<List<TrackingHistory>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("TrackingHistory saved Successfully");
		responseStructure.setData(saveHistories);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.CREATED);
	}
	
	
	
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findAllHistory(){
		
		List<TrackingHistory> trackingHistories = trackingHistoryRepository.findAll();
		if(trackingHistories.isEmpty()) {
			throw new ResourceNotFoundException("Tracking history empty");
		}
		
		ResponseStructure<List<TrackingHistory>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("tracking history found successfully");
		responseStructure.setData(trackingHistories);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}






	
	    public ResponseEntity<ResponseStructure<TrackingHistory>> findById(Integer trackingHistoryId){
		Optional<TrackingHistory> trackingHistory = trackingHistoryRepository.findById(trackingHistoryId);
		if(trackingHistory.isEmpty()) {
			throw new ResourceNotFoundException("Tracking history empty");
		}
		
		ResponseStructure<TrackingHistory> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("tracking history found successfully");
		responseStructure.setData(trackingHistory.get());
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
	}
	    
	   



		public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findByStatus(TrackingStatus trackingStatus) {
			List<TrackingHistory> trackingHistory = trackingHistoryRepository.findByTrackingStatus(trackingStatus);
			if(trackingHistory.isEmpty()) {
				throw new ResourceNotFoundException("Tracking status not found");
			}
			
			ResponseStructure<List<TrackingHistory>> responseStructure = new ResponseStructure<>();
			
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("tracking status found successfully");
			responseStructure.setData(trackingHistory);
			
			return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		}
	    
	    
	
}
