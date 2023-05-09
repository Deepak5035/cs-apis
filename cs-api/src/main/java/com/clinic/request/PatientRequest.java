package com.clinic.request;

import java.util.List;

public class PatientRequest {

	private Long id;
	
	private String name;
	
	private String age;
	
	private String sex;
	
	private AddressRequest address;
	
	private String refByDoc;
	
	private List<TestRequest> testTypes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getRefByDoc() {
		return refByDoc;
	}

	public void setRefByDoc(String refByDoc) {
		this.refByDoc = refByDoc;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	public List<TestRequest> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(List<TestRequest> testTypes) {
		this.testTypes = testTypes;
	}
}
