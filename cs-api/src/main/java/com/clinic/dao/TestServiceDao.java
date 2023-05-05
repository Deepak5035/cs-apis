package com.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clinic.entity.TestEntity;

public interface TestServiceDao extends JpaRepository<TestEntity, Long> {
	
	@Query(value = "delete from tbl_test_table where testId=:testId",nativeQuery = true)
	public Long deletebyTestId(Long testId); 
}
