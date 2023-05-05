package com.clinic.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.clinic.dao.TestServiceDao;
import com.clinic.entity.TestEntity;
import com.clinic.model.StatusDescription;
import com.clinic.request.TestRequest;
import com.clinic.response.TestResponse;
import com.clinic.service.TestService;

public class TestServiceImpl implements TestService {

	@Autowired
	TestServiceDao testServiceDao;
	
	@Override
	public TestResponse addingNewTest(TestRequest testRequest) {

		StatusDescription statusDescription = new StatusDescription();
		TestResponse testResponse = new TestResponse();
        
		TestEntity testEntity = new TestEntity();
		
		Long testId = GenerateTestId();
		
		testEntity.setTestId(testId);
		testEntity.setTestType(testRequest.getTestType());
		testEntity.setTestCost(testRequest.getTestCost());
		
		
		
		return null;

	}

	@Override
	public TestResponse deleteTest(TestRequest testRequest) {

		return null;

	}

	public Long GenerateTestId() {
		Random random = new Random();
		Long randomNumber = random.nextLong(90000) + 10000;
		return randomNumber;
	}
}
