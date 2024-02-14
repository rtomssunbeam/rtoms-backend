package com.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.VehicleDTO;
import com.app.service.VehicleService;



@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;
	
	@PostMapping("/registerVehicle")
	public ResponseEntity<?>registerVehicle(@RequestBody VehicleDTO vehicleDto )
	{
		Logger logger=LoggerFactory.getLogger(VehicleController.class);
	   	logger.info(vehicleDto.toString());
	   	
		return ResponseEntity.status(200).body(vehicleService.registerVehicle(vehicleDto));
		
	}
	

}
