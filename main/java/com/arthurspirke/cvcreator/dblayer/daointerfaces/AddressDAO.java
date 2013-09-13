package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface AddressDAO {
	
	public Address getById(String id) throws ComponentAssemblyException;
	public Address getByHostId(String hostId) throws ComponentAssemblyException;
	public List<Address> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Address entity) throws ComponentWriteException;
	public void insert(List<Address> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Address entity)throws ComponentWriteException;
	public void update(List<Address> list)throws ComponentWriteException;
	
}
