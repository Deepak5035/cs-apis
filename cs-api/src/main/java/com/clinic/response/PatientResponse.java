package com.clinic.response;

import com.clinic.entity.PatientEntity;
import com.clinic.model.StatusDescription;

public class PatientResponse {

	StatusDescription statusDescription;
	
	PatientEntity patientEntity;

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public PatientEntity getPatientEntity() {
		return patientEntity;
	}

	public void setPatientEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
	}
	
}
