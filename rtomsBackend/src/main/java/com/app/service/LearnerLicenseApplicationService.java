package com.app.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.app.dtos.ApiResponse;

public interface LearnerLicenseApplicationService {

	public ApiResponse addLernerLicenseApplication(String learningApplicationDTO, ArrayList<MultipartFile> files);
//	public ApiResponse addLernerLicenseApplication(String learningApplicationDTO, MultipartFile[] files);

}
