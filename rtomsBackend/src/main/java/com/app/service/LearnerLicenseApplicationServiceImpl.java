package com.app.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.ApplicationTypeDao;
import com.app.daos.LearnerApplicationDao;
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
	private LearnerApplicationDao learnerAppDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ApplicationTypeDao applicationTypeDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addLernerLicenseApplication(LearningLicenseApplicationDTO lernerLicenseApplicationDto) {
		
		Integer userId =lernerLicenseApplicationDto.getUserId();
		
		User user = userDao.findById(userId).orElseThrow();
		
		LearnerLicenseApplication lernerApp = mapper.map(lernerLicenseApplicationDto, LearnerLicenseApplication.class);
		
		System.out.println(lernerApp.getApplicationTypes());
		
		lernerApp.getApplicationTypes().clear();
		
		System.out.println(lernerApp);
		
		Set<ApplicationType> applicationTypes=new HashSet<>();
		
		lernerLicenseApplicationDto.getApplicationTypes().forEach(s->applicationTypes.add(applicationTypeDao.findByApplicationType(s)));
		
		System.out.println(applicationTypes);

		lernerApp.setUser(user);
		
		System.out.println(lernerApp.getApplicationTypes());
		
		for(ApplicationType eachType:applicationTypes)
		{
			lernerApp.addType(eachType);
		}
		
		System.out.println(lernerApp.getApplicationTypes());
		
		System.out.println(lernerApp);
		
		learnerAppDao.save(lernerApp);
		
		return new ApiResponse("application submitted successfully");
	}

}
