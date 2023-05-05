package com.clinic.response;

import com.clinic.entity.TestTypeEntity;
import com.clinic.model.StatusDescription;

public class TestResponse {

	StatusDescription statusDescription;
	TestTypeEntity testTypeEntity;
	
	public StatusDescription getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}
	public TestTypeEntity getTestTypeEntity() {
		return testTypeEntity;
	}
	public void setTestTypeEntity(TestTypeEntity testTypeEntity) {
		this.testTypeEntity = testTypeEntity;
	}
	
}
