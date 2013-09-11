package com.arthurspirke.cvcreator.service.generators;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.factory.resumeentity.PDFFormatFactory;
import com.arthurspirke.cvcreator.templates.PDFFormat;
import com.arthurspirke.cvcreator.util.Utils;



public class PDFGenerator {
	private final PDFFormat pdf;
	private final Person p;
	private static Logger log = Logger.getLogger(PDFGenerator.class);

	public PDFGenerator(Person person, String template) {
		this.p = person;
		TemplatePDF pdfTemplate = TemplatePDF.valueOf(template);
		this.pdf = PDFFormatFactory.getPDFFormat(pdfTemplate);
	}
	
	public PDFGenerator(Person person, TemplatePDF template) {
		this.p = person;
		this.pdf = PDFFormatFactory.getPDFFormat(template);
	}

	private void addLogo() {

	}

	private void addPersonData() {

		String firstName = p.getPersonalInfo().getFirstName();
		String secondName = p.getPersonalInfo().getSecondName();
		
		pdf.templatePersonalData(firstName,secondName);
		
	}

	private void addPosition() {
		
		String position = p.getPersonalInfo().getClaimPosition();
		
		pdf.templatePosition(position);
	}
	
	private void addContactInfo(){
        
		String eMail = p.getPersonalInfo().geteMail();
		String country = p.getAddress().getStringCountry();
		String region = p.getAddress().getStringRegion();
		String city = p.getAddress().getStringCity();
		String streetAddress = p.getAddress().getStreet();
		String postalCode = String.valueOf(p.getAddress().getPostalCode());
		
		pdf.templateContactInfo(eMail, country, region, city, streetAddress, postalCode);
	}
	
	private void addPersonLinks(){
		for(PersonLinks link : p.getPersonLinks()){
			pdf.templatePersonLinks(link.getLinkType(), link.getLinkName());
		}
	}
	
	private void addPhoneNumbers(){
		
		for(PhoneNumbers ph : p.getPhoneNumbers()){
			
			PhoneIcon phoneType = ph.getPhoneType();
			String phoneNumber = ph.getPhoneNumber();
			
			pdf.templatePhoneNumbers(phoneType, phoneNumber);
			
		}
		
	}

	private void addProfile() {
		
		String profile = p.getPersonalInfo().getProfile();
		
		pdf.templateProfile(profile);
	}

	private void addHobbies() {
		
		String hobbies = p.getPersonalInfo().getHobbies();
		
		pdf.templateHobbies(hobbies);
		
	}

	private void addSkills() {
		
		pdf.staticSkillsTitle();
		

		for (Skills skill : p.getSkills()) {
			
			String name = skill.getSkillsName();
			String value = skill.getSkillsValue();
			
			pdf.templateSkills(name, value);
			
		}
		
	}

	private void addEducation() {
		
		pdf.staticEducationTitle();
		
		List<Education> education = p.getEducation();

		for (Education edu : education) {
			
		    String educationType = edu.getType().toString();
		    String title = edu.getTitle();
		    String years = edu.getYears();
		    String degree = edu.getDegree();
		    String country = edu.getAddress().getStringCountry();
		    String region = edu.getAddress().getStringRegion();
		    String city = edu.getAddress().getStringCity();
		    String description = edu.getDescription();
			
			pdf.templateEducation(educationType, title, years, degree, country, region, city, description);
		
		}
	}

	private void addEmploymentHistory() {
		pdf.staticEmploymentTitle();
		List<EmploymentHistory> employment = p
				.getEmploymentHistory();

		for (EmploymentHistory emp : employment) {

			String title = emp.getTitle();
			String years = emp.getYears();
			String position = emp.getPosition();
			String country = emp.getAddress().getStringCountry();
			String region = emp.getAddress().getStringRegion();
			String city = emp.getAddress().getStringCity();
			String description = emp.getDescription();
			
			pdf.templateEmploymentHistory(title, years, position, country, region, city, description);
		    
			log.debug("Project list size - " + emp.getProjects().size());
			
			addProject(emp.getProjects());
		}
	}
	
	private void addProject(List<Project> projectList){
		 
			for(Project proj : projectList){
				
				String title = proj.getTitle();
				String position = proj.getPosition();
				String years = proj.getYears();
				String description = proj.getDescription();
				
				pdf.templateProject(title, position, years, description);
				
			}	
	}

	private void addCertificate() {
		List<Certificate> certificate = p.getCertificate();
		pdf.staticCertificateTitle();
			for (Certificate cert : certificate) {
				int h = cert.getImageSize().height();
				int w = cert.getImageSize().width();
				pdf.templateCertificate(cert.getImages(), h, w, cert.getSmallDescription(), cert.getFullDescription());
			}


	}

	public String write(String path) {
		new File(path + p.getId()).mkdir();
		pdf.init(path + p.getId() + "/" + p.getId() + ".pdf", Utils.getResourceBundleByLang(p.getPrefLang()));
		pdf.staticTitle();
		addPersonData();
		addPosition();
		addContactInfo();
		addPersonLinks();
		addPhoneNumbers();
		addProfile();
		addHobbies();
		addSkills();
		addEducation();
		addEmploymentHistory();
		addCertificate();
		pdf.close();
        return path + p.getId() + "/" + p.getId() + ".pdf";
	}
}
