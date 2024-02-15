package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.PermanentLicenseApplicationDTO;
import com.app.service.PermanentLicenseApplicationService;

@RestController
@RequestMapping("/permanentLicense")
@Validated
public class PermanentLicenseApplicationController {

	@Autowired // (required = true)
	private PermanentLicenseApplicationService permanentApplicationService;
	
	@PostMapping(value="/application")
	public ResponseEntity<?> plApplicationForm(@Valid @RequestBody PermanentLicenseApplicationDTO permanentLicenseApplicationDTO)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(permanentApplicationService.addPermanentLicenseApplicaation(permanentLicenseApplicationDTO));
	}
	
}
