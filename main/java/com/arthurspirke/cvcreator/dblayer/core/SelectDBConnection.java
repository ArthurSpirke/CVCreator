package com.arthurspirke.cvcreator.dblayer.core;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class SelectDBConnection {
	private static Logger log = Logger.getLogger(DBConnection.class);
	private static DataSource dataSource;
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	
	
	static {		
		final ResourceBundle config = ResourceBundle.getBundle("com.arthurspirke.cvcreator.properties.prop");


		DRIVER = config.getString("jdbc_driver");
		URL = config.getString("select_jdbc_url_to_db");
		USERNAME = config.getString("select_jdbc_username");
		PASSWORD = config.getString("select_jdbc_password");

		dataSource = setupDataSource();

	}

	public static Connection getMySQLConnection() throws SQLException {
		return dataSource.getConnection();
	}

	private static DataSource setupDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(DRIVER);
		} catch (PropertyVetoException ex) {
			log.error("Error - " + ex);
		}

		cpds.setJdbcUrl(URL);
		cpds.setUser(USERNAME);
		cpds.setPassword(PASSWORD);
		cpds.setMinPoolSize(2);
		cpds.setAcquireIncrement(2);
		cpds.setMaxPoolSize(50);
		return cpds;

	}

}
