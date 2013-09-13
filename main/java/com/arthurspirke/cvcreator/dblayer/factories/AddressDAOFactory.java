package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.AddressDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcAddressDAO;

public class AddressDAOFactory extends AbstractDAOFactorySupport{

	@Override
	public AddressDAO getAddressDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcAddressDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
