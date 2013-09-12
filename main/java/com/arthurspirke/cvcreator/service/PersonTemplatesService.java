package com.arthurspirke.cvcreator.service;

import java.util.List;
import java.util.Map;

import com.arthurspirke.cvcreator.dblayer.DAOFactory;
import com.arthurspirke.cvcreator.dblayer.MainDAO;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.enums.EntityType;
import com.arthurspirke.cvcreator.entity.enums.TemplateDOC;
import com.arthurspirke.cvcreator.entity.enums.TemplateHTML;
import com.arthurspirke.cvcreator.entity.enums.TemplatePDF;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;
import com.arthurspirke.cvcreator.util.Utils;

public class PersonTemplatesService implements DBService<PersonalTemplates>, FactoryService<PersonalTemplates> {

	MainDAO<PersonalTemplates> personalTemplatesDAO = DAOFactory.getDAO(EntityType.PERSONAL_TEMPLATES);

	@Override
	public void save(PersonalTemplates personalTemplates) throws ComponentWriteException {
		personalTemplatesDAO.insert(personalTemplates);
	}

	@Override
	public PersonalTemplates getEntity(String id) throws ComponentAssemblyException {
		return personalTemplatesDAO.getById(id);
	}

	@Override
	public List<PersonalTemplates> getEntitiesList(String personId) throws ComponentAssemblyException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void save(List<PersonalTemplates> entities) throws ComponentWriteException {
		personalTemplatesDAO.insert(entities);
	}

	@Override
	public List<PersonalTemplates> getEntitiesList(int entitiesCount,
			String personId, List<Map<String, String>> list) {
          throw new UnsupportedOperationException();
	}

	@Override
	public PersonalTemplates getEntity(String personId, Map<String, String> map) {
		
		 String id = Utils.getRealId(map.get("id"));
         String pdf = map.get("templatePDF");
         String html = map.get("templateHTML");
         String doc = map.get("templateDOC");
         String state = map.get("state");
         
         return new PersonalTemplates(id, personId, pdf, html, doc, state);
	}

	@Override
	public void update(PersonalTemplates entity) throws ComponentWriteException {
         personalTemplatesDAO.update(entity);
	}

	@Override
	public void update(List<PersonalTemplates> entities) throws ComponentWriteException {
         personalTemplatesDAO.update(entities);
	}

	@Override
	public void delete(PersonalTemplates entity) throws ComponentWriteException {
         String id = entity.getId();
         personalTemplatesDAO.delete(id);
	}

	@Override
	public void delete(List<PersonalTemplates> entities) throws ComponentWriteException {
		 String[] ids = Utils.getIdsByComponents(entities);
         personalTemplatesDAO.delete(ids);
	}

}
