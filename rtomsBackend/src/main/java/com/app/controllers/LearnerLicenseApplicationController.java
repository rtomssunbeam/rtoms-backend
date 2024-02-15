package com.app.controllers;


import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApiResponse;
import com.app.dtos.DocumentDTO;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.entities.Document;
import com.app.entities.LearnerLicenseApplication;
import com.app.service.LearnerLicenseApplicationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/lernerLicense")
@Slf4j
@Validated
public class LearnerLicenseApplicationController {
	

	private Logger logger=LoggerFactory.getLogger(LearnerLicenseApplicationController.class);
	
	@Autowired // (required = true)
	private LearnerLicenseApplicationService lernerApplicationService;
	
	

	@PostMapping(value="/application",consumes = "multipart/form-data")
	public ResponseEntity<?> llApplicationForm(@RequestParam String learnerLicenseApplicationDto, @RequestParam ArrayList<MultipartFile>files)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(lernerApplicationService.addLernerLicenseApplication(learnerLicenseApplicationDto,files));	
	}
}
	
	

/*
 * 
 @PostMapping(value = "/documents/upload",consumes = "multipart/form-data")
	public void upload(@RequestBody MultipartFile[]files) 
	{
		System.out.println("image controller image recieved");
		Document document=new Document();
		try {
			document.setProfilePhoto(files[0].getBytes());
			document.setAddressProof(files[1].getBytes());
			document.setEducationalDocument(files[2].getBytes());
			System.out.println("successfully parsed");
		} catch (IOException e) {
			System.out.println("error in doc ctrller");
			e.printStackTrace();
		}
	}

} 
 * 
 * 
 * 
 * {
  "firstName": "rajdeep",
  "middleName": "shankar",
  "lastName": "sutar",
  "mobileNumber": "string",
  "userId": 1,
  "postalAddressDTO": {
    "house": "string",
    "street": "string",
    "city": "string",
    "state": "string",
    "country": "string",
    "zipCode": "string"
  },
  "gender": "MALE",
  "bloodGroup": "A_POSITIVE",
  "dateOfBirth": "2000-02-13",
  "rtoOffice": "KOLHAPUR",
  "qualification": "BELOWSSC",
  "applicationTypes": [
    "MOTORCYCLE"
  ]
}*/

