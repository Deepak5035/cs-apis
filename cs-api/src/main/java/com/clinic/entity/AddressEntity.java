package com.clinic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_address")
public class AddressEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "houseNum")
	private String houseNum;

	@Column(name = "city")
    private String city;
    
	@Column(name = "state")
    private String state;
    
	@Column(name = "country") 
    private String country;
    
	@Column(name = "pincode")
    private Long pinCode;
    
    @JsonIgnore
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pid" , referencedColumnName = "id")
	private PatientEntity patientEntity;

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getPinCode() {
		return pinCode;
	}

	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PatientEntity getPatientEntity() {
		return patientEntity;
	}

	public void setPatientEntity(PatientEntity patientEntity) {
		this.patientEntity = patientEntity;
	}
	
}
