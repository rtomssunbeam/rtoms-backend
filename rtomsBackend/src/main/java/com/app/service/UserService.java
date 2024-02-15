package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.dtos.UserDTO;
import com.app.entities.User;

public interface UserService {

	 void addUser(UserDTO user);

	User authenticate(UserDTO userDto);

	List<User> getAllUsers();

	List<UserDTO> getAllUsersPaginated(int pageNumber);
}

//JavaRT



