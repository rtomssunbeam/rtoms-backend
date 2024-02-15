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
	
	public UserController() {
		System.out.println("in ctor of " + getClass());
	}

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
	
	@PostMapping("/signIn")
	public ResponseEntity<?>logIn(@RequestBody UserDTO userDto)
	{
		Logger logger=LoggerFactory.getLogger(UserController.class);
	   	logger.info(userDto.toString());
		User user=userService.authenticate(userDto);
		ApiResponse resp=new ApiResponse();
		
		if(user!=null && user.getRole().toString().equals("USER"))
		{
			if(user.getPassword().equals(userDto.getPassword())) {
				resp.setMsg("user logged in successfully");	
				return ResponseEntity.status(200).body(resp);
			}
			else
			{
				resp.setMsg("incorrect password!");
				return ResponseEntity.status(200).body(resp);			
			}
			
			
		}
		
		else if(user!=null && !user.getRole().toString().equals("USER"))
		{
			resp.setMsg("invalid login!");
			return ResponseEntity.status(401).body(resp);
		}
		
		else
		{
			resp.setMsg("user not found, please signup!");
			return ResponseEntity.status(401).body(resp);
		}
		
		
		
				
	}
	
	
	

}
