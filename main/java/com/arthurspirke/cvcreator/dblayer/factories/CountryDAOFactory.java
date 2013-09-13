package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.CountryDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcCountryDAO;

public class CountryDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public CountryDAO getCountryDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcCountryDAO();
		default:
			throw new IllegalArgumentException();
		}
	}


}
