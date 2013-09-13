package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.LinksToFiles;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface LinksToFilesDAO{

	public LinksToFiles getById(String id) throws ComponentAssemblyException;
	public List<LinksToFiles> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(LinksToFiles entity) throws ComponentWriteException;
	public void insert(List<LinksToFiles> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(LinksToFiles entity)throws ComponentWriteException;
	public void update(List<LinksToFiles> list)throws ComponentWriteException;
	
}
