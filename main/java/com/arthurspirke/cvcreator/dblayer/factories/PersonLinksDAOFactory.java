package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonLinksDAO;

public class PersonLinksDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public PersonLinksDAO getPersonalLinksDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcPersonLinksDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
