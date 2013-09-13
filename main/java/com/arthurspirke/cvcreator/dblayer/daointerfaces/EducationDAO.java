package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface EducationDAO{

	public Education getById(String id) throws ComponentAssemblyException;
	public List<Education> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Education entity) throws ComponentWriteException;
	public void insert(List<Education> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Education entity)throws ComponentWriteException;
	public void update(List<Education> list)throws ComponentWriteException;
}
