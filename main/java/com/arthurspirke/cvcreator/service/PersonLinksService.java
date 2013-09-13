package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.LinkIcon;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonLinksService implements DBService<PersonLinks>, FactoryService<PersonLinks>{
	
    PersonLinksDAO personLinksDAO = DAOFactoryProducer.getFactory(EntityType.PERSONAL_LINKS).getPersonalLinksDAO();
    

    @Override
    public void save(List<PersonLinks> personLinks) throws ComponentWriteException {
         personLinksDAO.insert(personLinks);
    }

	@Override
	public PersonLinks getEntity(String id) throws ComponentAssemblyException {
		return personLinksDAO.getById(id);
	}

	@Override
	public List<PersonLinks> getEntitiesList(String personId) throws ComponentAssemblyException {
		return personLinksDAO.getListByPersonId(personId);
	}

	@Override
	public void save(PersonLinks entity) throws ComponentWriteException {
		personLinksDAO.insert(entity);
	}

	@Override
	public List<PersonLinks> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		
        List<PersonLinks> returnList = new ArrayList<>();
        
        if(entitiesCount != 0){
            for(Map<String, String> map : list){
            	returnList.add(getEntity(personId, map));
            }
        }   
        
        return returnList;
	}

	@Override
	public PersonLinks getEntity(String personId, Map<String, String> map) {
		
		String id = Utils.getRealId(map.get("linkId"));
		LinkIcon linkIcon = LinkIcon.getIconObject(map.get("linkType"));
		String link = map.get("link");
		String state = map.get("linkState");

		return new PersonLinks(id, personId, linkIcon, link, state);

	}

	@Override
	public void update(PersonLinks entity) throws ComponentWriteException {
        personLinksDAO.update(entity);
	}

	@Override
	public void update(List<PersonLinks> entities) throws ComponentWriteException {
        personLinksDAO.update(entities);
	}

	@Override
	public void delete(PersonLinks entity) throws ComponentWriteException {
		String id = entity.getId();
        personLinksDAO.delete(id);
	}

	@Override
	public void delete(List<PersonLinks> entities) throws ComponentWriteException {
	      String[] ids = Utils.getIdsByComponents(entities);
	      personLinksDAO.delete(ids); 
	}
    
    
	
}
