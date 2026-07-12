package com.logistic.courier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.PackageType;


public interface PackageRepository extends JpaRepository<Package,Integer>{
	
	//1) Get All Package -> GetMapping -> findAll()

	//2) Get By Id -> GetMapping -> getById()
	
	//3) Get By Type -> GetMapping -> getByType
	List<Package> findByPackageType(PackageType packageType);

	
}
