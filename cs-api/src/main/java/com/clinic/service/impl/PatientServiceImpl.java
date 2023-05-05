package com.clinic.service.impl;

import org.springframework.stereotype.Service;

import com.clinic.model.StatusDescription;
import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Override
	public PatientResponse resisterNewPatient(PatientRequest patientRequest) {

		StatusDescription statusDescription = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();
		
		
		
		
		return null;
	}

}
