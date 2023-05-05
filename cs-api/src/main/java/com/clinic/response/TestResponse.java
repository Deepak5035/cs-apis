package com.clinic.response;

import com.clinic.entity.TestEntity;
import com.clinic.model.StatusDescription;

public class TestResponse {

	StatusDescription statusDescription;
	TestEntity testTypeEntity;
	
	public StatusDescription getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}
	public TestEntity getTestTypeEntity() {
		return testTypeEntity;
	}
	public void setTestTypeEntity(TestEntity testTypeEntity) {
		this.testTypeEntity = testTypeEntity;
	}
	
}
