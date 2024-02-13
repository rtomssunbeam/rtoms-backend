package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.OwnerDTO;
import com.app.service.OwnerService;
import com.app.service.VehicleService;

@RestController
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	
	@PostMapping("/register")
	public ResponseEntity<?>register(@RequestBody OwnerDTO ownerDto)
	{
		return ResponseEntity.status(200).body(ownerService.register(ownerDto));
		
	}
}
