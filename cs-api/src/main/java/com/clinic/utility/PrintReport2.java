package com.clinic.utility;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.clinic.entity.PatientEntity;
import com.clinic.entity.PatientTestEntity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PrintReport2 {

	@Autowired
	Environment environment;

	private Float[] logoImgScale = { 40f, 40f };

	private static Font COURIER = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

	public String printPatientReport(PatientEntity patientEntity) {

		String pdfPath = environment.getProperty("path.pdfPath");

		String fileName = patientEntity.getName() + "_" + patientEntity.getAge() + "_" + patientEntity.getRefByDoc()
				+ ".pdf";

		String pdfDestinationPath = pdfPath + fileName;

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(pdfDestinationPath));
			document.open();
			addLogo(document);
			addDocTitle(document);
			createDetailsTable(document, patientEntity);
			createTestTable(document, patientEntity);
			addFooter(document);
			document.close();

			return pdfDestinationPath;

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		return pdfPath;
	}

	private void addLogo(Document document) {
		try {
			Image img = Image.getInstance(environment.getProperty("path.logo"));
			img.scalePercent(logoImgScale[0], logoImgScale[1]);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document) throws DocumentException {

		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph("TEST CLINIC REPORT", COURIER));
		p1.setAlignment(Element.ALIGN_CENTER);
		leaveEmptyLine(p1, 1);
		document.add(p1);
	}

	private void createDetailsTable(Document document, PatientEntity patientEntity) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 2);
		paragraph.add(new Paragraph("PATIENT DETAILS", COURIER));
		leaveEmptyLine(paragraph, 1);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBorderColor(BaseColor.WHITE);

		table.addCell("Patient Name");
		table.addCell(patientEntity.getName());

		table.addCell("Patient Age");
		table.addCell(patientEntity.getAge());

		table.addCell("Patient Sex");
		table.addCell(patientEntity.getSex());

		String address = patientEntity.getAddress().getHouseNum() + " " + patientEntity.getAddress().getCity() + " "
				+ patientEntity.getAddress().getState() + " " + patientEntity.getAddress().getPinCode() + " "
				+ patientEntity.getAddress().getCountry();

		table.addCell("Patient Address");
		table.addCell(address);

		table.addCell("Refer By Doctor");
		table.addCell(patientEntity.getRefByDoc());

		document.add(table);
	}

	private void createTestTable(Document document, PatientEntity patientEntity) throws DocumentException {

		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 2);
		paragraph.add(new Paragraph("TEST REPORT", COURIER));
		leaveEmptyLine(paragraph, 1);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(3);

		String[] columnName = { "TestNo", "Test Name ", "Test Price" };

		for (int i = 0; i < 3; i++) {
			Phrase phrase = new Phrase(columnName[i]);
			PdfPCell cell = new PdfPCell(phrase);
			cell.setHorizontalAlignment(Element.ALIGN_TOP);
			cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell.setBorderColor(BaseColor.BLACK);
			table.addCell(cell);
		}

		Long testNum = 1L;
		for (PatientTestEntity patientTestEntity : patientEntity.getTest()) {

			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			table.getDefaultCell().setBorderColor(BaseColor.BLACK);
			table.addCell(testNum.toString());
			table.addCell(patientTestEntity.getTestType());
			table.addCell(Double.toString(patientTestEntity.getTestCost()));
			testNum++;
		}

		double totalTestPrice = 0.0;
		// Getting total price
		for (PatientTestEntity testEntity : patientEntity.getTest()) {
			totalTestPrice += testEntity.getTestCost();
		}

		String totalAmount = Double.toString(totalTestPrice);
		
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBorderColor(BaseColor.BLACK);
		table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
		table.addCell("");
		table.addCell("");
		table.addCell("--");
		
		
		table.setWidthPercentage(100);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBorderColor(BaseColor.BLACK);
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell("");
		table.addCell("TOTAL BILL");
		table.addCell(totalAmount);
		
		document.add(table);

	}

	private void addFooter(Document document) throws DocumentException {
		Paragraph p2 = new Paragraph();
		leaveEmptyLine(p2, 3);
		p2.setAlignment(Element.ALIGN_LEFT);
		p2.add(new Paragraph(environment.getProperty("footerData"), COURIER_SMALL_FOOTER));

		document.add(p2);
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
}