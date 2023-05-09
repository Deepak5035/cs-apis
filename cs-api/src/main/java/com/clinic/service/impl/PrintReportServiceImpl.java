package com.clinic.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.dao.PatientServiceDao;
import com.clinic.entity.PatientEntity;
import com.clinic.model.StatusDescription;
import com.clinic.request.PatientRequest;
import com.clinic.response.PatientResponse;
import com.clinic.service.PrintReportService;
import com.clinic.utility.ConstantManager;
import com.clinic.utility.PrintReport2;

@Service
public class PrintReportServiceImpl implements PrintReportService {

	@Autowired
	PatientServiceDao patientServiceDao;

	@Autowired
	PrintReport2 printReport2;

	@Override
	public PatientResponse printReport(PatientRequest patientRequest) {

		StatusDescription statusDescription = new StatusDescription();
		PatientResponse patientResponse = new PatientResponse();

		String pdfPath = "";

		Optional<PatientEntity> optionalEntity = patientServiceDao.findById(patientRequest.getId());

		if (optionalEntity.isPresent()) {
			PatientEntity patientEntity = optionalEntity.get();

			pdfPath = printReport2.printPatientReport(patientEntity);

			if (pdfPath != "") {

				statusDescription.setCode(ConstantManager.Successfull.getStatusCode());
				statusDescription.setDescription(ConstantManager.Successfull.getDescription());
				patientResponse.setStatusDescription(statusDescription);

			} else {
				statusDescription.setCode(ConstantManager.ServerSideError.getStatusCode());
				statusDescription.setDescription(ConstantManager.ServerSideError.getDescription());
				patientResponse.setStatusDescription(statusDescription);
				patientResponse.setReportPath(pdfPath);
			}

		}

		return patientResponse;
	}

}
