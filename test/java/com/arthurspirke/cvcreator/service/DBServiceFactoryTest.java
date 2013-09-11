package com.arthurspirke.cvcreator.service;

import org.junit.Test;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class DBServiceFactoryTest {

	
	@Test
	public void testReturnObjectsFromDBServiceFactory(){	
		DBService<Address> address = DBServiceFactory.getDBService(EntityType.ADDRESS);
		DBService<PersonalInfo> personalInfo = DBServiceFactory.getDBService(EntityType.PERSONAL_INFO);
		DBService<PersonalTemplates> personalTemplates = DBServiceFactory.getDBService(EntityType.PERSONAL_TEMPLATES);
		DBService<PersonLinks> personLinks = DBServiceFactory.getDBService(EntityType.PERSONAL_LINKS);
		DBService<PhoneNumbers> phoneNumbers = DBServiceFactory.getDBService(EntityType.PHONE_NUMBERS);
		DBService<Skills> skills = DBServiceFactory.getDBService(EntityType.SKILLS);
		DBService<Education> education = DBServiceFactory.getDBService(EntityType.EDUCATION);
		DBService<EmploymentHistory> empHistory = DBServiceFactory.getDBService(EntityType.EMPLOYMENT_HISTORY);
		DBService<Project> project = DBServiceFactory.getDBService(EntityType.PROJECT);
		DBService<Certificate> certificate = DBServiceFactory.getDBService(EntityType.CERTIFICATE);
		
		assertThat(address, is(allOf(instanceOf(DBService.class), instanceOf(AddressService.class))));
		assertThat(personalInfo, is(allOf(instanceOf(DBService.class), instanceOf(PersonalInfoService.class))));
		assertThat(personalTemplates, is(allOf(instanceOf(DBService.class), instanceOf(PersonTemplatesService.class))));
		assertThat(personLinks, is(allOf(instanceOf(DBService.class), instanceOf(PersonLinksService.class))));
		assertThat(phoneNumbers, is(allOf(instanceOf(DBService.class), instanceOf(PhoneNumbersService.class))));
		assertThat(skills, is(allOf(instanceOf(DBService.class), instanceOf(SkillsService.class))));
		assertThat(education, is(allOf(instanceOf(DBService.class), instanceOf(EducationService.class))));
		assertThat(empHistory, is(allOf(instanceOf(DBService.class), instanceOf(EmploymentHistoryService.class))));
		assertThat(project, is(allOf(instanceOf(DBService.class), instanceOf(ProjectService.class))));
		assertThat(certificate, is(allOf(instanceOf(DBService.class), instanceOf(CertificateService.class))));
	}
}
