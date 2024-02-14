package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.daos.OwnerDao;
import com.app.daos.VehicleDao;
import com.app.dtos.VehicleDTO;

import com.app.entities.*;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;
	
	@Autowired
	private OwnerDao ownerDao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public VehicleDTO registerVehicle(VehicleDTO vehicleDto) {//base 64 encoaded photo came in vehiclePhoto field
		
		Integer OwnerId=vehicleDto.getOwnerId();
		
		Owner owner=ownerDao.findById(OwnerId).orElseThrow();
		
		Vehicle vehicle=mapper.map(vehicleDto, Vehicle.class);

		owner.addVehicle(vehicle);
		
		return mapper.map(vehicleDao.save(vehicle), VehicleDTO.class);
	}

}
