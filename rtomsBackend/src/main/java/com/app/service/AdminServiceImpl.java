package com.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.daos.LearnerApplicationDao;
import com.app.daos.OwnerDao;
import com.app.daos.UserDao;
import com.app.dtos.ApplicationTypeDTO;
import com.app.dtos.DocumentDTO;
import com.app.dtos.LearningLicenseApplicationDTO;
import com.app.dtos.OwnerDTO;
import com.app.dtos.PostalAddressDTO;
import com.app.dtos.UserDTO;
import com.app.entities.Document;
import com.app.entities.LearnerLicenseApplication;
import com.app.entities.Owner;
import com.app.entities.PostalAddress;
import com.app.entities.User;
import com.app.enums.DocumentName;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private LearnerApplicationDao learnerApplicationDao;
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<UserDTO> getAllUsersPaginated(int pageNumber) {
		
		Pageable pageable=PageRequest.of(pageNumber, 10);
		List<User>usersList=userDao.findAll(pageable).getContent();
		
		return usersList.stream().map(user->mapper.map(user,UserDTO.class)).collect(Collectors.toList());
	}

	
//Below methos doesnot set postal address into DTO object present in list, even though select query is fired in address table
	@Override
	public List<LearningLicenseApplicationDTO> getAllLearnerLicensePaginated(int pageNumber) {
		
		List<PostalAddress>address=new ArrayList<>();
		Pageable pageable=PageRequest.of(pageNumber, 10);
		
		List<LearnerLicenseApplication>list=learnerApplicationDao.findAll(pageable).getContent(); //gives list of all applications
	
		List<LearningLicenseApplicationDTO>applications=
				list.stream()
				.map(eachApplication->mapper.map(eachApplication,LearningLicenseApplicationDTO.class))
				.collect(Collectors.toList());
		
		 return applications ;
	}


	@Override
	public byte[] getDocuments(Integer applicationId, DocumentName name) {
		
			LearnerLicenseApplication application =  learnerApplicationDao.findById(applicationId).orElseThrow();
			if(name.equals(DocumentName.ADDRESS_PROOF))
			{
				return application.getMyDocument().getAddressProof();
			}
			else if(name.equals(DocumentName.EDUCATIONAL_DOC))
			{
				return application.getMyDocument().getAddressProof();
			}
			else if(name.equals(DocumentName.PROFILE_PICTURE))
			{
				return application.getMyDocument().getAddressProof();
			}
			
			
			
		
		return null;
		
	}


	@Override
	public List<OwnerDTO> getAllOwners() {
		List<Owner>allOwners=ownerDao.findAll();
		
		List<OwnerDTO>owners=allOwners.stream()
//		.filter(owner->!owner.getVehicles().isEmpty())
		.map(owner->mapper.map(owner, OwnerDTO.class))
		.collect(Collectors.toList());
		
		return owners;

		
	}
}

//Below method fetches postal address
//	@Override
//	public List<LearningLicenseApplicationDTO> getAllLearnerLicensePaginated(int pageNumber) {
//	    Pageable pageable = PageRequest.of(pageNumber, 10);
//	    List<LearnerLicenseApplication> list = learnerApplicationDao.findAll(pageable).getContent();
//
//	    List<LearningLicenseApplicationDTO> applications = list.stream()
//	            .map(eachApplication -> {
//	                LearningLicenseApplicationDTO applicationDTO = mapper.map(eachApplication, LearningLicenseApplicationDTO.class);
//	                PostalAddress postalAddress = eachApplication.getPostalAddress();
//	                System.out.println(postalAddress);
//	                if (postalAddress != null) {
//	                    PostalAddressDTO postalAddressDTO = mapper.map(postalAddress, PostalAddressDTO.class);
//	                    System.out.println(postalAddressDTO);
//	                    applicationDTO.setPostalAddressDTO(postalAddressDTO);
//	                    applicationDTO.setUserId(eachApplication.getUser().getId());
//	                }
//	                return applicationDTO;
//	            })
//	            .collect(Collectors.toList());
//
//	    return applications;
//	}
//}

	
