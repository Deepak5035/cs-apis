package com.clinic.request;

import java.util.List;
import com.clinic.entity.AddressEntity;
import com.clinic.entity.PatientTestEntity;

public class PatientRequest {

	private String name;
	
	private String age;
	
	private String sex;
	
	private AddressEntity address;
	
	private String refByDoc;
	
	private List<PatientTestEntity> testTypes;


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

	public List<PatientTestEntity> getTestTypes() {
		return testTypes;
	}

	public void setTestTypes(List<PatientTestEntity> testTypes) {
		this.testTypes = testTypes;
	}
}
