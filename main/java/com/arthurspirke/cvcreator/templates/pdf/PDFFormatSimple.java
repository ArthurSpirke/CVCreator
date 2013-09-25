package com.arthurspirke.cvcreator.templates.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


import com.arthurspirke.cvcreator.entity.enums.Icons;
import com.arthurspirke.cvcreator.templates.PDFFormat;
import static com.arthurspirke.cvcreator.util.AppProperties.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;


public class PDFFormatSimple implements PDFFormat {
	private Logger log = Logger.getLogger(PDFFormatSimple.class);
	private Document document;
	private PdfWriter writer;
	private ResourceBundle res;
    
	private static final Font MENU_FONT;
	private static final Font INFO_FONT;
	private static final Font MAIN_TITLE_FONT;
	private static final Font MIDDLE_TITLE_FONT;
	private static final Font MINI_TITLE_FONT;
	private static BaseFont BASE_FONT;
	
    static{
		try {
			BASE_FONT = BaseFont.createFont(getFontTimes(), BaseFont.IDENTITY_H, true);
		} catch (DocumentException | IOException e) {
			//NOP
		}
		MENU_FONT = new Font(BASE_FONT, 18, Font.BOLD);
		INFO_FONT = new Font(BASE_FONT, 18);
		MAIN_TITLE_FONT = new Font(BASE_FONT, 25, Font.BOLD);
		MIDDLE_TITLE_FONT = new Font(BASE_FONT, 22, Font.BOLD);
		MINI_TITLE_FONT = new Font(BASE_FONT, 20, Font.BOLD);
		
    }
	
    

	public void init(String path, ResourceBundle lang) {
		res = lang;
		Rectangle newSize = new Rectangle(900, 842);
		document = new Document(newSize);
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(path));
			writer.getAcroForm().setNeedAppearances(true);
		} catch (FileNotFoundException | DocumentException e) {
			log.error("Error - " + e);
		}

		document.open();
		document.addTitle(res.getString("cv_document_title"));
		
		

	}



	public void staticTitle(){
		try{
			Paragraph par = new Paragraph(res.getString("cv_title"), MAIN_TITLE_FONT);
			par.setAlignment(Element.ALIGN_CENTER);
			document.add(par);
			emptySpace();
		} catch (DocumentException ex){
			log.error("Error - " + ex);
		}
	}
	
	@Override
	public void templatePersonalData(String firstName, String secondName) {
		writeMiniTitle(firstName + " " + secondName);
	}

	@Override
	public void templatePosition(String position) {
			writePlainTextWithLeftTitle(res.getString("$claimPositionT"), position);
	}

	@Override
	public void templateContactInfo(String eMail, String country, String region, String city, String address, String zipCode){
		        writeMiniTitle(res.getString("contact_information_title"));
		        emptySpace();
		        writePlainTextWithLeftTitle(res.getString("$eMailT"), eMail);
		        writePlainTextWithLeftTitle(res.getString(".CountryT"), country);
		        writePlainTextWithLeftTitle(res.getString(".RegionT"), region);
		        writePlainTextWithLeftTitle(res.getString(".CityT"), city);
		        writePlainTextWithLeftTitle(res.getString("$addressT"), address);
		        writePlainTextWithLeftTitle(res.getString("$zipCodeT"), String.valueOf(zipCode));

	}

	
	@Override
	public void templatePersonLinks(Icons icon, String linkName) {
		writePlainTextWithIcons(icon, linkName);
		emptySpace();
	}

	@Override
	public void templatePhoneNumbers(Icons phoneType, String phoneNumber) {
		writePlainTextWithIcons(phoneType, phoneNumber);
		emptySpace();
	}

	@Override
	public void templateProfile(String profile) {		
		writeMiniTitle(res.getString("$profileT"));
		writePlainText(profile);
		
	}

	@Override
	public void templateHobbies(String hobbies) {
		    writeMiniTitle(res.getString("$hobbiesT"));
			writePlainText(hobbies);
		
	}
	
	public void staticSkillsTitle(){	
		writeMiddleTitle(res.getString("$skillsTitle"));
	}

	@Override
	public void templateSkills(String name, String values) {
			writePlainTextWithLeftTitle(name, values);
	}
	
	public void staticEducationTitle(){
		writeMiddleTitle(res.getString("$educationTitle"));
	}

	@Override
	public void templateEducation(String eduType, String eduTitle, String eduYears, String eduDegree, String eduCountry, String eduRegion, String eduCity, String eduDescription){
		writePlainTextWithLeftTitle(res.getString(".eduTypeT"), eduType);
		writePlainTextWithLeftTitle(res.getString(".eduTitleT"), eduTitle);
		writePlainTextWithLeftTitle(res.getString(".allYearsT"), eduYears);
		writePlainTextWithLeftTitle(res.getString(".eduDegreeT"), eduDegree);
		writePlainTextWithLeftTitle(res.getString(".CountryT"), eduCountry);
		writePlainTextWithLeftTitle(res.getString(".RegionT"), eduRegion);
		writePlainTextWithLeftTitle(res.getString(".CityT"), eduCity);
		writePlainTextWithLeftTitle(res.getString(".allDescriptionT"), eduDescription);
	}

	public void staticEmploymentTitle(){
		writeMiddleTitle(res.getString("$employmentTitle"));
	}
	
	@Override
	public void templateEmploymentHistory(String empTitle, String empYears, String empPosition, String empCountry, String empRegion, String empCity, String empDescription) {
		writePlainTextWithLeftTitle(res.getString(".empTitleT"), empTitle);
		writePlainTextWithLeftTitle(res.getString(".allYearsT"), empYears);
		writePlainTextWithLeftTitle(res.getString(".empPositionT"), empPosition);
		writePlainTextWithLeftTitle(res.getString(".CountryT"), empCountry);
		writePlainTextWithLeftTitle(res.getString(".RegionT"), empRegion);
		writePlainTextWithLeftTitle(res.getString(".CityT"), empCity);
		writePlainTextWithLeftTitle(res.getString(".allDescriptionT"), empDescription);
		
	}
	
	@Override
	public void templateProjectTitle(){
		writeMiddleTitle(res.getString(".projectTitle"));
	}
	
	@Override
	public void templateProject(String projTitle, String projPosition,
			String projYears, String projDescription) {
		writePlainTextWithLeftTitle(res.getString(".projTitleT"), projTitle);
		writePlainTextWithLeftTitle(res.getString(".projPositionT"), projPosition);
		writePlainTextWithLeftTitle(res.getString(".allYearsT"), projYears);
		writePlainTextWithLeftTitle(res.getString(".allDescriptionT"), projDescription);
		
	}
	
	public void staticCertificateTitle(){
		writeMiddleTitle(res.getString("$certificateTitle"));
	}

	@Override
	public void templateCertificate(Image image, Integer height, Integer width, String small, String full) {
		try{
			document.add(image);
			writePlainText(small);
			writePlainText(full);
		} catch(DocumentException ex){
			log.error("Error - " + ex);
		}
		
	}
	
	private void emptySpace(){
		try {
			document.add(new Paragraph(" "));
		} catch (DocumentException e) {
          //NOP
		}
	}
	
	private void writeMiniTitle(String title){
		Paragraph miniTitle = new Paragraph(title, MINI_TITLE_FONT);
		try {
			document.add(miniTitle);
			emptySpace();
			emptySpace();
		} catch (DocumentException e) {
            //NOP
		}
	}
	
	private void writeMiddleTitle(String title){
		Paragraph middleTitle = new Paragraph(title, MIDDLE_TITLE_FONT);	
		try {
			middleTitle.setAlignment(Element.ALIGN_CENTER);
			document.add(middleTitle);
			emptySpace();
			emptySpace();
		} catch (DocumentException e) {
			//NOP
		}
	}
	
	private void writePlainTextWithLeftTitle(String title, String value){
		Chunk titleChunk = new Chunk(title, MENU_FONT);
		Chunk valueChunk = new Chunk(value, INFO_FONT);
		Chunk separator = new Chunk(" : ");
		try {
			document.add(titleChunk);
			document.add(separator);
			document.add(valueChunk);
			emptySpace();
		} catch (DocumentException e) {
           //NOP
		}
	}
	
	private void writePlainTextWithIcons(Icons icon, String value){
		try {
			Image image = icon.getIconImage();
			document.add(image);
			document.add(new Phrase(" : " + value, MENU_FONT));
		} catch (DocumentException e) {
            //NOP
		}
	}
	
	private void writePlainText(String value){
		Chunk valueChunk = new Chunk(value, INFO_FONT); 
		try {
			document.add(valueChunk);
			emptySpace();
			emptySpace();
		} catch (DocumentException e) {
            //NOP
		}
	}
	


	
	public void close() {document.close();}

}
