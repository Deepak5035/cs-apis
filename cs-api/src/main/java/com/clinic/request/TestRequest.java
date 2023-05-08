package com.clinic.request;

public class TestRequest {
	
	private Long getTestId;
	
	private String testType;
	
	private double testCost;

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

	public double getTestCost() {
		return testCost;
	}

	public void setTestCost(double testCost) {
		this.testCost = testCost;
	}

	public Long getGetTestId() {
		return getTestId;
	}

	public void setGetTestId(Long getTestId) {
		this.getTestId = getTestId;
	}
}
