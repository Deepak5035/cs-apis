package com.clinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.AddressDao;
import com.clinic.dao.PatientServiceDao;
import com.clinic.dao.PatientTestDao;
import com.clinic.dao.TestServiceDao;
import com.clinic.entity.AddressEntity;
import com.clinic.entity.PatientEntity;
import com.clinic.entity.PatientTestEntity;
import com.clinic.entity.TestEntity;
import com.clinic.model.StatusDescription;
import com.clinic.request.PatientRequest;
import com.clinic.request.TestRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.PatientService;
import com.clinic.utility.ConstantManager;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientServiceDao patientServiceDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	PatientTestDao patientTestDao;

	@Autowired
	TestServiceDao testServiceDao;

	@Override
	public PatientResponse resisterNewPatient(PatientRequest patientRequest) {

		StatusDescription statusDescription = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();

		try {

			PatientEntity patientEntity = new PatientEntity();
			AddressEntity addressEntity = new AddressEntity();

			patientEntity.setName(patientRequest.getName());
			patientEntity.setAge(patientRequest.getAge());
			patientEntity.setRefByDoc(patientRequest.getRefByDoc());
			patientEntity.setSex(patientRequest.getSex());

			patientServiceDao.save(patientEntity);

			addressEntity.setHouseNum(patientRequest.getAddress().getHouseNum());
			addressEntity.setCity(patientRequest.getAddress().getCity());
			addressEntity.setCountry(patientRequest.getAddress().getCountry());
			addressEntity.setState(patientRequest.getAddress().getState());
			addressEntity.setPinCode(patientRequest.getAddress().getPinCode());
			addressEntity.setPatientEntity(patientEntity);

			addressDao.save(addressEntity);

			for (TestRequest entity : patientRequest.getTestTypes()) {
				PatientTestEntity patientTestEntity = new PatientTestEntity();
				patientTestEntity.setTestType(entity.getTestType());

				TestEntity testEntity = testServiceDao.getTestIdByTestName(entity.getTestType().trim());

				if (testEntity != null) {

					if (entity.getTestCost() != 0.0) {
						patientTestEntity.setTestCost(entity.getTestCost());
					} else {
						patientTestEntity.setTestCost(testEntity.getTestCost());
					}
					patientTestEntity.setTestId(testEntity.getTestId());
					patientTestEntity.setPatientEntity(patientEntity);
					patientTestDao.save(patientTestEntity);

				} else {
					statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
					statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
					patientResponse.setStatusDescription(statusDescription);
				}

			}

			statusDescription.setDescription(ConstantManager.Successfull.getDescription());
			statusDescription.setCode(ConstantManager.Successfull.getStatusCode());
			patientResponse.setStatusDescription(statusDescription);

		} catch (Exception e) {
			statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
			statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
			patientResponse.setStatusDescription(statusDescription);
			e.printStackTrace();
		}
		return patientResponse;
	}

	public PatientResponse getAllPatient() {

		StatusDescription statusDescription = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();

		try {
			List<PatientEntity> listOfAllEntity = patientServiceDao.findAll();

			if (listOfAllEntity.size() > 0) {

				patientResponse.setPatientEntity(listOfAllEntity);
				statusDescription.setDescription(ConstantManager.Successfull.getDescription());
				statusDescription.setCode(ConstantManager.Successfull.getStatusCode());
				patientResponse.setStatusDescription(statusDescription);
			} else {
				statusDescription.setDescription(ConstantManager.NoRecdordFound.getDescription());
				statusDescription.setCode(ConstantManager.NoRecdordFound.getStatusCode());
				patientResponse.setStatusDescription(statusDescription);
			}
		} catch (Exception e) {
			statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
			statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
			patientResponse.setStatusDescription(statusDescription);
			e.printStackTrace();
		}
		return patientResponse;
	}
}
