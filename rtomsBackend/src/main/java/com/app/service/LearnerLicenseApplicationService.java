package com.app.service;

import com.app.dtos.ApiResponse;
import com.app.dtos.LearningLicenseApplicationDTO;

public interface LearnerLicenseApplicationService {

	public ApiResponse addllApplication(LearningLicenseApplicationDTO llaDTO);

}
