package com.logistic.courier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Package;

public interface PackageRepository extends JpaRepository<Package,Integer>{

}
