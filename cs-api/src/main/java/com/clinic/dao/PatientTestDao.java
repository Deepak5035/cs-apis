package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.PatientTestEntity;

public interface PatientTestDao extends JpaRepository<PatientTestEntity, Long> {

}
