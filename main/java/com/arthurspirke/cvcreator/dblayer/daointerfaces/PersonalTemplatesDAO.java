package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface PersonalTemplatesDAO{
	
	public PersonalTemplates getById(String id) throws ComponentAssemblyException;
	public List<PersonalTemplates> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(PersonalTemplates entity) throws ComponentWriteException;
	public void insert(List<PersonalTemplates> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(PersonalTemplates entity)throws ComponentWriteException;
	public void update(List<PersonalTemplates> list)throws ComponentWriteException;
}
