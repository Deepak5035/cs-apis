package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.impl.PatientServiceImpl;

@RestController
@RequestMapping(value = "/patient")
public class RegisterNewPatient {

	@Autowired
	PatientServiceImpl patientServiceImpl;
	
	@PostMapping(value = "/p1/newPatient")
	public ResponseEntity<PatientResponse> newPatientResistration(@RequestBody PatientRequest patientReq){
		
	PatientResponse patientResponse = new PatientResponse();
	
	patientResponse = patientServiceImpl.resisterNewPatient(patientReq);
	
	return new ResponseEntity<PatientResponse>(patientResponse, HttpStatus.OK);	
		
	}
	
	@PostMapping(value = "/p2/allPatient")
	public ResponseEntity<PatientResponse> getAllRegisterdPatient(){
		
		PatientResponse patientResponse = new PatientResponse();
		                              
		patientResponse = patientServiceImpl.getAllPatient();
		
		return new ResponseEntity<PatientResponse>(patientResponse , HttpStatus.OK);
		
	}
}
