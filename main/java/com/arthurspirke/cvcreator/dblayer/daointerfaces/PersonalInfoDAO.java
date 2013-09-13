package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface PersonalInfoDAO{

	public PersonalInfo getById(String id) throws ComponentAssemblyException;
	public List<PersonalInfo> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(PersonalInfo entity) throws ComponentWriteException;
	public void insert(List<PersonalInfo> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(PersonalInfo entity)throws ComponentWriteException;
	public void update(List<PersonalInfo> list)throws ComponentWriteException;
}
