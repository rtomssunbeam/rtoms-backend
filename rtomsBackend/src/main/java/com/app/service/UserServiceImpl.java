package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.UserDao;
import com.app.dtos.UserDTO;
import com.app.entities.User;

@Service
public class UserServiceImpl implements UserService  {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ModelMapper mapper;
	
	public void addUser(UserDTO userDTO)
	{
		User user=mapper.map(userDTO, User.class);
		
		userDao.save(user);
	}

}
