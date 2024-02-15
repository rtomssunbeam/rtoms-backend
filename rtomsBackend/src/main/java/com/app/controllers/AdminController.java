package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.LearnerApplicationDao;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.UserDTO;
import com.app.service.AdminService;
import com.app.service.LearnerLicenseApplicationService;
import com.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LearnerLicenseApplicationService lernerApplicationService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<?>getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber )
	{
		List<UserDTO>usersList=adminService.getAllUsersPaginated(pageNumber);
		return ResponseEntity.status(201).body(adminService.getAllUsersPaginated(pageNumber));
		
	}
	
	@GetMapping("/getLearnerApplications")
	public ResponseEntity<?>getAllLearnerApplications(@RequestParam(defaultValue = "0", required = false) int pageNumber )
	{
		List<LearningLicenseApplicationDTO>usersList=adminService.getAllLearnerLicensePaginated(pageNumber);
		return ResponseEntity.status(201).body(adminService.getAllUsersPaginated(pageNumber));
	}
	
	
	
	
}
