package com.clinic.service;

import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;

public interface PatientService {

	public PatientResponse resisterNewPatient(PatientRequest patientRequest) ;
	
}
