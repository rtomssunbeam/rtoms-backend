package com.app.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApiResponse;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.enums.Status;

public interface LearnerLicenseApplicationService {

	public ApiResponse addLernerLicenseApplication(String learningApplicationDTO, ArrayList<MultipartFile> files);
//	public ApiResponse addLernerLicenseApplication(String learningApplicationDTO, MultipartFile[] files);

	public ApiResponse updateStatus(Integer learnerAppId,Status status);

	public LearningLicenseApplicationDTO getApplication(Integer appId);

}
