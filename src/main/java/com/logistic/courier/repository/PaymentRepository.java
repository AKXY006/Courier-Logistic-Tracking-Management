package com.logistic.courier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	
	    //1) Get All Payment -> GetMapping -> findAll()

		//2) Get By Id -> GetMapping -> getById()
	
	    //3) update status -> PatchMapping -> updateByPaymentStatus()
//	    Optional<Payment> updateByPaymentStatus(String paymentstatus);
	    
	    //4) Delete by Id ->DeleteMapping -> DeleteById()
}
