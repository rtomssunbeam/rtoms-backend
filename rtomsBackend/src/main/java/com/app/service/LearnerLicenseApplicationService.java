package com.app.service;


import java.io.IOException;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApiResponse;
import com.app.dtos.DocumentDTO;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.entities.LearnerLicenseApplication;

public interface LearnerLicenseApplicationService {

	public ApiResponse addLernerLicenseApplication(String learningApplicationDTO,ArrayList<MultipartFile>files);


}
