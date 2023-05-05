package com.clinic.request;

import java.util.Map;

import com.clinic.entity.AddressEntity;

public class PatientRequest {

	private String name;
	
	private String age;
	
	private String sex;
	
	private AddressEntity address;
	
	private String refByDoc;
	
	private Map<Integer,String> testTypes;


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

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getRefByDoc() {
		return refByDoc;
	}

	public void setRefByDoc(String refByDoc) {
		this.refByDoc = refByDoc;
	}

	public Map<Integer, String> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(Map<Integer, String> testTypes) {
		this.testTypes = testTypes;
	}
}
