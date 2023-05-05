package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.PatientEntity;

public interface PatientServiceDao extends JpaRepository<PatientEntity, Long> {

	
	
}
