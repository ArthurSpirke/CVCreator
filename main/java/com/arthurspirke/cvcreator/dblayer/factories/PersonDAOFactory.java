package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalDAO;

public class PersonDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public PersonalDAO getPersonDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcPersonalDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
