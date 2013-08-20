package com.arthurspirke.cvcreator.model;

import java.util.List;

import com.arthurspirke.cvcreator.dblayer.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.EducationDAO;
import com.arthurspirke.cvcreator.dblayer.EmploymentHistoryDAO;
import com.arthurspirke.cvcreator.dblayer.MainPersonalDAO;
import com.arthurspirke.cvcreator.dblayer.PersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.PhoneNumbersDAO;
import com.arthurspirke.cvcreator.dblayer.SkillsDAO;
import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.arthurspirke.cvcreator.entity.domain.Education;
import com.arthurspirke.cvcreator.entity.domain.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.domain.PersonLinks;
import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entity.domain.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.domain.Skills;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonUpdater {

	public static void updatePerson(PersonalInfo p){
		
		MainPersonalDAO dao = new MainPersonalDAO();
		dao.updateStandaloneDataFromPerson(p);
		
		
		changePhonesNumbers(p.getPhoneNumbers(), p.getId());
		changePersonLinks(p.getPersonLinks(), p.getId());
		changeSkills(p.getSkills(), p.getId());
		changeEducation(p.getEducation(), p.getId());
		changeEmploymentHistory(p.getEmploymentHistory(), p.getId());
		changeCertificate(p.getCertificate(), p.getId());
	
		
	}
	

	
	private static void changePhonesNumbers(List<PhoneNumbers> phonesNumber, Integer personId){
		PhoneNumbersDAO dao = new PhoneNumbersDAO();
		dao.updatePhonesNumbersById(Utils.getPhoneNumbersListByState(phonesNumber, "upd"));
		dao.addPhoneNumbers(personId, Utils.getPhoneNumbersListByState(phonesNumber, "new"));
		dao.deletePhonesNumbersById(Utils.getPhoneNumbersListByState(phonesNumber, "del"));
		
	}
	
	private static void changePersonLinks(List<PersonLinks> personLinks, Integer personId){
		PersonLinksDAO dao = new PersonLinksDAO();
		dao.updatePersonLinksById(Utils.getPersonLinksListByState(personLinks, "upd"));
		dao.addPersonLinks(personId, Utils.getPersonLinksListByState(personLinks, "new"));
		dao.deletePersonLinksById(Utils.getPersonLinksListByState(personLinks, "del"));
	}
	
	
	private static void changeSkills(List<Skills> skills, Integer personId){
		SkillsDAO dao = new SkillsDAO();
        dao.updateSkillEntityById(Utils.getSkillsListByState(skills, "upd"));
        dao.addSkills(personId, Utils.getSkillsListByState(skills, "new"));
        dao.deleteSkillEntity(Utils.getSkillsListByState(skills, "del"));

		}

	
	
	private static void changeEducation(List<Education> education, Integer personId){
		EducationDAO dao = new EducationDAO();
        dao.updateEducationEntityBy(Utils.getEducationListByState(education, "upd"));
        dao.addEducation(personId, Utils.getEducationListByState(education, "new"));
        dao.deleteEducationEntity(Utils.getEducationListByState(education, "del"));

	}
	
	private static void changeEmploymentHistory(List<EmploymentHistory> employmentHistory, Integer personId){
		EmploymentHistoryDAO dao = new EmploymentHistoryDAO();
        dao.updateEmploymentHistoryEntityById(Utils.getEmploymentHistoryListByState(employmentHistory, "upd"));
        dao.addEmploymentHistory(personId, Utils.getEmploymentHistoryListByState(employmentHistory, "new"));
        dao.deleteEmploymentHistoryEntity(Utils.getEmploymentHistoryListByState(employmentHistory, "del"));

	}

	private static void changeCertificate(List<Certificate> certificate, Integer personId){
		CertificateDAO dao = new CertificateDAO();
		dao.updateCertificateEntityById(Utils.getCertificateListByState(certificate, "upd"));
        dao.addCertificateLinks(personId, Utils.getCertificateListByState(certificate, "new"));
		dao.deleteCertificateEntity(Utils.getCertificateListByState(certificate, "del"));

	}
	
}
