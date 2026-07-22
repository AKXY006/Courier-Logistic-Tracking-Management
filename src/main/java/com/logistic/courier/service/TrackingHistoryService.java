package com.logistic.courier.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.TrackingHistory;
import com.logistic.courier.entity.TrackingStatus;
import com.logistic.courier.exception.InvalidInputException;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.TrackingHistoryRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class TrackingHistoryService {
	
	@Autowired
	private TrackingHistoryRepository trackingHistoryRepository;
	
	public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findAllHistory(){
		
		List<TrackingHistory> trackingHistories = trackingHistoryRepository.findAll();
		if(trackingHistories.isEmpty()) {
			throw new ResourceNotFoundException("No Tracking History Found");
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
			throw new ResourceNotFoundException("Tracking history not found");
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



		public ResponseEntity<ResponseStructure<List<TrackingHistory>>> findByTrackingNumber(String trackingNumber) {

		    List<TrackingHistory> trackingHistories = trackingHistoryRepository.findByShipmentTrackingNumber(trackingNumber);

		    if (trackingHistories.isEmpty()) {
		        throw new ResourceNotFoundException("No Tracking History Found With Tracking Number : " + trackingNumber);
		    }

		    ResponseStructure<List<TrackingHistory>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Tracking Histories Retrieved Successfully");
		    responseStructure.setData(trackingHistories);

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}


		public ResponseEntity<ResponseStructure<String>> deleteById(Integer id) {

		    Optional<TrackingHistory> optional =  trackingHistoryRepository.findById(id);

		    if (optional.isEmpty()) {
		        throw new ResourceNotFoundException("Tracking History Not Found With Id : " + id);
		    }

		    trackingHistoryRepository.deleteById(id);

		    ResponseStructure<String> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.OK.value());
		    responseStructure.setMessage("Tracking History Deleted Successfully");
		    responseStructure.setData("Success");

		    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}
	    
	    
	
}
