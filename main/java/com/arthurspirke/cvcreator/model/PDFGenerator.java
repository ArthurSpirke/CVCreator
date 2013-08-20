package com.arthurspirke.cvcreator.model;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.arthurspirke.cvcreator.entity.domain.Education;
import com.arthurspirke.cvcreator.entity.domain.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.domain.PersonLinks;
import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entity.domain.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;
import com.arthurspirke.cvcreator.entity.domain.Skills;
import com.arthurspirke.cvcreator.factory.PDFFormatFactory;
import com.arthurspirke.cvcreator.templates.PDFFormat;
import com.arthurspirke.cvcreator.util.Utils;

import static com.arthurspirke.cvcreator.entityfactory.PlacesFactory.*;


public class PDFGenerator {
	private final PDFFormat pdf;
	private final PersonalInfo p;
	private static Logger log = Logger.getLogger(PDFGenerator.class);

	public PDFGenerator(PersonalInfo personalInfo, String template) {
		this.p = personalInfo;
		this.pdf = PDFFormatFactory.getPDFFormat(template);
	}

	private void addLogo() {

	}

	private void addPersonData() {


		pdf.templatePersonalData(p.getFirstName(),
				p.getSecondName());

		
	}

	private void addPosition() {
		pdf.templatePosition(p.getClaimPosition());
	}
	
	private void addContactInfo(){
		String country = (p.getCountry() == 0) ? "" : getCountryByPrefLang(p.getCountry(), p.getPrefLang());
		String region = (p.getRegion() == 0) ? "" : getRegionByPrefLang(p.getRegion(), p.getPrefLang());
		String city = (p.getCity() == 0) ? "" : getCityByPrefLang(p.getCity(), p.getPrefLang());
		String zipCode = (p.getZipCode() == 0) ? "" : String.valueOf(p.getZipCode());
		
		
		pdf.templateContactInfo(p.geteMail(), country, region, city, p.getAddress(), zipCode);
	}
	
	private void addPersonLinks(){
		for(PersonLinks link : p.getPersonLinks()){
			pdf.templatePersonLinks(link.getLinkType(), link.getLinkName());
		}
	}
	
	private void addPhoneNumbers(){
		for(PhoneNumbers ph : p.getPhoneNumbers()){
			pdf.templatePhoneNumbers(ph.getPhoneType(), ph.getPhoneNumber());
		}
	}

	private void addProfile() {
		pdf.templateProfile(p.getProfile());
	}

	private void addHobbies() {
		pdf.templateHobbies(p.getHobbies());
	}

	private void addSkills() {
		pdf.staticSkillsTitle();
		List<Skills> skills = p.getSkills();

		for (Skills skill : skills) {
			pdf.templateSkills(skill.getSkillsName(), skill.getSkillsValue());
		}
	}

	private void addEducation() {
		pdf.staticEducationTitle();
		List<Education> education = p.getEducation();

		for (Education edu : education) {
			String eduCountry = (edu.getEduCountry() == 0) ? "" : getCountryByPrefLang(edu.getEduCountry(), p.getPrefLang());
			String eduRegion = (edu.getEduRegion() == 0) ? "" : getRegionByPrefLang(edu.getEduRegion(), p.getPrefLang());
			String eduCity = (edu.getEduCity() == 0) ? "" : getCityByPrefLang(edu.getEduCity(), p.getPrefLang());
			pdf.templateEducation(edu.getEduType().toString(), edu.getEduTitle(), edu.getEduYears(), edu.getEduDegree(), eduCountry, eduRegion, eduCity, edu.getEduDescription());
		}
	}

	private void addEmploymentHistory() {
		pdf.staticEmploymentTitle();
		List<EmploymentHistory> employment = p
				.getEmploymentHistory();

		for (EmploymentHistory emp : employment) {
			String empCountry = (emp.getEmpCountry() == 0) ? "" : getCountryByPrefLang(emp.getEmpCountry(), p.getPrefLang());
			String empRegion = (emp.getEmpRegion() == 0 ) ? "" : getRegionByPrefLang(emp.getEmpRegion(), p.getPrefLang());
			String empCity = (emp.getEmpCity() == 0) ? "" : getCityByPrefLang(emp.getEmpCity(), p.getPrefLang());
			pdf.templateEmploymentHistory(emp.getEmpTitle(), emp.getEmpYears(), emp.getEmpPosition(), empCountry, empRegion, empCity, emp.getEmpDescription());
		    addProject(emp.getProjects());
		}
	}
	
	private void addProject(List<ProjectOnJob> projectList){
		 
			for(ProjectOnJob proj : projectList){
				pdf.templateProject(proj.getProjTitle(), proj.getProjPosition(), proj.getProjYears(), proj.getProjDescription());
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
