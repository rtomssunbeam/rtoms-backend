package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.controllers.LearnerLicenseApplicationController;
import com.app.daos.ApplicationTypeDao;
import com.app.daos.LearnerApplicationDao;
import com.app.daos.UserDao;
import com.app.dtos.ApiResponse;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.PostalAddressDTO;
import com.app.entities.ApplicationType;
import com.app.entities.Document;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.PostalAddress;
import com.app.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Autowired
	private ObjectMapper objMapper;

	private Logger logger = LoggerFactory.getLogger(LearnerLicenseApplicationController.class);

	@Override
	public ApiResponse addLernerLicenseApplication(String learnerApplicationString, ArrayList<MultipartFile> files)

	/*
	 * Why throws keyword ? Therefore, even though Spring Boot simplifies exception
	 * handling by converting checked exceptions to unchecked ones, it's still
	 * crucial to handle exceptions appropriately to maintain the stability,
	 * reliability, and usability of your application. You should catch and handle
	 * exceptions where they occur or propagate them to higher levels of the
	 * application where they can be handled effectively.
	 */
	{
		LearningLicenseApplicationDTO learnerApplicationDTO = null;
		Document document = null;
		PostalAddressDTO postalAddressDTO = null;
		try {
			document = new Document(files.get(0).getBytes(), files.get(1).getBytes(), files.get(2).getBytes());
			learnerApplicationDTO = objMapper.readValue(learnerApplicationString, LearningLicenseApplicationDTO.class);
		} catch (IOException e) {
			logger.info("Error While Parsing the file");
			e.printStackTrace();
		}

		Integer userId = learnerApplicationDTO.getUserId();
		User user = userDao.findById(userId).orElseThrow();
		
		
		System.out.println(learnerApplicationDTO.getPostalAddressDTO());
		
		
		PostalAddress postalAddress = mapper.map(learnerApplicationDTO.getPostalAddressDTO(), PostalAddress.class);
		
//		System.out.println(learnerApplicationDTO);
//		System.out.println(postalAddressDTO);
//		System.out.println(postalAddress);
		
		LearnerLicenseApplication learnerApp = mapper.map(learnerApplicationDTO, LearnerLicenseApplication.class);
		System.out.println(learnerApp.getApplicationTypes());
		learnerApp.getApplicationTypes().clear();
		learnerApp.setUser(user);
		learnerApp.setMyDocument(document);
		learnerApp.setPostalAddress(postalAddress);
		

		Set<ApplicationType> applicationTypes = new HashSet<>();
		learnerApplicationDTO.getApplicationTypes()
				.forEach(s -> applicationTypes.add(applicationTypeDao.findByApplicationType(s)));

//		applicationTypes.stream().forEach(type->learnerApp.addType(type));

		for (ApplicationType eachType : applicationTypes) {
			System.out.println(eachType);
			learnerApp.addType(eachType);
		}

//		logger.info(learnerApp.getApplicationTypes().toString());
//		logger.info(learnerApp.toString());
		learnerAppDao.save(learnerApp);
		return new ApiResponse("Learning Application submitted successfully...!");
	}

}
