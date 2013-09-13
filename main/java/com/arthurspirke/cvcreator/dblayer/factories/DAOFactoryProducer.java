package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.entity.enums.EntityType;

public class DAOFactoryProducer {

	
	public static AbstractDAOFactory getFactory(EntityType entityType){
		switch(entityType){
		case ADDRESS:
			return new AddressDAOFactory();
		case CERTIFICATE:
			return new CertificateDAOFactory();
		case EDUCATION:
			return new EducationDAOFactory();
		case EMPLOYMENT_HISTORY:
			return new EmploymentHistoryDAOFactory();
		case PHONE_NUMBERS:
			return new PhoneNumbersDAOFactory();
		case PERSONAL_LINKS:
			return new PersonLinksDAOFactory();
		case PROJECT:
			return new ProjectDAOFactory();
		case SKILLS:
			return new SkillsDAOFactory();
		case PERSONAL_INFO:
			return new PersonalInfoDAOFactory();
		case PERSONAL_TEMPLATES:
			return new PersonalTemplatesDAOFactory();
		case PERSON:
			return new PersonDAOFactory();
		case COUNTRY:
			return new CountryDAOFactory();
		case REGION:
			return new RegionDAOFactory();
		case CITY:
			return new CityDAOFactory();
		default:
			throw new IllegalArgumentException();
		}
	}
}
