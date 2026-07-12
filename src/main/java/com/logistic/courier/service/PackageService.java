package com.logistic.courier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.logistic.courier.entity.Package;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.PackageRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class PackageService {
	
	@Autowired
	private PackageRepository packageRepository;
	
	public ResponseEntity<ResponseStructure<List<Package>>> findAllPackage(){
		List<Package> packages = packageRepository.findAll();
		
		if(packages.isEmpty()) {
			throw new ResourceNotFoundException("no package found");
		}
		
		ResponseStructure<List<Package>> responseStructure = new ResponseStructure<>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("package found successfully");
		responseStructure.setData(packages);
		
		return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		
		
	}

}
