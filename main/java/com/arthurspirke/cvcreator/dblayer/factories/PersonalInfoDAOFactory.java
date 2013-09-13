package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalInfoDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalInfoDAO;

public class PersonalInfoDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public PersonalInfoDAO getPersonalInfoDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcPersonalInfoDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

	
}
