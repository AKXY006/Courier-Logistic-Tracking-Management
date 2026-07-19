package com.logistic.courier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.PackageType;
import com.logistic.courier.exception.ResourceNotFoundException;
import com.logistic.courier.repository.PackageRepository;
import com.logistic.courier.util.ResponseStructure;

@Service
public class PackagesService {
	
	@Autowired
	private PackageRepository packageRepository;
	
	public ResponseEntity<ResponseStructure<List<Package>>> saveAll(List<Package> packages){
		List<Package> savedPackages = packageRepository.saveAll(packages);
		
		 ResponseStructure<List<Package>> responseStructure = new ResponseStructure<>();

		    responseStructure.setStatusCode(HttpStatus.CREATED.value());
		    responseStructure.setMessage("Packages saved successfully");
		    responseStructure.setData(savedPackages);

		    return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Package>>> findAll() {

	    List<Package> packages = packageRepository.findAll();

	    if (packages.isEmpty()) {
	        throw new ResourceNotFoundException("Package not found");
	    }

	    ResponseStructure<List<Package>> responseStructure = new ResponseStructure<>();

	    responseStructure.setStatusCode(HttpStatus.OK.value());
	    responseStructure.setMessage("Packages found successfully");
	    responseStructure.setData(packages);

	    return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<Package>> findById(Integer packageId){
	Optional<Package> optional = packageRepository.findById(packageId);
	if(optional.isEmpty()) {
		throw new ResourceNotFoundException("Id No Found");
	}
	
	ResponseStructure<Package> responseStructure = new ResponseStructure<>();
	
	responseStructure.setStatusCode(HttpStatus.OK.value());
	responseStructure.setMessage("Id Found Successfully");
	responseStructure.setData(optional.get());
	
	return new ResponseEntity<>(responseStructure,HttpStatus.OK);
}
	
	public ResponseEntity<ResponseStructure<List<Package>>> findByType(PackageType packageType){
	List<Package> packages = packageRepository.findByPackageType(packageType);
	
	if(packages.isEmpty()){
		throw new ResourceNotFoundException("Package not found");
	}
	
	ResponseStructure<List<Package>> responseStructure = new ResponseStructure<>();
	
	responseStructure.setStatusCode(HttpStatus.OK.value());
	responseStructure.setMessage("package found successfully");
	responseStructure.setData(packages);
	
	return new ResponseEntity<>(responseStructure,HttpStatus.OK);
}


	
}