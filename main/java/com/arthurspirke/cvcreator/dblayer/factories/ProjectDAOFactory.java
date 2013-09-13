package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.ProjectDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcProjectDAO;

public class ProjectDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public ProjectDAO getProjectDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcProjectDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
