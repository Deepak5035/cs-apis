package com.clinic.service;

import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;

public interface PrintReportService {

	public PatientResponse printReport(PatientRequest patientRequest);
	
}
