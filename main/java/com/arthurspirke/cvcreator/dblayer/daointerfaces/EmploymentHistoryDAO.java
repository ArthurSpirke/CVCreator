package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface EmploymentHistoryDAO{

	public EmploymentHistory getById(String id) throws ComponentAssemblyException;
	public List<EmploymentHistory> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(EmploymentHistory entity) throws ComponentWriteException;
	public void insert(List<EmploymentHistory> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(EmploymentHistory entity)throws ComponentWriteException;
	public void update(List<EmploymentHistory> list)throws ComponentWriteException;
}
