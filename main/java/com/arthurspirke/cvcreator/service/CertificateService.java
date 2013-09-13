package com.arthurspirke.cvcreator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.factories.DAOFactoryProducer;
import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.ImageSize;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class CertificateService implements DBService<Certificate>, FactoryService<Certificate>{
	
	CertificateDAO certificateDAO = DAOFactoryProducer.getFactory(EntityType.CERTIFICATE).getCertificateDAO();

	@Override
	public Certificate getEntity(String id) throws ComponentAssemblyException {
		return certificateDAO.getById(id);
	}

	@Override
	public List<Certificate> getEntitiesList(String personId) throws ComponentAssemblyException {
		return certificateDAO.getListByPersonId(personId);
	}

	@Override
	public void save(Certificate entity) throws ComponentWriteException {
		certificateDAO.insert(entity);
	}

	@Override
	public void save(List<Certificate> entities) throws ComponentWriteException {
        certificateDAO.insert(entities);
	}

	@Override
	public List<Certificate> getEntitiesList(int entitiesCount, String personId, List<Map<String, String>> list) {
		List<Certificate> returnList = new ArrayList<>();
		if(entitiesCount != 0){
			for(Map<String, String> map : list){
	             returnList.add(getEntity(personId, map));
			}
		}	
		return returnList;
	}

	@Override
	public Certificate getEntity(String personId, Map<String, String> map) {

		String id = Utils.getRealId(map.get("certId"));
		ImageSize imageSize = ImageSize.getType(map.get("certSize"));
		String pathToImage = map.get("pathToImages");
		String shortDesc = map.get("certSmallDesc");
		String fullDesc = map.get("certFullDesc");
		String state = map.get("certState");
		
		return new Certificate(id, personId, imageSize, pathToImage, shortDesc, fullDesc, state);

	}

	@Override
	public void update(Certificate entity) throws ComponentWriteException {
         certificateDAO.update(entity);
	}

	@Override
	public void update(List<Certificate> entities) throws ComponentWriteException {
         certificateDAO.update(entities);
	}

	@Override
	public void delete(Certificate entity) throws ComponentWriteException {
		String id = entity.getId();
        certificateDAO.delete(id);
	}

	@Override
	public void delete(List<Certificate> entities) throws ComponentWriteException {
	      String[] ids = Utils.getIdsByComponents(entities);	       
	      certificateDAO.delete(ids); 
	}
	
	
}
