package com.clinic.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.clinic.entity.PatientEntity;
import com.clinic.entity.PatientTestEntity;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.Table;
import com.spire.doc.TableCell;
import com.spire.doc.TableRow;

@Component
public class PrintReport {

	@Autowired
	Environment environment;
	
	public  String printPatientReport(PatientEntity patientEntity) {
		
		String docTempPath = environment.getProperty("path.docPath");
		String pdfPath =environment.getProperty("path.pdfPath");
		
		String fileName = patientEntity.getName()+"_"+patientEntity.getAge()+"_"+patientEntity.getRefByDoc()+".pdf";
		
		String pdfDestinationPath = pdfPath+fileName;
		
		Document document = new Document();
		document.loadFromFile(docTempPath);

		double totalTestPrice =0.0;
		//Getting total price
		for(PatientTestEntity testEntity  : patientEntity.getTest()) {
			totalTestPrice += testEntity.getTestCost();
		}
		
		String totalAmount = Double.toString(totalTestPrice);
		
		String address = patientEntity.getAddress().getHouseNum()+ " "+patientEntity.getAddress().getCity()+" "+
		                 patientEntity.getAddress().getState()+" "+patientEntity.getAddress().getPinCode()+" "+
				         patientEntity.getAddress().getCountry();
		
		document.replace("#Patient Name", patientEntity.getName(),true,true);
		document.replace("#Patient Age", patientEntity.getAge(),true,true);
		document.replace("#Patient Sex", patientEntity.getSex(),true,true);
		document.replace("#Patient Address", address,true,true);
		document.replace("#Refer By Doctor", patientEntity.getRefByDoc(),true,true);
		
		Section section = document.addSection();
		
		Table table = section.addTable(true);

		table.resetCells(patientEntity.getTest().size(), 3);
		
		TableRow row = table.addRow(true);

		row.setHeight(20f);
		
		TableCell cell1 = row.addCell();
		
		document.replace("#TOTAL AMOUNT", totalAmount ,true,true);
		
		
		document.saveToFile(pdfDestinationPath, FileFormat.PDF);
		
		return pdfDestinationPath;
	}
}
