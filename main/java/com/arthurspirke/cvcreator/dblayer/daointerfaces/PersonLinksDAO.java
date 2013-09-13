package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface PersonLinksDAO{
	
	public PersonLinks getById(String id) throws ComponentAssemblyException;
	public List<PersonLinks> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(PersonLinks entity) throws ComponentWriteException;
	public void insert(List<PersonLinks> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(PersonLinks entity)throws ComponentWriteException;
	public void update(List<PersonLinks> list)throws ComponentWriteException;
}
