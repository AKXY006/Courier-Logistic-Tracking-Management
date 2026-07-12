package com.logistic.courier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Payment;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.PaymentRepository;
import com.logistic.courier.util.ResponseStructure;

import sun.jvm.hotspot.oops.ReturnTypeEntry;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public ResponseEntity<ResponseStructure<List<Payment>>> findAllPayment(){
		List<Payment> payments = paymentRepository.findAll();
		
		if(payments.isEmpty()) {
			throw new ResourceNotFoundException("Payment Record not found");
		}
		
		ResponseStructure<List<Payment>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Payment record successfully received");
		responseStructure.setData(payments);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
	}
	
	
	public ResponseEntity<ResponseStructure<Payment>> findById(Integer paymentId){
		Optional<Payment> optional= paymentRepository.findById(paymentId);
		
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("id not found ");
		}
		
		ResponseStructure<Payment> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Payment record successfully received with id "+paymentId);
		responseStructure.setData(optional.get());
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);

		
	}

}
