package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import static com.arthurspirke.cvcreator.service.DBServiceFactory.*;
import static com.arthurspirke.cvcreator.service.FactoryServiceFactory.*;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonResumeService{
	private Logger log = Logger.getLogger(PersonResumeService.class);
	private PersonalDAO personDAO = DAOFactoryProducer.getFactory(EntityType.PERSON).getPersonDAO();
      
	private DBService<Address> addressDBService = getDBService(EntityType.ADDRESS);
	private DBService<PersonalInfo> personalInfoDBService = getDBService(EntityType.PERSONAL_INFO);
	private DBService<PersonalTemplates> personalTemplatesDBService = getDBService(EntityType.PERSONAL_TEMPLATES);
	private DBService<PersonLinks> personLinksDBService = getDBService(EntityType.PERSONAL_LINKS);
	private DBService<PhoneNumbers> phoneNumbersDBService = getDBService(EntityType.PHONE_NUMBERS);
	private DBService<Skills> skillsDBService = getDBService(EntityType.SKILLS);
	private DBService<Education> educationDBService = getDBService(EntityType.EDUCATION);
	private DBService<EmploymentHistory> employmentHistoryDBService = getDBService(EntityType.EMPLOYMENT_HISTORY);
	private DBService<Certificate> certificateDBService = getDBService(EntityType.CERTIFICATE);
    
	private FactoryService<Address> addressFactoryService = getFactoryService(EntityType.ADDRESS);
	private FactoryService<PersonalInfo> personalInfoFactoryService = getFactoryService(EntityType.PERSONAL_INFO);
	private FactoryService<PersonalTemplates> personalTemplatesFactoryService = getFactoryService(EntityType.PERSONAL_TEMPLATES);
	private FactoryService<PersonLinks> personLinksFactoryService = getFactoryService(EntityType.PERSONAL_LINKS);
	private FactoryService<PhoneNumbers> phoneNumbersFactoryService = getFactoryService(EntityType.PHONE_NUMBERS);
	private FactoryService<Skills> skillsFactoryService = getFactoryService(EntityType.SKILLS);
	private FactoryService<Education> educationFactoryService = getFactoryService(EntityType.EDUCATION);
	private FactoryService<EmploymentHistory> employmentHistoryFactoryService = getFactoryService(EntityType.EMPLOYMENT_HISTORY);
	private FactoryService<Certificate> certificateFactoryService = getFactoryService(EntityType.CERTIFICATE);
    
    public Person newPerson(Map<String, String> personInfo, Map<String, List<Map<String, String>>> additionalInfo){
    	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    	 Person person = getPerson(personInfo);
    	 String personId = person.getId(); 
    	 
    	 //TODO: work with this! Bad implementation! 
    	 Map<String, String> addressInfo = additionalInfo.get("address").get(0);
    	 person.setAddress(addressFactoryService.getEntity(personId, addressInfo));
    	 
    	 Map<String, String> personalInfo = additionalInfo.get("personalInfo").get(0);
    	 person.setPersonalInfo(personalInfoFactoryService.getEntity(personId, personalInfo));
    	 
    	 Map<String, String> personalTemplates = additionalInfo.get("personalTemplates").get(0);
    	 log.debug("Pdf - " + personalTemplates.get("templatePDF"));
    	 log.debug("html - " + personalTemplates.get("templateHTML"));
    	 log.debug("doc - " + personalTemplates.get("templateDOC"));
    	 person.setPersonalTemplates(personalTemplatesFactoryService.getEntity(personId, personalTemplates));
    	 
    	 int personLinksCount = Utils.getInteger(personInfo.get("personLinksCount"));
    	 int phoneNumbersCount = Utils.getInteger(personInfo.get("phoneNumbersCount"));
    	 int skillsCount = Utils.getInteger(personInfo.get("skillsCount"));
    	 int eduCount = Utils.getInteger(personInfo.get("eduCount"));
    	 int empCount = Utils.getInteger(personInfo.get("empCount"));
    	 int certCount = Utils.getInteger(personInfo.get("certCount"));
    	 
    	 List<PersonLinks> personLinks = personLinksFactoryService.getEntitiesList(personLinksCount, personId, additionalInfo.get("personLinks"));
    	 person.setPersonLinks(personLinks);
    	 
    	 List<PhoneNumbers> phoneNumbers = phoneNumbersFactoryService.getEntitiesList(phoneNumbersCount, personId, additionalInfo.get("phoneNumbers"));
    	 person.setPhoneNumbers(phoneNumbers);
    	 
    	 List<Skills> skills = skillsFactoryService.getEntitiesList(skillsCount, personId, additionalInfo.get("skills"));
    	 person.setSkills(skills);
    	 
    	 List<Education> education = educationFactoryService.getEntitiesList(eduCount, personId, additionalInfo.get("education"));
    	 person.setEducation(education);
    	 
    	 List<EmploymentHistory> employmentHistory = employmentHistoryFactoryService.getEntitiesList(empCount, personId, additionalInfo.get("employmentHistory"));
    	 person.setEmploymentHistory(employmentHistory);
    	 
    	 List<Certificate> certificates = certificateFactoryService.getEntitiesList(certCount, personId, additionalInfo.get("certificate"));
    	 person.setCertificate(certificates);
    	 
    	 return person;
    }
    

	public Person getEntity(String id){
		
		try {
			
			Person person = personDAO.getById(id);		
			
			person.setAddress(addressDBService.getEntity(id));
			person.setPersonalInfo(personalInfoDBService.getEntity(id));
			person.setPersonalTemplates(personalTemplatesDBService.getEntity(id));
			person.setPersonLinks(personLinksDBService.getEntitiesList(id));
			person.setPhoneNumbers(phoneNumbersDBService.getEntitiesList(id));
			person.setSkills(skillsDBService.getEntitiesList(id));
			person.setEducation(educationDBService.getEntitiesList(id));
			person.setEmploymentHistory(employmentHistoryDBService.getEntitiesList(id));
			person.setCertificate(certificateDBService.getEntitiesList(id));
			//TODO: set LinksToFiles
			
			return person;
			
		} catch (ComponentAssemblyException e) {
			
             return Person.getEmptyPerson();
             
		}
	}

	public boolean save(Person person) {
		
		try {
			personDAO.insert(person);
			
			addressDBService.save(person.getAddress());
			personalInfoDBService.save(person.getPersonalInfo());
			personalTemplatesDBService.save(person.getPersonalTemplates());
			personLinksDBService.save(person.getPersonLinks());
			phoneNumbersDBService.save(person.getPhoneNumbers());
			skillsDBService.save(person.getSkills());
			educationDBService.save(person.getEducation());
			employmentHistoryDBService.save(person.getEmploymentHistory());
			certificateDBService.save(person.getCertificate());
			//TODO: save LinksToFiles
			
			return true;
			
		} catch (ComponentWriteException e) {
            return false;
		}
		

		
	}


	public void update(Person entity) throws ComponentWriteException{
          personDAO.update(entity);	
	}



	public void update(List<Person> entities)throws ComponentWriteException{
		personDAO.update(entities);
	}



	public void delete(Person entity)throws ComponentWriteException{
       String id = entity.getId();
       personDAO.delete(id);
	}



	public void delete(List<Person> entities)throws ComponentWriteException{
		
        String[] ids = new String[entities.size()];
        int length = ids.length;
        
        for(int i = 0; i < length; i++){
        	ids[i] = entities.get(i).getId();
        }
        
        personDAO.delete(ids);
	}
	
	private Person getPerson(Map<String, String> personInfo){
		
		String id = Utils.getRealId(personInfo.get("id"));
		Language language = Language.getLanguage(personInfo.get("prefLang"));
		
		return new Person(id, language);

	}
	
}
