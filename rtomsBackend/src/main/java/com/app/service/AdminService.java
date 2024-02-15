package com.app.service;

import java.util.List;

import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.UserDTO;

public interface AdminService {

	List<UserDTO> getAllUsersPaginated(int pageNumber);
	List<LearningLicenseApplicationDTO> getAllLearnerLicensePaginated(int pageNumber);

	
	
}
