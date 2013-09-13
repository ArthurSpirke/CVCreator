package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.EmploymentHistoryDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcEmploymentHistoryDAO;

public class EmploymentHistoryDAOFactory extends AbstractDAOFactorySupport {
	
	@Override
	public EmploymentHistoryDAO getEmploymentHistoryDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcEmploymentHistoryDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
