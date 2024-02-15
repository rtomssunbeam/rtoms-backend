package com.app.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.PermanentLicenseApplication;

public interface PermanentApplicationDao extends JpaRepository<PermanentLicenseApplication, Integer> {

}
