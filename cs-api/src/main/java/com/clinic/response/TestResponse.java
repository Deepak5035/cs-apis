package com.clinic.response;

import java.util.List;

import com.clinic.entity.TestEntity;
import com.clinic.model.StatusDescription;

public class TestResponse {

	StatusDescription statusDescription;
	List<TestEntity> testTypeEntity;
	
	public StatusDescription getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(StatusDescription statusDescription) {
		this.statusDescription = statusDescription;
	}
	public List<TestEntity> getTestTypeEntity() {
		return testTypeEntity;
	}
	public void setTestTypeEntity(List<TestEntity> testTypeEntity) {
		this.testTypeEntity = testTypeEntity;
	}
	
}
