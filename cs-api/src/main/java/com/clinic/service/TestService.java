package com.clinic.service;

import com.clinic.request.TestRequest;
import com.clinic.response.TestResponse;

public interface TestService {

	public TestResponse addingNewTest(TestRequest testRequest);
	
	public TestResponse deleteTest(TestRequest testRequest);
	
	public TestResponse getAllTest();
	
}
