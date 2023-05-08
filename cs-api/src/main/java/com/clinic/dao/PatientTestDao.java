package com.clinic.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entity.PatientTestEntity;

@Repository
@Transactional
public interface PatientTestDao extends JpaRepository<PatientTestEntity, Long> {

}
