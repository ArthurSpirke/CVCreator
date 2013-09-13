package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalInfoDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonalInfoService implements DBService<PersonalInfo>, FactoryService<PersonalInfo>{

     PersonalInfoDAO personalInfoDAO = DAOFactoryProducer.getFactory(EntityType.PERSONAL_INFO).getPersonalInfoDAO();
     
     @Override
     public void save(PersonalInfo personalInfo) throws ComponentWriteException {
    	 personalInfoDAO.insert(personalInfo);
     }

	@Override
	public PersonalInfo getEntity(String id) throws ComponentAssemblyException {
        return personalInfoDAO.getById(id);
	}

	@Override
	public List<PersonalInfo> getEntitiesList(String personId) throws ComponentAssemblyException {
		return personalInfoDAO.getListByPersonId(personId);
	}

	@Override
	public void save(List<PersonalInfo> entities) throws ComponentWriteException {
		personalInfoDAO.insert(entities);	
	}

	@Override
	public List<PersonalInfo> getEntitiesList(int entitiesCount,
			String personId, List<Map<String, String>> list) {
         throw new UnsupportedOperationException();
	}

	@Override
	public PersonalInfo getEntity(String personId, Map<String, String> map) {
		
         String id = Utils.getRealId(map.get("id"));
         String firstName = map.get("firstName");
         String lastName = map.get("lastName");
         String claimPosition = map.get("claimPosition");
         String eMail = map.get("eMail");
         String profile = map.get("profile");
         String hobbies = map.get("hobbies");
         String state = map.get("state");
         
         return new PersonalInfo(id, personId, firstName, lastName, claimPosition, eMail, profile, hobbies, state);

	}

	@Override
	public void update(PersonalInfo entity) throws ComponentWriteException {
         personalInfoDAO.update(entity);
	}

	@Override
	public void update(List<PersonalInfo> entities) throws ComponentWriteException {
         personalInfoDAO.update(entities);	
	}

	@Override
	public void delete(PersonalInfo entity) throws ComponentWriteException {
         String id = entity.getId();
         personalInfoDAO.delete(id);
	}

	@Override
	public void delete(List<PersonalInfo> entities) throws ComponentWriteException {
	       String[] ids = Utils.getIdsByComponents(entities);
	      personalInfoDAO.delete(ids);
	}


}
