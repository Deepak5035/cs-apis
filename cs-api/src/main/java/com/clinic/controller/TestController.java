package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	TestServiceImpl testServiceImpl;

	@PostMapping(value = "/t1/addTest")
	public ResponseEntity<TestResponse> addingTest(@RequestBody TestRequest patientReq) {

		TestResponse testResponse = new TestResponse();

		testResponse = testServiceImpl.addingNewTest(patientReq);

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}

	@DeleteMapping(value = "/t2/deleteTest")
	public ResponseEntity<TestResponse> deleteTest(@RequestBody TestRequest testRequest) {

		TestResponse testResponse = new TestResponse();
		
		testResponse = testServiceImpl.deleteTest(testRequest);

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}
	
	@GetMapping(value = "/t3/allTest")
	public ResponseEntity<TestResponse> getAllTest(){
		
		TestResponse testResponse = new TestResponse();
		
		testResponse = testServiceImpl.getAllTest();
		
		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);
	}
}
