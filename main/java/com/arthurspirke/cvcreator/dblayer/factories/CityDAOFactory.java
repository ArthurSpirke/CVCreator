package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.CityDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcCityDAO;

public class CityDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public CityDAO getCityDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcCityDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
