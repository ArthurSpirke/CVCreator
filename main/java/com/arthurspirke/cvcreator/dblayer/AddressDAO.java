package com.arthurspirke.cvcreator.dblayer;

import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;

public interface AddressDAO extends MainDAO<Address> {

	public Address getByHostId(String hostId) throws ComponentAssemblyException;
}
