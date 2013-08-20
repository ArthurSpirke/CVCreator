package com.arthurspirke.cvcreator.entityfactory;

import static com.arthurspirke.cvcreator.util.Utils.*;

import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.EducationDAO;
import com.arthurspirke.cvcreator.dblayer.EmploymentHistoryDAO;
import com.arthurspirke.cvcreator.dblayer.LinksToFilesDAO;
import com.arthurspirke.cvcreator.dblayer.MainPersonalDAO;
import com.arthurspirke.cvcreator.dblayer.PersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.PhoneNumbersDAO;
import com.arthurspirke.cvcreator.dblayer.SkillsDAO;
import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entity.enums.Language;

public class PersonalInfoFactory {

	public static PersonalInfo newPersonalInfoToGenerateResume(Map<String, String> info, Map<String, List<Map<String, String>>> addition) {
		int personId = getInteger(info.get("personId"));
		
		PersonalInfo person = new PersonalInfo(personId, info.get("firstName"), info.get("lastName"), info.get("claimPosition"), info.get("eMail"), getInteger(info.get("personCountry")), getInteger(info.get("personRegion")), getInteger(info.get("personCity")), getInteger(info.get("zipCode")), info.get("address"), info.get("profile"), info.get("hobbies"), info.get("templatePDF"), info.get("templateHTML"), info.get("templateDOC"), Language.getLanguage(info.get("prefLang")));
        
		person.setLinksToFiles(LinksToFilesFactory.getLinksToFilesEntity(personId));
		person.setPhoneNumbers(PhoneNumbersFactory.getPhoneNumbersList(getInteger(info.get("phoneNumbersCount")), personId, addition.get("phoneNumbers")));
		person.setPersonLinks(PersonLinksFactory.getPersonLinksList(getInteger(info.get("personLinksCount")), personId, addition.get("personLinks")));
		person.setSkills(SkillsFactory.getSkillsList(getInteger(info.get("skillsCount")), personId, addition.get("skills")));
		person.setEducation(EducationFactory.getEducationList(getInteger(info.get("educationCount")), personId, addition.get("education")));
		person.setEmploymentHistory(EmploymentHistoryFactory.getEmploymentHistoryList(getInteger(info.get("employmentCount")), personId, addition.get("employment"), ProjectOnJobFactory.getProjectOnJobList(getInteger(info.get("projectsCount")), personId, addition.get("projects"))));
		person.setCertificate(CertificateFactory.getCertificateList(getInteger(info.get("certificateCount")), personId, addition.get("certificate")));

		return person;
	}
	
	
	public static PersonalInfo newPerosnalInfoToUpdateResume(Map<String, String> info, Map<String, List<Map<String, String>>> addition){
        return newPersonalInfoToGenerateResume(info, addition);
	}
	
	public static PersonalInfo newPersonalInfoToPreviewResume(Map<String, String> info, Map<String, List<Map<String, String>>> addition){
		return newPersonalInfoToGenerateResume(info, addition);
	}
	
	public static PersonalInfo getFullPersonFromDBById(int personId){
		PersonalInfo person = new MainPersonalDAO().getPersonById(personId);
		
		person.setPhoneNumbers(new PhoneNumbersDAO().getPhoneNumbersByPersonId(personId));
		person.setPersonLinks(new PersonLinksDAO().getPersonLinksByPersonId(personId));
		person.setSkills(new SkillsDAO().getSkillsByPersonId(personId));
		person.setEducation(new EducationDAO().getEducationByPersonId(personId));
		person.setEmploymentHistory(new EmploymentHistoryDAO().getEmploymentHistoryByPersonId(personId));
		person.setCertificate(new CertificateDAO().getCertificateByPersonId(personId));
		person.setLinksToFiles(new LinksToFilesDAO().getFutureLinksToFilesByPersonId(personId));
		
		return person;
	}
}
