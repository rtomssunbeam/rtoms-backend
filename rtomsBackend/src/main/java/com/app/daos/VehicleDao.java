package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.dtos.VehicleDTO;
import com.app.entities.Vehicle;

@Repository
public interface VehicleDao extends JpaRepository<Vehicle, Integer> {



	
}
