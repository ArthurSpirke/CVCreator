package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class AddressService implements DBService<Address>, FactoryService<Address>{
	
     MainDAO<Address> addressDAO = DAOFactory.getDAO(EntityType.ADDRESS);
  
     
	@Override
	public Address getEntity(String id) throws ComponentAssemblyException {
		return addressDAO.getById(id);
	}

	//TODO: problem method!
	@Override
	public List<Address> getEntitiesList(String personId) throws ComponentAssemblyException {
		return addressDAO.getListByPersonId(personId); 
	}

	@Override
	public void save(List<Address> entities) throws ComponentWriteException {
		addressDAO.insert(entities);
	}
	
    @Override
    public void save(Address address) throws ComponentWriteException {
   	 addressDAO.insert(address);
    }

	@Override
	public List<Address> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
         throw new UnsupportedOperationException();
	}

	@Override
	public Address getEntity(String personId, Map<String, String> map) {
		
		 String id = Utils.getRealId(map.get("id"));
		 int countryId = Utils.getInteger(map.get("countryId"));
		 int regionId = Utils.getInteger(map.get("regionId"));
		 int cityId = Utils.getInteger(map.get("cityId"));
		 int postalCode = Utils.getInteger(map.get("postalCode"));
		 String street = map.get("streetAddress");
		 Language prefLang = Language.getLanguage(map.get("prefLang"));
		 String state = map.get("state");
		 
		return new Address(id, personId, personId, countryId, regionId, cityId, postalCode, street, prefLang, state);
	}

	
	@Override
	public void update(Address entity) throws ComponentWriteException {
        addressDAO.update(entity);
	}

	@Override
	public void update(List<Address> entities) throws ComponentWriteException {
        addressDAO.update(entities);
	}

	@Override
	public void delete(Address entity) throws ComponentWriteException {
        String id = entity.getId();
        addressDAO.delete(id);
	}

	@Override
	public void delete(List<Address> entities) throws ComponentWriteException {
	       String[] ids = new String[entities.size()];
	       int length = ids.length;
	       
	       for(int i = 0; i < length; i++){
	    	   ids[i] = entities.get(i).getId();
	       }
	       
	      addressDAO.delete(ids);
	}


}
