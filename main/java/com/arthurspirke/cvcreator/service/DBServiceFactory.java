package com.arthurspirke.cvcreator.service;

import com.arthurspirke.cvcreator.entity.enums.EntityType;

public class DBServiceFactory {

	
    @SuppressWarnings("unchecked")
	public static <T> DBService<T> getDBService(EntityType entityType){
    	switch(entityType){
    	case CERTIFICATE:
    		return (DBService<T>) new CertificateService();
    	case EDUCATION:
    		return (DBService<T>) new EducationService();
    	case EMPLOYMENT_HISTORY:
    		return (DBService<T>) new EmploymentHistoryService();
    	case PHONE_NUMBERS:
    		return (DBService<T>) new PhoneNumbersService();
    	case PERSONAL_LINKS:
    		return (DBService<T>) new PersonLinksService();
    	case SKILLS:
    		return (DBService<T>) new SkillsService();
    	case ADDRESS:
    		return (DBService<T>) new AddressService();
    	case PERSONAL_INFO:
    		return (DBService<T>) new PersonalInfoService();
    	case PERSONAL_TEMPLATES:
    		return (DBService<T>) new PersonTemplatesService();
    	case PROJECT:
    		return (DBService<T>) new ProjectService();
    	default:
    		throw new IllegalArgumentException();
    	}
    }

}
