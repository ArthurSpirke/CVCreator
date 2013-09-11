package com.arthurspirke.cvcreator.service;

import com.arthurspirke.cvcreator.entity.enums.EntityType;

public class FactoryServiceFactory {

	 @SuppressWarnings("unchecked")
		public static <T> FactoryService<T> getFactoryService(EntityType entityType){
	    	switch(entityType){
	    	case CERTIFICATE:
	    		return (FactoryService<T>) new CertificateService();
	    	case EDUCATION:
	    		return (FactoryService<T>) new EducationService();
	    	case EMPLOYMENT_HISTORY:
	    		return (FactoryService<T>) new EmploymentHistoryService();
	    	case PHONE_NUMBERS:
	    		return (FactoryService<T>) new PhoneNumbersService();
	    	case PERSONAL_LINKS:
	    		return (FactoryService<T>) new PersonLinksService();
	    	case SKILLS:
	    		return (FactoryService<T>) new SkillsService();
	    	case ADDRESS:
	    		return (FactoryService<T>) new AddressService();
	    	case PERSONAL_INFO:
	    		return (FactoryService<T>) new PersonalInfoService();
	    	case PERSONAL_TEMPLATES:
	    		return (FactoryService<T>) new PersonTemplatesService();
	    	case PROJECT:
	    		return (FactoryService<T>) new ProjectService();
	    	default:
	    		throw new IllegalArgumentException();
	    	}
	    }
}
