package com.arthurspirke.cvcreator.templates.pdf;

import java.util.ResourceBundle;

import com.arthurspirke.cvcreator.entity.enums.Icons;
import com.arthurspirke.cvcreator.templates.PDFFormat;
import com.itextpdf.text.Image;

public class PDFFormatExtended implements PDFFormat {

	@Override
	public void init(String path, ResourceBundle res) {


	}

	@Override
	public void close() {


	}

	@Override
	public void staticTitle() {


	}

	@Override
	public void templatePersonalData(String firstName, String secondName) {

	}

	@Override
	public void templatePosition(String claimPosition) {


	}

	@Override
	public void templateContactInfo(
			String eMail, String country, String region, String city,
			String address, String zipCode) {


	}

	@Override
	public void templateProfile(String profile) {


	}

	@Override
	public void templateHobbies(String hobbies) {


	}

	@Override
	public void staticSkillsTitle() {


	}

	@Override
	public void templateSkills(String type, String concrete) {
	

	}

	@Override
	public void staticEducationTitle() {


	}

	@Override
	public void templateEducation(String eduType, String eduTitle,
			String eduYears, String eduDegree, String eduCountry,
			String eduRegion, String eduCity, String eduDescription) {


	}

	@Override
	public void staticEmploymentTitle() {


	}

	@Override
	public void templateEmploymentHistory(String empTitle, String empYears,
			String empPosition, String empCountry, String empRegion,
			String empCity, String empDescription) {


	}

	@Override
	public void staticCertificateTitle() {


	}

	@Override
	public void templateCertificate(Image image, Integer height, Integer width,
			String small, String full) {


	}

	@Override
	public void templateProject(String projTitle, String projPosition,
			String projYears, String projDescription) {

		
	}

	@Override
	public void templatePersonLinks(Icons linkType, String linkName) {

		
	}

	@Override
	public void templatePhoneNumbers(Icons phoneType, String phoneNumber) {

		
	}

	@Override
	public void templateProjectTitle() {

		
	}

}
