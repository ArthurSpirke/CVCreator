package com.arthurspirke.cvcreator.dblayer.core;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnection {
	private static Logger log = Logger.getLogger(DBConnection.class);
	private static DataSource dataSource;
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	static {
		ResourceBundle res = ResourceBundle.getBundle("com.arthurspirke.cvcreator.properties.prop");
		
		DRIVER = res.getString("jdbc_driver");
		URL = res.getString("jdbc_url_to_db");
		USERNAME = res.getString("jdbc_username");
		PASSWORD = res.getString("jdbc_password");

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
