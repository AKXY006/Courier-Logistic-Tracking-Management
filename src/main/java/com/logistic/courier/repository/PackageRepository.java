package com.logistic.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.PackageType;


public interface PackageRepository extends JpaRepository<Package,Integer>{
	
	List<Package> findByPackageType(PackageType packageType);	
}
