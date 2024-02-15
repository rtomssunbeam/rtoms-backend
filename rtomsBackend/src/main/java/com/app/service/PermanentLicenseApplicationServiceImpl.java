package com.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.daos.LearnerApplicationDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.PermanentLicenseApplicationDTO;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.PermanentLicenseApplication;
import com.app.entities.User;

@Service
@Transactional
public class PermanentLicenseApplicationServiceImpl implements PermanentLicenseApplicationService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LearnerApplicationDao learnerAppDao;
	
	@Autowired
	private PermanentApplicationDao permanentApplicationDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse addPermanentLicenseApplicaation(PermanentLicenseApplicationDTO permanentLicenseApplicationDTO) {
		Integer userId = permanentLicenseApplicationDTO.getUserId();
		User user = userDao.findById(userId).orElseThrow();

		Integer learnerApplicationId = permanentLicenseApplicationDTO.getLearnerApplicationId();
		LearnerLicenseApplication learnerLicenseApplication = learnerAppDao.findById(learnerApplicationId).orElseThrow();
		
		PermanentLicenseApplication permanentApp = mapper.map(permanentLicenseApplicationDTO, PermanentLicenseApplication.class);
		
		permanentApp.setUser(user);
		permanentApp.setLearnerApplication(learnerLicenseApplication);
		
		permanentApplicationDao.save(permanentApp);

		return new ApiResponse("Permanent Application submitted successfully...!");
	}

}
