package com.clinic.response;

import java.util.List;

import com.clinic.entity.PatientEntity;
import com.clinic.model.StatusDescription;

public class PatientResponse {

	StatusDescription statusDescription;
	
	List<PatientEntity> patientEntity;

	public StatusDescription getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}

	public List<PatientEntity> getPatientEntity() {
		return patientEntity;
	}

	public void setPatientEntity(List<PatientEntity> patientEntity) {
		this.patientEntity = patientEntity;
	}	
}
