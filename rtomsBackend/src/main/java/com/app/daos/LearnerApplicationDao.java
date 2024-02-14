package com.app.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.LearnerLicenseApplication;

public interface LearnerApplicationDao extends JpaRepository<LearnerLicenseApplication, Integer> {

}
