package com.arthurspirke.cvcreator.dblayer.factories;


import com.arthurspirke.cvcreator.dblayer.daointerfaces.RegionDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcRegionDAO;

public class RegionDAOFactory extends AbstractDAOFactorySupport {


	@Override
	public RegionDAO getRegionDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcRegionDAO();
		default:
			throw new IllegalArgumentException();
		}
	}



}
