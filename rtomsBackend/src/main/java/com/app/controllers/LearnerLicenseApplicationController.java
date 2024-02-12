package com.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApiResponse;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.service.LearnerLicenseApplicationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/llapplication")
@Slf4j
@Validated
public class LearnerLicenseApplicationController {
	
	@Autowired // (required = true)
	LearnerLicenseApplicationService llApplicationService;

	public LearnerLicenseApplicationController() {
		System.out.println("in ctor of " + getClass());
	}
	
	
	// REST API end point
	// URL : http://localhost:8080/llapplication/
	// Method : POST
	// resp : sc 201
	@PostMapping("/llapplicationform")
	public ResponseEntity<?> llApplicationForm(@RequestBody LearningLicenseApplicationDTO llaDTO)
	{
		Logger logger=LoggerFactory.getLogger(LearnerLicenseApplicationController.class);
	   	logger.info(llaDTO.toString());
	   
//		ApiResponse resp=new ApiResponse();
//		resp.setMsg("application submitted successfully");
		return ResponseEntity.status(HttpStatus.CREATED).body(llApplicationService.addllApplication(llaDTO));	
	}

}
