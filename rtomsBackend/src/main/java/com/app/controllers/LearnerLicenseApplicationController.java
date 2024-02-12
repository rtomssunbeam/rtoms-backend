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
@RequestMapping("/lernerLicense")
@Slf4j
@Validated
public class LearnerLicenseApplicationController {
	
	@Autowired // (required = true)
	LearnerLicenseApplicationService llApplicationService;

	public LearnerLicenseApplicationController() {
		System.out.println("in ctor of " + getClass());
	}
	
	
	@PostMapping("/application")
	public ResponseEntity<?> llApplicationForm(@RequestBody LearningLicenseApplicationDTO lernerLicenseApplicationDto)
	{
		Logger logger=LoggerFactory.getLogger(LearnerLicenseApplicationController.class);
	   	logger.info(lernerLicenseApplicationDto.toString());
	   

		return ResponseEntity.status(HttpStatus.CREATED).body(llApplicationService.addLernerLicenseApplication(lernerLicenseApplicationDto));	
	}

}
