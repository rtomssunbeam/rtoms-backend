package com.app.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.daos.ApplicationTypeDao;
import com.app.daos.LearnerApplicationDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.DocumentDTO;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.entities.ApplicationType;
import com.app.entities.Document;
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
	public ApiResponse addLernerLicenseApplication(LearningLicenseApplicationDTO learnerLicenseApplicationDto) 
/*	Why throws keyword ?
 * Therefore, even though Spring Boot simplifies exception handling by converting checked exceptions to unchecked ones,
 *  it's still crucial to handle exceptions appropriately to maintain the stability, reliability, and usability of your application.
 *   You should catch and handle exceptions where they occur or propagate them to higher levels of the 
 *   application where they can be handled effectively.
 */
	{
		
		Integer userId =learnerLicenseApplicationDto.getUserId();
		
		User user = userDao.findById(userId).orElseThrow();
		
		LearnerLicenseApplication learnerApp = mapper.map(learnerLicenseApplicationDto, LearnerLicenseApplication.class);
		
//		System.out.println(learnerApp.getApplicationTypes());
		
		learnerApp.getApplicationTypes().clear();
		
//		System.out.println(learnerApp);
		
		Set<ApplicationType> applicationTypes=new HashSet<>();
		
		learnerLicenseApplicationDto.getApplicationTypes().forEach(s->applicationTypes.add(applicationTypeDao.findByApplicationType(s)));
		
//		System.out.println(applicationTypes);

		learnerApp.setUser(user);
		
//		System.out.println(learnerApp.getApplicationTypes());
		
		for(ApplicationType eachType:applicationTypes)
		{
			learnerApp.addType(eachType);
		}
		
//		System.out.println(learnerApp.getApplicationTypes());
		
//		System.out.println(learnerApp);
		
		learnerAppDao.save(learnerApp);
		
		return new ApiResponse("application submitted successfully");
	}

}
