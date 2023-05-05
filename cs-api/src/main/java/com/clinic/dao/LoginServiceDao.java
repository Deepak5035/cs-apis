package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.clinic.entity.LoginEntity;

@Repository
@Transactional
public interface LoginServiceDao extends JpaRepository<LoginEntity, Long> {

    public LoginEntity findByMobileNumber(Long mobileNumber);	
	
    public LoginEntity findByEmailId(String emailId);
}
