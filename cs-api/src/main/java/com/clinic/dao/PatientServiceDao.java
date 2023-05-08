package com.clinic.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clinic.entity.PatientEntity;

@Repository
@Transactional
public interface PatientServiceDao extends JpaRepository<PatientEntity, Long> {

}
