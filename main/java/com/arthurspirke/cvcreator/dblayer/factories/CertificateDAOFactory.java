package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcCertificateDAO;

public class CertificateDAOFactory extends AbstractDAOFactorySupport {

	@Override
	public CertificateDAO getCertificateDAO() {
		switch(DataStorageType.getCurrentStorage()){
		case JDBC:
			return new JdbcCertificateDAO();
		default:
			throw new IllegalArgumentException();
		}
	}

}
