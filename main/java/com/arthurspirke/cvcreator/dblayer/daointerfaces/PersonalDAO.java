package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface PersonalDAO{

	public Person getById(String id) throws ComponentAssemblyException;
	public List<Person> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Person entity) throws ComponentWriteException;
	public void insert(List<Person> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Person entity)throws ComponentWriteException;
	public void update(List<Person> list)throws ComponentWriteException;
}
