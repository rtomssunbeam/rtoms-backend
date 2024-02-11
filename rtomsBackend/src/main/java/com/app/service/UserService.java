package com.app.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dtos.UserDTO;
import com.app.entities.User;

public interface UserService {

	 void addUser(UserDTO user);
}
