package com.arthurspirke.cvcreator.service;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

public class FactoryServiceTest {

	
	@Test
	public void testReturnObjectsFromFactoryServiceFactory(){	
		FactoryService<Address> address = FactoryServiceFactory.getFactoryService(EntityType.ADDRESS);
		FactoryService<PersonalInfo> personalInfo = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_INFO);
		FactoryService<PersonalTemplates> personalTemplates = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_TEMPLATES);
		FactoryService<PersonLinks> personLinks = FactoryServiceFactory.getFactoryService(EntityType.PERSONAL_LINKS);
		FactoryService<PhoneNumbers> phoneNumbers = FactoryServiceFactory.getFactoryService(EntityType.PHONE_NUMBERS);
		FactoryService<Skills> skills = FactoryServiceFactory.getFactoryService(EntityType.SKILLS);
		FactoryService<Education> education = FactoryServiceFactory.getFactoryService(EntityType.EDUCATION);
		FactoryService<EmploymentHistory> empHistory = FactoryServiceFactory.getFactoryService(EntityType.EMPLOYMENT_HISTORY);
		FactoryService<Project> project = FactoryServiceFactory.getFactoryService(EntityType.PROJECT);
		FactoryService<Certificate> certificate = FactoryServiceFactory.getFactoryService(EntityType.CERTIFICATE);
		
		assertThat(address, is(allOf(instanceOf(FactoryService.class), instanceOf(AddressService.class))));
		assertThat(personalInfo, is(allOf(instanceOf(FactoryService.class), instanceOf(PersonalInfoService.class))));
		assertThat(personalTemplates, is(allOf(instanceOf(FactoryService.class), instanceOf(PersonTemplatesService.class))));
		assertThat(personLinks, is(allOf(instanceOf(FactoryService.class), instanceOf(PersonLinksService.class))));
		assertThat(phoneNumbers, is(allOf(instanceOf(FactoryService.class), instanceOf(PhoneNumbersService.class))));
		assertThat(skills, is(allOf(instanceOf(FactoryService.class), instanceOf(SkillsService.class))));
		assertThat(education, is(allOf(instanceOf(FactoryService.class), instanceOf(EducationService.class))));
		assertThat(empHistory, is(allOf(instanceOf(FactoryService.class), instanceOf(EmploymentHistoryService.class))));
		assertThat(project, is(allOf(instanceOf(FactoryService.class), instanceOf(ProjectService.class))));
		assertThat(certificate, is(allOf(instanceOf(FactoryService.class), instanceOf(CertificateService.class))));
	}
}
