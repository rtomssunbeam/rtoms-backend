package com.app.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.ApplicationTypeDao;
import com.app.daos.LLADao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.entities.ApplicationType;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.User;

@Service
@Transactional
public class LearnerLicenseApplicationServiceImpl implements LearnerLicenseApplicationService {

	@Autowired
	private LLADao llaDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationTypeDao applicationTypeDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addllApplication(LearningLicenseApplicationDTO llaDTO) {
		Integer userId =llaDTO.getUserId();
		User user = userDao.findById(userId).orElseThrow();
		
		Set<ApplicationType> applicationTypes1=new HashSet<>();
		for (String type   : llaDTO.getApplicationTypes()) {
			System.out.println(type);
			applicationTypes1.add(applicationTypeDao.findByApplicationType(type));
			
		}
		System.out.println(applicationTypes1);
		
		
		LearnerLicenseApplication lla = mapper.map(llaDTO, LearnerLicenseApplication.class);
		lla.setUser(user);
		lla.addTypes(applicationTypes1);
		llaDao.save(lla);
		
		return new ApiResponse("application submitted successfully");
	}

}
