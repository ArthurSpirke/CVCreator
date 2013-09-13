package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.EducationDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcEducationDAO;

public class EducationDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public EducationDAO getEducationDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcEducationDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
