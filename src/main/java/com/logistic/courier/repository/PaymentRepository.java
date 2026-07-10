package com.logistic.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
