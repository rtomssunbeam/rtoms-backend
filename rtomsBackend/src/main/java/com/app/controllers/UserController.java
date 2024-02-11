package com.app.controllers;

import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dtos.ApiResponse;
import com.app.dtos.UserDTO;
import com.app.entities.User;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
@Validated
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/signUp")
	public ResponseEntity<?>signUp(@RequestBody UserDTO user)
	{
		Logger logger=LoggerFactory.getLogger(UserController.class);
	   	logger.info(user.toString());
		userService.addUser(user);
		ApiResponse resp=new ApiResponse();
		resp.setMsg("user added successfully");
		return ResponseEntity.status(200).body(resp);		
	}
	
	
	

}
