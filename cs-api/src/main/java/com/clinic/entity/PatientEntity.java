package com.clinic.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "tbl_patient")
public class PatientEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "refByDoc")
	private String refByDoc;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "patientEntity")
	@Fetch(FetchMode.JOIN)
	private AddressEntity address;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "patientEntity")
	@Fetch(FetchMode.JOIN)
	private List<PatientTestEntity> test;
	
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
	public List<PatientTestEntity> getTest() {
		return test;
	}
	public void setTest(List<PatientTestEntity> test) {
		this.test = test;
	}
}
