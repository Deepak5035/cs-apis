package com.clinic.controller;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.model.StatusDescription;
import com.clinic.request.TestRequest;
import com.clinic.response.TestResponse;
import com.clinic.service.impl.JwtFactoryServiceImpl;
import com.clinic.service.impl.TestServiceImpl;
import com.clinic.utility.ConstantManager;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	static RsaJsonWebKey senderJwk = null;

	static {
		try {
			senderJwk = RsaJwkGenerator.generateJwk(2048);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	TestServiceImpl testServiceImpl;

	@Autowired
	JwtFactoryServiceImpl jwtFactoryServiceImpl;

	@PostMapping(value = "/t1/addTest")
	public ResponseEntity<TestResponse> addingTest(@RequestBody TestRequest patientReq,
			@RequestHeader("Authorization") String token, @RequestHeader("aid") Long aId, HttpServletRequest req) {

		StatusDescription statusDescriptionModel = new StatusDescription();

		TestResponse testResponse = new TestResponse();

		boolean isValid = jwtFactoryServiceImpl.validateToken(senderJwk, token, aId, "WEB", "", "", req);

		if (isValid) {
			testResponse = testServiceImpl.addingNewTest(patientReq);
		} else {
			statusDescriptionModel.setCode(ConstantManager.UnAuhorization.getStatusCode());
			statusDescriptionModel.setDescription(ConstantManager.UnAuhorization.getDescription());
			testResponse.setStatusDescription(statusDescriptionModel);
		}

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}

	@DeleteMapping(value = "/t2/deleteTest")
	public ResponseEntity<TestResponse> deleteTest(@RequestBody TestRequest testRequest,
			@RequestHeader("Authorization") String token, @RequestHeader("aid") Long aId, HttpServletRequest req) {

		StatusDescription statusDescriptionModel = new StatusDescription();

		boolean isValid = jwtFactoryServiceImpl.validateToken(senderJwk, token, aId, "WEB", "", "", req);

		TestResponse testResponse = new TestResponse();

		if (isValid) {

			testResponse = testServiceImpl.deleteTest(testRequest);
		} else {
			statusDescriptionModel.setCode(ConstantManager.UnAuhorization.getStatusCode());
			statusDescriptionModel.setDescription(ConstantManager.UnAuhorization.getDescription());
			testResponse.setStatusDescription(statusDescriptionModel);
		}

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);

	}

	@GetMapping(value = "/t3/allTest")
	public ResponseEntity<TestResponse> getAllTest(@RequestHeader("Authorization") String token,
			@RequestHeader("aid") Long aId, HttpServletRequest req) {

		StatusDescription statusDescriptionModel = new StatusDescription();

		boolean isValid = jwtFactoryServiceImpl.validateToken(senderJwk, token, aId, "WEB", "", "", req);

		TestResponse testResponse = new TestResponse();

		if (isValid) {
			testResponse = testServiceImpl.getAllTest();
		} else {
			statusDescriptionModel.setCode(ConstantManager.UnAuhorization.getStatusCode());
			statusDescriptionModel.setDescription(ConstantManager.UnAuhorization.getDescription());
			testResponse.setStatusDescription(statusDescriptionModel);
		}

		return new ResponseEntity<TestResponse>(testResponse, HttpStatus.OK);
	}
}
