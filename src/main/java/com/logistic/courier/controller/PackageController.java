package com.logistic.courier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.logistic.courier.entity.Package;
import com.logistic.courier.entity.PackageType;
import com.logistic.courier.service.PackagesService;
import com.logistic.courier.util.ResponseStructure;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackagesService packageService;

    @PostMapping
    public ResponseEntity<ResponseStructure<List<Package>>> saveAllPackage(@RequestBody List<Package> packages) {
        return packageService.saveAll(packages);
    }
    
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Package>>>  findAll(){
    	return packageService.findAll();
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseStructure<Package>>  findById(@PathVariable Integer id){
    	return packageService.findById(id);
    }
    
    @GetMapping("/packagetype/{packageType}")
    public ResponseEntity<ResponseStructure<List<Package>>> findByPackageType(@PathVariable PackageType packageType){
    	return packageService.findByType(packageType);
    }
}