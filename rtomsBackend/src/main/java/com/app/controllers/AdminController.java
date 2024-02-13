package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.daos.LearnerApplicationDao;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LearnerApplicationDao lernerApplicationDao;
}
