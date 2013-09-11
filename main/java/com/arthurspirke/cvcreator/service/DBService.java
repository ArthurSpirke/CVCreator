package com.arthurspirke.cvcreator.service;

import java.util.List;

import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface DBService<T> {

	public T getEntity(String id) throws ComponentAssemblyException;
	public List<T> getEntitiesList(String personId) throws ComponentAssemblyException;
	public void save(T entity) throws ComponentWriteException;;
	public void save(List<T> entities) throws ComponentWriteException;;
	public void update(T entity) throws ComponentWriteException;;
	public void update(List<T> entities) throws ComponentWriteException;;
	public void delete(T entity) throws ComponentWriteException;;
	public void delete(List<T> entities) throws ComponentWriteException;;
}
