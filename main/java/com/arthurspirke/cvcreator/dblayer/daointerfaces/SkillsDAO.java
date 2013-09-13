package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface SkillsDAO{

	public Skills getById(String id) throws ComponentAssemblyException;
	public List<Skills> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Skills entity) throws ComponentWriteException;
	public void insert(List<Skills> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Skills entity)throws ComponentWriteException;
	public void update(List<Skills> list)throws ComponentWriteException;
}
