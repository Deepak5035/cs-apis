package com.clinic.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clinic.entity.TestEntity;

@Repository
@Transactional
public interface TestServiceDao extends JpaRepository<TestEntity, Long> {
	
	@Query(value = "delete from tbl_test_table where test_id=:testId",nativeQuery = true)
	public Long deletebyTestId(Long testId); 
	
	@Query(value = "select * from tbl_test_table where test_type=:testType",nativeQuery = true)
	public TestEntity getTestIdByTestName(String testType);
	
}
