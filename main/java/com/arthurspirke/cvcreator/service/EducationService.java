package com.arthurspirke.cvcreator.service;

import static com.arthurspirke.cvcreator.util.Utils.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.AddressDAO;
import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcAddressDAO;
import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.Component;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.enums.EducationType;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class EducationService implements DBService<Education>, FactoryService<Education>{
	MainDAO<Education> educationDAO = DAOFactory.getDAO(EntityType.EDUCATION);
    AddressService addressService = new AddressService();

	@Override
	public Education getEntity(String id) throws ComponentAssemblyException {
		Education education = educationDAO.getById(id);
		
		Address address = addressService.getAddressByHost(education.getId());
		education.setAddress(address);
		
		return education;
	}

	@Override
	public List<Education> getEntitiesList(String personId) throws ComponentAssemblyException {
		List<Education> educations = educationDAO.getListByPersonId(personId);
		
		for(Education education : educations){
			Address address = addressService.getAddressByHost(education.getId());
			education.setAddress(address);
		}
		
		return educationDAO.getListByPersonId(personId);
	}

	@Override
	public void save(Education entity) throws ComponentWriteException {
		educationDAO.insert(entity);
		addressService.save(entity.getAddress());
	}

	@Override
	public void save(List<Education> entities) throws ComponentWriteException {
        educationDAO.insert(entities);
        addressService.save(addressService.getAddressesFromEducations(entities));
	}

	@Override
	public List<Education> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		List<Education> returnList = new ArrayList<>();
		
		if(entitiesCount != 0){
			for(Map<String, String> map : list){
				returnList.add(getEntity(personId, map));
			}	
		}
		
		return returnList;
	}

	@Override
	public Education getEntity(String personId, Map<String, String> map) {
		
		String eduId = Utils.getRealId(map.get("eduId"));
		EducationType educationType = EducationType.getType(map.get("eduType"));
		String title = map.get("eduTitle");
		String years = map.get("eduYears");
		String degree = map.get("eduDegree");
		String description = map.get("eduDescription");
		String state = map.get("eduState");
		
        Address address = getEduAddress(eduId, personId, map);

	    return new Education(eduId, personId, educationType, title, years, degree, address, description, state);
	}
	
	//TODO: right place?
	private Address getEduAddress(String hostId, String personId, Map<String, String> map){
	
		
		String id = Utils.getRealId(map.get("id"));
		int countryId = getInteger(map.get("countryId"));
		int regionId = getInteger(map.get("regionId"));
		int cityId = getInteger(map.get("cityId"));
		Language lang = Language.getLanguage(map.get("prefLang"));
		String state = map.get("state");
		
		return new Address(id, hostId, personId, countryId, regionId, cityId, lang, state);
	}

	@Override
	public void update(Education entity) throws ComponentWriteException {
        educationDAO.update(entity);
        addressService.update(entity.getAddress());
	}

	@Override
	public void update(List<Education> entities) throws ComponentWriteException {
       educationDAO.update(entities);
       addressService.update(addressService.getAddressesFromEducations(entities));
	}

	@Override
	public void delete(Education entity) throws ComponentWriteException {
       String id = entity.getId();
       Address address = entity.getAddress();
       
       educationDAO.delete(id);
       addressService.delete(address);
	}

	@Override
	public void delete(List<Education> entities) throws ComponentWriteException {
	       String[] ids = Utils.getIdsByComponents(entities);
	       List<Address> addresses = addressService.getAddressesFromEducations(entities);
	       
	       addressService.delete(addresses);
	       educationDAO.delete(ids);      
	      
	}
	
	

}
