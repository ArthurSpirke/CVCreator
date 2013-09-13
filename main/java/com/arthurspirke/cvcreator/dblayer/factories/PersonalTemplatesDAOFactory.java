package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalTemplatesDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalTemplatesDAO;

public class PersonalTemplatesDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public PersonalTemplatesDAO getPersonalTemplatesDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcPersonalTemplatesDAO();
		default:
			throw new IllegalArgumentException();
		}
	}


}
