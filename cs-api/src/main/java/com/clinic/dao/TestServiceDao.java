package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.entity.TestEntity;

public interface TestServiceDao extends JpaRepository<TestEntity, Long> {
	
}
