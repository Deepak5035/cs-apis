package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.request.TestRequest;
import com.clinic.response.TestResponse;
import com.clinic.service.impl.TestServiceImpl;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	TestServiceImpl patientServiceImpl;

	@PostMapping(value = "/t1/addTest")
	public ResponseEntity<TestResponse> addingTest(@RequestBody TestRequest patientReq) {

		TestResponse testResponse = new TestResponse();

		testResponse = patientServiceImpl.addingNewTest(patientReq);

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}

	public ResponseEntity<TestResponse> deleteTest(@RequestBody TestRequest testRequest) {

		TestResponse testResponse = new TestResponse();
		
		testResponse = patientServiceImpl.deleteTest(testRequest);

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}
}
