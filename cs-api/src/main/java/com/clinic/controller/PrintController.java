package com.clinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.impl.PrintReportServiceImpl;

@RestController
@RequestMapping(value ="/print")
@CrossOrigin("*")
public class PrintController {

	
	@Autowired
	PrintReportServiceImpl printReportServiceImpl;
	
	@GetMapping(value ="/p1/printRep")
	public ResponseEntity<PatientResponse> printReport(@RequestBody PatientRequest patientRequest) {
		
		PatientResponse patientResponse = new PatientResponse();
		
		patientResponse = printReportServiceImpl.printReport(patientRequest);
		
		return new ResponseEntity<PatientResponse>(patientResponse,HttpStatus.OK);
		
	}
	
}
