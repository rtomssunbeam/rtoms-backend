package com.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.OwnerDao;
import com.app.dtos.OwnerDTO;
import com.app.entities.Owner;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	
	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private ModelMapper mapper;
	
	public Owner register(OwnerDTO ownerDto)
	{
		
		Owner owner=mapper.map(ownerDto, Owner.class);
		owner.setEntryTime(LocalDateTime.now());
		return ownerDao.save(owner);
		
	}
}
