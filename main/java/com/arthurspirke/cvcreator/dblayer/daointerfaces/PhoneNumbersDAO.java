package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface PhoneNumbersDAO{
	
	public PhoneNumbers getById(String id) throws ComponentAssemblyException;
	public List<PhoneNumbers> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(PhoneNumbers entity) throws ComponentWriteException;
	public void insert(List<PhoneNumbers> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(PhoneNumbers entity)throws ComponentWriteException;
	public void update(List<PhoneNumbers> list)throws ComponentWriteException;
}
