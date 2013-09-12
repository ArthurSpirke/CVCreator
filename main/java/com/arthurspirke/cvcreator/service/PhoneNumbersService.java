package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class PhoneNumbersService implements DBService<PhoneNumbers>, FactoryService<PhoneNumbers>{
	MainDAO<PhoneNumbers> phoneNumbersDAO = DAOFactory.getDAO(EntityType.PHONE_NUMBERS);


	@Override
	public void save(List<PhoneNumbers> personNumbers) throws ComponentWriteException {
		phoneNumbersDAO.insert(personNumbers);
	}

	@Override
	public PhoneNumbers getEntity(String id) throws ComponentAssemblyException {
		return phoneNumbersDAO.getById(id);
	}

	@Override
	public List<PhoneNumbers> getEntitiesList(String personId) throws ComponentAssemblyException {
		return phoneNumbersDAO.getListByPersonId(personId);
	}

	@Override
	public void save(PhoneNumbers entity) throws ComponentWriteException {
		phoneNumbersDAO.insert(entity);
	}

	@Override
	public List<PhoneNumbers> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		
		List<PhoneNumbers> returnList = new ArrayList<>();
		
		if(entitiesCount != 0){
			for(Map<String, String> map : list){
				returnList.add(getEntity(personId, map));	
			}	
		}

		return returnList;
	}

	@Override
	public PhoneNumbers getEntity(String personId, Map<String, String> map) {
		
		String id = Utils.getRealId(map.get("phId"));
		PhoneIcon phoneIcon = PhoneIcon.getPhoneIcon(map.get("phoneType"));
		String number = map.get("number");
		String state = map.get("phState");

		return new PhoneNumbers(id, personId, phoneIcon, number, state);

	}

	@Override
	public void update(PhoneNumbers entity) throws ComponentWriteException {
       phoneNumbersDAO.update(entity);
	}

	@Override
	public void update(List<PhoneNumbers> entities) throws ComponentWriteException {
	   phoneNumbersDAO.update(entities);
	}

	@Override
	public void delete(PhoneNumbers entity) throws ComponentWriteException {
	   String id = entity.getId();
       phoneNumbersDAO.delete(id);
	}

	@Override
	public void delete(List<PhoneNumbers> entities) throws ComponentWriteException {
       String[] ids = Utils.getIdsByComponents(entities);       
       phoneNumbersDAO.delete(ids);
	}

}
