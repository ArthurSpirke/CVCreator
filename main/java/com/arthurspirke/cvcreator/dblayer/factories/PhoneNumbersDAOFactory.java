package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.PhoneNumbersDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPhoneNumbersDAO;

public class PhoneNumbersDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public PhoneNumbersDAO getPhoneNumbersDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcPhoneNumbersDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
