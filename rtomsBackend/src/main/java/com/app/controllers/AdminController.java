package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.LearnerApplicationDao;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.UserDTO;
import com.app.enums.DocumentName;
import com.app.enums.Status;
import com.app.service.AdminService;
import com.app.service.LearnerLicenseApplicationService;
import com.app.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private LearnerLicenseApplicationService learnerApplicationService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/getUsers")
	public ResponseEntity<?>getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber )
	{
		return ResponseEntity.status(201).body(adminService.getAllUsersPaginated(pageNumber));
		
	}
	
	@GetMapping("/getLearnerApplications")
	public ResponseEntity<?>getAllLearnerApplications(@RequestParam(defaultValue = "0", required = false) int pageNumber )

	{
		return ResponseEntity.status(201).body(adminService.getAllLearnerLicensePaginated(pageNumber));
	}
	
	
	@GetMapping("/getDocuments") //get documents of single application
	public ResponseEntity<?>getAllLearnerApplications(@RequestParam Integer applicationId,DocumentName name)

	{
		return ResponseEntity.status(201).contentType(MediaType.valueOf("image/jpeg")).body(adminService.getDocuments(applicationId,name));
	}
	
	@GetMapping("/getAllOwners") //get documents of single application
	public ResponseEntity<?>getOwners()

	{
		return ResponseEntity.status(201).body(adminService.getAllOwners());
	}
	
	@PutMapping("/learnerApplication/updateStatus")
	public ResponseEntity<?>updateLearnerAppStatus(@RequestParam Integer LearnerAppId,Status status)
	{
		return ResponseEntity.status(200).body(learnerApplicationService.updateStatus(LearnerAppId,status));
	}
	
	
}
