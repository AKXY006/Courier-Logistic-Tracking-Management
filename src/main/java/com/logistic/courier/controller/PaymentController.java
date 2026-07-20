package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.entity.Payment;
import com.logistic.courier.entity.PaymentStatus;
import com.logistic.courier.service.PaymentService;
import com.logistic.courier.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
       @Autowired
       private PaymentService paymentService;
       
       
       @PostMapping
       public ResponseEntity<ResponseStructure<List<Payment>>>  saveAllPayment(@Valid @RequestBody List<Payment> payments){
    	   return paymentService.saveAll(payments);
       }
       
       @GetMapping
       public ResponseEntity<ResponseStructure<List<Payment>>>   findAllPayment(){
    	   return paymentService.findAllPayment();
       }
       
       @GetMapping("/{id}")
       public ResponseEntity<ResponseStructure<Payment>>  findById(@PathVariable Integer id){
    	   return paymentService.findById(id);
       }
       
       @PatchMapping("/{id}")
       public ResponseEntity<ResponseStructure<Payment>> updatePayment(@PathVariable Integer id, @RequestBody PaymentStatus paymentStatus){
    	   return paymentService.updateStatus(id, paymentStatus);
    	}
       
       @DeleteMapping("/{id}")
       public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable Integer id){
    	   return paymentService.deleteById(id);
       }
      
 
 
}
