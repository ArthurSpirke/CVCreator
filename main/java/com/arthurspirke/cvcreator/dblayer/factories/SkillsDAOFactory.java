package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.SkillsDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcSkillsDAO;

public class SkillsDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public SkillsDAO getSkillsDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcSkillsDAO();
		default:
			throw new IllegalArgumentException();
		}
	}


}
