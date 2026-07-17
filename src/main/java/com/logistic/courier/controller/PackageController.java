package com.logistic.courier.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.logistic.courier.service.PackageService;

@RestController
public class PackageController {
	
     @Autowired
     private PackageService packageService;
     
     

	

}
