package com.arthurspirke.cvcreator.entity.domain;

import java.util.List;

import com.arthurspirke.cvcreator.entity.enums.Language;

public class PersonalInfo {
	private final int id;
	private final String firstName;
	private final String secondName;
	private final String claimPosition;
	private final String eMail;
	private final int country;
	private final int region;
	private final int city;
	private final int zipCode;
	private final String address;
	private final String profile;
	private final String hobbies;
	private final String templatePdf;
	private final String templateHtml;
	private final String templateDoc;
	private List<Skills> skills;
	private List<Education> education;
	private List<EmploymentHistory> employmentHistory;
	private List<Certificate> certificate;
	private List<PhoneNumbers> phoneNumbers;
	private List<PersonLinks> personLinks;
	private LinksToFiles linksToFiles;
	private final Language prefLang;


	

	
	public PersonalInfo(int id, String firstName, String lastName,
			String claimPosition, String eMail, int country,
			int region, int city, int zipCode,
			String address, String profile, String hobbies, Language prefLang){
		this(id, firstName, lastName, claimPosition, eMail, country, region, city, zipCode, address, profile, hobbies, "", "", "", prefLang);
	}
	
	public PersonalInfo(int id, String firstName, String secondName,
			String claimPosition, String eMail, int country,
			int region, int city, int zipCode,
			String address, String profile, String hobbies, String templatePdf, String templateHtml, String templateDoc, Language prefLang) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.claimPosition = claimPosition;
		this.eMail = eMail;
		this.country = country;
		this.region = region;
		this.city = city;
		this.zipCode = zipCode;
		this.address = address;
		this.profile = profile;
		this.hobbies = hobbies;
		this.templatePdf = templatePdf;
		this.templateHtml = templateHtml;
		this.templateDoc = templateDoc;
		this.prefLang = prefLang;

	}

	public List<Skills> getSkills() {
		return skills;
	}

	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}


	public List<EmploymentHistory> getEmploymentHistory() {
		return employmentHistory;
	}

	public void setEmploymentHistory(List<EmploymentHistory> employmentHistory) {
		this.employmentHistory = employmentHistory;
	}

	public List<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(List<Certificate> certificate) {
		this.certificate = certificate;
	}

	public List<PhoneNumbers> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumbers> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public List<PersonLinks> getPersonLinks() {
		return personLinks;
	}

	public void setPersonLinks(List<PersonLinks> personLinks) {
		this.personLinks = personLinks;
	}

	public LinksToFiles getLinksToFiles() {
		return linksToFiles;
	}

	public void setLinksToFiles(LinksToFiles linksToFiles) {
		this.linksToFiles = linksToFiles;
	}

	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public String getClaimPosition() {
		return claimPosition;
	}

	public String geteMail() {
		return eMail;
	}
	
	public int getCountry() {
		return country;
	}

	public int getRegion() {
		return region;
	}
	
	public int getCity() {
		return city;
	}

	public int getZipCode() {
		return zipCode;
	}

	public String getAddress() {
		return address;
	}

	public String getProfile() {
		return profile;
	}

	public String getHobbies() {
		return hobbies;
	}

	public Language getPrefLang() {
		return prefLang;
	}

	public String getTemplatePdf() {
		return templatePdf;
	}

	public String getTemplateHtml() {
		return templateHtml;
	}

	public String getTemplateDoc() {
		return templateDoc;
	}

	
}
