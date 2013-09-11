package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.business.Component;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class UpdateService {
    private final Person person;


    public UpdateService(Person person){
    	this.person = person;
    }
    
    
    public boolean update() {
    	
    	try {
    		
			updateMainPersonInfo();
	    	skillsUpdate();
	    	personLinksUpdate();
	    	phoneNumbersUpdate();
	    	educationUpdate();
	    	employmentHistoryUpdate();
	    	certificateUpdate(); 	
	    	
	    	return true;
	    	
		} catch (ComponentWriteException e) {
             return false;
		}

    }
    
    private void updateMainPersonInfo() throws ComponentWriteException {
   	
     PersonResumeService personService = new PersonResumeService(); 
     DBService<Address> addressService = DBServiceFactory.getDBService(EntityType.ADDRESS);
     DBService<PersonalInfo> personalInfoService = DBServiceFactory.getDBService(EntityType.PERSONAL_INFO);
     DBService<PersonalTemplates> personalTemplatesService = DBServiceFactory.getDBService(EntityType.PERSONAL_TEMPLATES);
     
     personService.update(person);
     addressService.update(person.getAddress());
     personalInfoService.update(person.getPersonalInfo());
     personalTemplatesService.update(person.getPersonalTemplates());
     
     
    }
    
    private void personLinksUpdate() throws ComponentWriteException {
    	List<PersonLinks> personLinks = person.getPersonLinks();
    	DBService<PersonLinks> service = DBServiceFactory.getDBService(EntityType.PERSONAL_LINKS);
    	
    	service.save(getEntitiesByOperType(personLinks, "new"));
    	service.update(getEntitiesByOperType(personLinks, "upd"));
    	service.delete(getEntitiesByOperType(personLinks, "del"));
    }
    
    private void phoneNumbersUpdate() throws ComponentWriteException {
    	List<PhoneNumbers> phoneNumbers = person.getPhoneNumbers();
    	DBService<PhoneNumbers> service = DBServiceFactory.getDBService(EntityType.PHONE_NUMBERS);
    	
    	service.save(getEntitiesByOperType(phoneNumbers, "new"));
    	service.update(getEntitiesByOperType(phoneNumbers, "upd"));
    	service.delete(getEntitiesByOperType(phoneNumbers, "del"));
    }
    
    private void skillsUpdate() throws ComponentWriteException {
    	List<Skills> skills = person.getSkills();
    	DBService<Skills> service = DBServiceFactory.getDBService(EntityType.SKILLS);
    	
    	service.save(getEntitiesByOperType(skills, "new"));
    	service.update(getEntitiesByOperType(skills, "upd"));
    	service.delete(getEntitiesByOperType(skills, "del"));
    	
      }
    
    private void educationUpdate() throws ComponentWriteException {
    	List<Education> education = person.getEducation();
    	DBService<Education> service = DBServiceFactory.getDBService(EntityType.EDUCATION);
    	
    	service.save(getEntitiesByOperType(education, "new"));
    	service.update(getEntitiesByOperType(education, "upd"));
    	service.delete(getEntitiesByOperType(education, "del"));
    }
    
    private void employmentHistoryUpdate() throws ComponentWriteException {
    	List<EmploymentHistory> employmentHistory = person.getEmploymentHistory();
    	DBService<EmploymentHistory> service = DBServiceFactory.getDBService(EntityType.EMPLOYMENT_HISTORY);
    	
    	service.save(getEntitiesByOperType(employmentHistory, "new"));
    	service.update(getEntitiesByOperType(employmentHistory, "upd"));
    	service.delete(getEntitiesByOperType(employmentHistory, "del"));
    	
    	List<Project> allProjects = Utils.assemblyProjectsFromCompanies(employmentHistory);
    	projectUpdate(allProjects);
    }
    
    private void projectUpdate(List<Project> projects) throws ComponentWriteException {
    	DBService<Project> service = DBServiceFactory.getDBService(EntityType.PROJECT);
    	
    	service.save(getEntitiesByOperType(projects, "new"));
    	service.update(getEntitiesByOperType(projects, "upd"));
    	service.delete(getEntitiesByOperType(projects, "del"));
    }
    
    private void certificateUpdate() throws ComponentWriteException {
    	List<Certificate> certificate = person.getCertificate();
    	DBService<Certificate> service = DBServiceFactory.getDBService(EntityType.CERTIFICATE);
    	
    	service.save(getEntitiesByOperType(certificate, "new"));
    	service.update(getEntitiesByOperType(certificate, "upd"));
    	service.delete(getEntitiesByOperType(certificate, "del"));
    }
    
    
    private <T extends Component> List<T> getEntitiesByOperType(List<T> components, String operationType){
        	List<T> returnList = new ArrayList<>();
        	
    		for(T comp : components){
    			if(comp.getState().equals(operationType)){
    				returnList.add(comp);
    			}
    		}	
    		
        return returnList;	
    }
    
    
    
}
