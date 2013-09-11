package com.arthurspirke.cvcreator.dblayer;

import com.arthurspirke.cvcreator.dblayer.enums.DataStorageType;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcAddressDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcCertificateDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcEducationDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcEmploymentHistoryDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalInfoDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPersonalTemplatesDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcPhoneNumbersDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcProjectDAO;
import com.arthurspirke.cvcreator.dblayer.jdbc.JdbcSkillsDAO;
import com.arthurspirke.cvcreator.entity.enums.EntityType;

public class DAOFactory {
     private final static DataStorageType dataStorageType;
	
     static{
    	 dataStorageType = DataStorageType.JDBC;
     }
     
	
	public static <T> MainDAO<T> getDAO(EntityType type){
		switch(dataStorageType){
		case JDBC:
			return getJdbcDAO(type);
		default:
			throw new IllegalArgumentException();
		}
	}
	
	
	//TODO: work with cast problem. Find right solve of this problem. Thanks!
	private static <T> MainDAO<T> getJdbcDAO(EntityType type){
		switch(type){
		case CERTIFICATE:
			return (MainDAO<T>) new JdbcCertificateDAO();
		case EDUCATION:
			return (MainDAO<T>) new JdbcEducationDAO();
		case EMPLOYMENT_HISTORY:
			return (MainDAO<T>) new JdbcEmploymentHistoryDAO();
		case PERSON:
			return (MainDAO<T>) new JdbcPersonalDAO();
		case PHONE_NUMBERS:
			return (MainDAO<T>) new JdbcPhoneNumbersDAO();
		case PERSONAL_LINKS:
			return (MainDAO<T>) new JdbcPersonLinksDAO();
		case PROJECT:
			return (MainDAO<T>) new JdbcProjectDAO();
		case SKILLS:
			return (MainDAO<T>) new JdbcSkillsDAO();
		case ADDRESS:
			return (MainDAO<T>) new JdbcAddressDAO();
		case PERSONAL_INFO:
			return (MainDAO<T>) new JdbcPersonalInfoDAO();
		case PERSONAL_TEMPLATES:
			return (MainDAO<T>) new JdbcPersonalTemplatesDAO();
		default:
			throw new IllegalArgumentException();
		}
	}
	
}
