package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.SelectDBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.CountryDAO;
import com.arthurspirke.cvcreator.entity.support.Country;
import com.arthurspirke.cvcreator.entity.support.Places;

public class JdbcCountryDAO implements CountryDAO{
  private static final String GET_COUNTRY_BY_ID = "SELECT * FROM country_ WHERE country_.id= ?";
  private static final String GET_ALL_COUNTRIES="SELECT * FROM selectBase.country_";
  Logger log = Logger.getLogger(JdbcCountryDAO.class);
  


@Override
public Country getById(int id) {
	  Country country = null;
 
	    try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_COUNTRY_BY_ID)){
       
	    pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            country = new Country(rs.getInt("id"), rs.getString("country_name_ru"), rs.getString("country_name_en"));
        }

           return country;
	  } catch (SQLException ex){
		 log.error("Error - " + ex);
		 return country;
	  }

}

@Override
public List<Places> getPlacesList() {	 
	  	  
	  try(Connection conn = SelectDBConnection.getMySQLConnection(); Statement stmt = conn.createStatement()){
		  List<Places> listOfCountries = new ArrayList<>();
		  
		  ResultSet rs = stmt.executeQuery(GET_ALL_COUNTRIES);
		  
		  while(rs.next()){
			  listOfCountries.add(new Country(rs.getInt("id"), rs.getString("country_name_ru"), rs.getString("country_name_en")));
		  }
		  
		  return listOfCountries;
		  
	  } catch (SQLException ex){
		  //my exception
		  return null;
	  }
	  
}

   
}
