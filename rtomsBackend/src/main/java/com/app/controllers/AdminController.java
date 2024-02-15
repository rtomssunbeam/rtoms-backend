package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.LearnerApplicationDao;
import com.app.dtos.UserDTO;
import com.app.service.LearnerLicenseApplicationService;
import com.app.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LearnerLicenseApplicationService lernerApplicationService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getUsers/peginate")
	public ResponseEntity<?>getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNumber )
	{
		List<UserDTO>usersList=userService.getAllUsersPaginated(pageNumber);
		return ResponseEntity.status(201).body(userService.getAllUsersPaginated(pageNumber));
		
	}
	
	
	
	
}
