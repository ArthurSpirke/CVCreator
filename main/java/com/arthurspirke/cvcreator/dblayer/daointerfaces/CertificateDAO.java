package com.arthurspirke.cvcreator.dblayer.daointerfaces;

import java.util.List;

import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public interface CertificateDAO{
	
	public Certificate getById(String id) throws ComponentAssemblyException;
	public List<Certificate> getListByPersonId(String personId) throws ComponentAssemblyException;
	
	public void insert(Certificate entity) throws ComponentWriteException;
	public void insert(List<Certificate> list)throws ComponentWriteException;
	
	public void delete(String id)throws ComponentWriteException;
	public void delete(String[] ids)throws ComponentWriteException;
	
	public void update(Certificate entity)throws ComponentWriteException;
	public void update(List<Certificate> list)throws ComponentWriteException;
}
