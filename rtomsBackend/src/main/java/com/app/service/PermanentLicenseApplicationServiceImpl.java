package com.app.service;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ApplicationDoesNotExistException;
import com.app.daos.LearnerApplicationDao;
import com.app.daos.PermanentApplicationDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.PermanentLicenseApplicationDTO;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.PermanentLicenseApplication;
import com.app.entities.User;
import com.app.enums.Status;

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

	@Override
	public ApiResponse updateStatus(@Valid Integer permanentAppId, Status status) {
		PermanentLicenseApplication permanentApp=permanentApplicationDao.findById(permanentAppId).orElseThrow(()->new ApplicationDoesNotExistException("Permanent Application does not exist"));
		permanentApp.setStatus(status);
		if(status.equals(Status.APPROVED))
		{
			return new ApiResponse("Permanent License Aprooved");
		}
		else if(status.equals(Status.REJECTED))
		{
			return new ApiResponse("Permanent License Rejected");
		}
		return new ApiResponse("failed");
	}

}
