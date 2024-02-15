package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.LearnerApplicationDao;
import com.app.daos.UserDao;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.UserDTO;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.User;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LearnerApplicationDao learnerApplicationDao;
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<UserDTO> getAllUsersPaginated(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 10);
		List<User>usersList=userDao.findAll(pageable).getContent();
		
		return usersList.stream().map(user->mapper.map(user,UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<LearningLicenseApplicationDTO> getAllLearnerLicensePaginated(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 10);
		
		List<LearnerLicenseApplication>list=learnerApplicationDao.findAll(pageable).getContent();
		
		list.forEach(eachApplication->System.out.println(eachApplication.getPostalAddress()));
		
		
		
		
		 return list.stream().map(user->mapper.map(user,LearningLicenseApplicationDTO.class)).collect(Collectors.toList());
	}
}
