package com.arthurspirke.cvcreator.dblayer;

import java.util.List;

import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface MainDAO<T> {
	
	public T getById(String id) throws ComponentAssemblyException;
	public List<T> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(T entity) throws ComponentWriteException;
	public void insert(List<T> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(T entity)throws ComponentWriteException;
	public void update(List<T> list)throws ComponentWriteException;
}
