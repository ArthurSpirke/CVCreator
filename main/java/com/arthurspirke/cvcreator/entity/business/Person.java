package com.arthurspirke.cvcreator.entity.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.util.Utils;

public class Person extends MainEntity{
	private PersonalInfo personalInfo;
    private Address address;
	private PersonalTemplates personalTemplates;
	private final Map<String, String> linksToResumeFiles;
	private Language prefLang;
	private List<Skills> skills;
	private List<Education> education;
	private List<EmploymentHistory> employmentHistory;
	private List<Certificate> certificate;
	private List<PhoneNumbers> phoneNumbers;
	private List<PersonLinks> personLinks;


    public Person(String id, Language prefLang){
    	this(id, null, null, null, prefLang, "");
    }
	
	public Person(String id, PersonalInfo personalInfo, Address address, PersonalTemplates personalTemplates, Language prefLang, String state){
		super(id, state);
		this.id = id;
		this.personalInfo = personalInfo;
		this.address = address;
		this.personalTemplates = personalTemplates;
		this.linksToResumeFiles = new HashMap<>();
		this.prefLang = prefLang;
	}

	public static Person getEmptyPerson(){
		return new Person("", null);
	}
       
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PersonalTemplates getPersonalTemplates() {
		return personalTemplates;
	}

	public void setPersonalTemplates(PersonalTemplates personalTemplates) {
		this.personalTemplates = personalTemplates;
	}

	public Language getPrefLang() {
		return prefLang;
	}

	public void setPrefLang(Language prefLang) {
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

	public Map<String, String> getLinksToResumeFiles() {
		return linksToResumeFiles;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object o){
		if(o == null || o.getClass() != this.getClass()) return false;
		Person person = (Person) o;
		
		boolean idCheck = id == person.id || (id != null && id.equals(person.getId()));
		boolean personalInfoCheck = personalInfo == person.personalInfo || (personalInfo != null && personalInfo.equals(person.getPersonalInfo()));
		boolean addressCheck = address == person.address || (address != null && address.equals(person.getAddress()));
		boolean personalTemplatesCheck = personalTemplates == person.personalTemplates || (personalTemplates != null && personalTemplates.equals(person.getPersonalTemplates()));
		boolean linksToFilesCheck = linksToResumeFiles == person.linksToResumeFiles || (linksToResumeFiles != null && linksToResumeFiles.equals(person.getLinksToResumeFiles()));
		boolean prefLangCheck = prefLang == person.prefLang || (prefLang != null && prefLang.equals(person.getPrefLang()));
		boolean skillsCheck = skills == person.skills || (skills != null && skills.equals(person.getSkills()));
		boolean educationCheck = education == person.education || (education != null && education.equals(person.getEducation()));
		boolean employmentHistoryCheck = employmentHistory == person.employmentHistory || (employmentHistory != null && employmentHistory.equals(person.getEmploymentHistory()));
		boolean certificateCheck = certificate == person.certificate || (certificate != null & certificate.equals(person.getCertificate()));
		boolean phoneNumbersCheck = phoneNumbers == person.phoneNumbers || (phoneNumbers != null && phoneNumbers.equals(person.getPhoneNumbers()));
		boolean personLinksCheck = personLinks == person.personLinks || (personLinks != null && personLinks.equals(person.getPersonalInfo()));
		
		return idCheck && personalInfoCheck && addressCheck && personalTemplatesCheck && linksToFilesCheck && prefLangCheck && skillsCheck && educationCheck && employmentHistoryCheck && certificateCheck && phoneNumbersCheck && personLinksCheck;
	}
	
	//TODO: integer is not to big?
	@Override
	public int hashCode(){
		int hash = 17;
		hash = hash * 31 + (id == null ? 0 : id.hashCode());
		hash = hash * 31 + (personalInfo == null ? 0 : personalInfo.hashCode());
		hash = hash * 31 + (address == null ? 0 : address.hashCode());
		hash = hash * 31 + (personalTemplates == null ? 0 : personalTemplates.hashCode());
		hash = hash * 31 + (linksToResumeFiles == null ? 0 : linksToResumeFiles.hashCode());
		hash = hash * 31 + (prefLang == null ? 0 : prefLang.hashCode());
		hash = hash * 31 + (skills == null ? 0 : skills.hashCode());
		hash = hash * 31 + (education == null ? 0 : education.hashCode());
		hash = hash * 31 + (employmentHistory == null ? 0 : employmentHistory.hashCode());
		hash = hash * 31 + (certificate == null ? 0 : certificate.hashCode());
		hash = hash * 31 + (phoneNumbers == null ? 0 : phoneNumbers.hashCode());
		hash = hash * 31 + (personLinks == null ? 0 : personLinks.hashCode());
		return hash;
	}

	
}
