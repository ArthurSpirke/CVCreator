package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.Country;
import com.arthurspirke.cvcreator.entity.Places;

public class CountryDAO {
  private static final String GET_COUNTRY_BY_ID = "SELECT * FROM country_ WHERE country_.id= ?";
  private static final String GET_COUNTRY_LIST="SELECT * FROM selectBase.country_";
  Logger log = Logger.getLogger(CountryDAO.class);
  
  public Country getCountryById(Integer id){
	  Country country = null;

	  
	  try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_COUNTRY_BY_ID)){
          pstmt.setInt(1, id);
          pstmt.execute();
          ResultSet rs = pstmt.getResultSet();
          rs.next();
		  country = new Country(rs.getInt("id"), rs.getString("country_name_ru"), rs.getString("country_name_en"));

	  } catch (SQLException ex){
		 log.error("Error - " + ex);
	  }
	  
	  return country;
  }
  
  public List<Places> getListOfCountries(){
	  List<Places> listOfCountries = new ArrayList<>();
	  
	  
	  try(Connection conn = SelectDBConnection.getMySQLConnection(); Statement stmt = conn.createStatement()){
		  ResultSet rs = stmt.executeQuery(GET_COUNTRY_LIST);
		  
		  while(rs.next()){
			  listOfCountries.add(new Country(rs.getInt("id"), rs.getString("country_name_ru"), rs.getString("country_name_en")));
		  }
	  } catch (SQLException ex){
		  log.error("Error - " + ex);
	  }
	  
	  return listOfCountries;
  }
}
