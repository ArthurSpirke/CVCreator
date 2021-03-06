package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.SkillsDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class SkillsService implements DBService<Skills>, FactoryService<Skills> {
	
	SkillsDAO skillsDAO = DAOFactoryProducer.getFactory(EntityType.SKILLS).getSkillsDAO();

	@Override
	public void save(List<Skills> skills) throws ComponentWriteException {
		skillsDAO.insert(skills);
	}

	@Override
	public Skills getEntity(String id) throws ComponentAssemblyException {
		return skillsDAO.getById(id);
	}

	@Override
	public List<Skills> getEntitiesList(String personId) throws ComponentAssemblyException {
		return skillsDAO.getListByPersonId(personId);
	}

	@Override
	public void save(Skills entity) throws ComponentWriteException {
		skillsDAO.insert(entity);
	}

	@Override
	public List<Skills> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		
	       List<Skills> returnList = new ArrayList<>();
	       
	        if(entitiesCount != 0){
	            for(Map<String, String> map : list){
	            	returnList.add(getEntity(personId, map));
	            }
	        }
	        return returnList;
	}

	@Override
	public Skills getEntity(String personId, Map<String, String> map) {
		
		String id = Utils.getRealId(map.get("skillsId"));
		String name = map.get("skillsName");
		String value = map.get("skillsValue");
		String state = map.get("skillsState");
		
		return new Skills(id, personId, name, value, state);

	}

	@Override
	public void update(Skills entity) throws ComponentWriteException {
       skillsDAO.update(entity);
	}

	@Override
	public void update(List<Skills> entities) throws ComponentWriteException {
       skillsDAO.update(entities);
	}

	@Override
	public void delete(Skills entity) throws ComponentWriteException {
       String id = entity.getId();
       skillsDAO.delete(id);
	}

	@Override
	public void delete(List<Skills> entities) throws ComponentWriteException {
	      String[] ids = Utils.getIdsByComponents(entities);
	      skillsDAO.delete(ids); 
	}
}
