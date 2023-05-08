package com.clinic.controller;

import javax.servlet.http.HttpServletRequest;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.model.StatusDescription;
import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.impl.JwtFactoryServiceImpl;
import com.clinic.service.impl.PatientServiceImpl;
import com.clinic.utility.ConstantManager;

@RestController
@RequestMapping(value = "/patient")
public class RegisterNewPatient {

	static RsaJsonWebKey senderJwk = null;

	static {
		try {
			senderJwk = RsaJwkGenerator.generateJwk(2048);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Autowired
	PatientServiceImpl patientServiceImpl;

	@Autowired
	JwtFactoryServiceImpl jwtFactoryServiceImpl;

	@PostMapping(value = "/p1/newPatient")
	public ResponseEntity<PatientResponse> newPatientResistration(@RequestBody PatientRequest patientReq,
			@RequestHeader("Authorization") String token, @RequestHeader("aid") Long aId, HttpServletRequest req) {

		StatusDescription statusDescriptionModel = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();

		boolean isValid = jwtFactoryServiceImpl.validateToken(senderJwk, token, aId, "WEB", "", "", req);

		if (isValid) {

			patientResponse = patientServiceImpl.resisterNewPatient(patientReq);
		} else {
			statusDescriptionModel.setCode(ConstantManager.UnAuhorization.getStatusCode());
			statusDescriptionModel.setDescription(ConstantManager.UnAuhorization.getDescription());
			patientResponse.setStatusDescription(statusDescriptionModel);
		}
		return new ResponseEntity<PatientResponse>(patientResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/p2/allPatient")
	public ResponseEntity<PatientResponse> getAllRegisterdPatient(@RequestHeader("Authorization") String token,
			@RequestHeader("aid") Long aId, HttpServletRequest req) {

		StatusDescription statusDescriptionModel = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();

		boolean isValid = jwtFactoryServiceImpl.validateToken(senderJwk, token, aId, "WEB", "", "", req);

		if (isValid) {
			patientResponse = patientServiceImpl.getAllPatient();

		} else {
			statusDescriptionModel.setCode(ConstantManager.UnAuhorization.getStatusCode());
			statusDescriptionModel.setDescription(ConstantManager.UnAuhorization.getDescription());
			patientResponse.setStatusDescription(statusDescriptionModel);
		}
		return new ResponseEntity<PatientResponse>(patientResponse, HttpStatus.OK);
	}
}
