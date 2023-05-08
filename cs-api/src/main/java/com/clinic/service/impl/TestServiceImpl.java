package com.clinic.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.TestServiceDao;
import com.clinic.entity.TestEntity;
import com.clinic.model.StatusDescription;
import com.clinic.request.TestRequest;
import com.clinic.response.TestResponse;
import com.clinic.service.TestService;
import com.clinic.utility.ConstantManager;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	TestServiceDao testServiceDao;
	
	@Override
	public TestResponse addingNewTest(TestRequest testRequest) {

		StatusDescription statusDescription = new StatusDescription();
		TestResponse testResponse = new TestResponse();
		
        List<TestEntity> testEntities = new ArrayList<TestEntity>();
		
		TestEntity testEntity = new TestEntity();
		
		Long testId = GenerateTestId();
		
		testEntity.setTestId(testId);
		testEntity.setTestType(testRequest.getTestType());
		testEntity.setTestCost(testRequest.getTestCost());
        
		testEntities.add(testEntity);
		
		testServiceDao.save(testEntity);
		statusDescription.setDescription(ConstantManager.Successfull.getDescription());
		statusDescription.setCode(ConstantManager.Successfull.getStatusCode());
		testResponse.setStatusDescription(statusDescription);
        testResponse.setTestTypeEntity(testEntities);
		
		return testResponse;

	}

	@Override
	public TestResponse deleteTest(TestRequest testRequest) {
		
		StatusDescription statusDescription = new StatusDescription();
		TestResponse testResponse = new TestResponse();
		
		testServiceDao.deletebyTestId(testRequest.getGetTestId());

		testResponse.setStatusDescription(statusDescription);
        		
		return testResponse;

	}
	
	public Long GenerateTestId() {
		Random random = new Random();
		Long randomNumber = random.nextLong(90000) + 10000;
		return randomNumber;
	}

	@Override
	public TestResponse getAllTest() {
		
		StatusDescription statusDescription = new StatusDescription();
		TestResponse testResponse = new TestResponse();
		
		List<TestEntity> testEntities = testServiceDao.findAll();
		
		if(testEntities!=null) {
			statusDescription.setDescription(ConstantManager.Successfull.getDescription());
			statusDescription.setCode(ConstantManager.Successfull.getStatusCode());
			testResponse.setStatusDescription(statusDescription);
			testResponse.setTestTypeEntity(testEntities);
		}else {
	      
			statusDescription.setDescription(ConstantManager.NoRecdordFound.getDescription());
			statusDescription.setCode(ConstantManager.NoRecdordFound.getStatusCode());
			testResponse.setStatusDescription(statusDescription);
			testResponse.setTestTypeEntity(testEntities);
		}
		
		return testResponse;
	}

}
