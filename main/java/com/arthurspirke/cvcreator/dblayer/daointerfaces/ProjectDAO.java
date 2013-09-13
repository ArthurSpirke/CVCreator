package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface ProjectDAO{

	public Project getById(String id) throws ComponentAssemblyException;
	public List<Project> getListByHostId(String hostId) throws ComponentAssemblyException;
	public List<Project> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Project entity) throws ComponentWriteException;
	public void insert(List<Project> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Project entity)throws ComponentWriteException;
	public void update(List<Project> list)throws ComponentWriteException;
}
