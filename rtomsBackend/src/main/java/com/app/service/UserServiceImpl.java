package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	@Override
	public User authenticate(UserDTO userDto) {
		
		return userDao.findByEmail(mapper.map(userDto, User.class).getEmail());
	}

	@Override
	public List<User> getAllUsers() {
		
		return null;
	}
	

	@Override
	public List<UserDTO> getAllUsersPaginated(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 10);
		List<User>usersList=userDao.findAll(pageable).getContent();
		
		return usersList.stream().map(user->mapper.map(user,UserDTO.class)).collect(Collectors.toList());
	}

	

}
