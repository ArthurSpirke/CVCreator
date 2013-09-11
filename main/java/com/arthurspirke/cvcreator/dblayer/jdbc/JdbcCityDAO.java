package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.PlacesDAO;
import com.arthurspirke.cvcreator.dblayer.core.SelectDBConnection;
import com.arthurspirke.cvcreator.entity.support.City;
import com.arthurspirke.cvcreator.entity.support.Places;

public class JdbcCityDAO implements PlacesDAO{
   private static final String GET_CITY_LIST_BY_REGION_ID = "SELECT * FROM city_ WHERE id_region= ?";
   private static final String GET_CITY_BY_ID = "SELECT * FROM city_ WHERE city_.id= ?";
   private static final String GET_ALL_CITIES = "SELECT * FROM city_";
   Logger log = Logger.getLogger(JdbcCityDAO.class);



@Override
public City getById(int id) {
	
	   City city = null;

	   try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CITY_BY_ID)){
		   pstmt.setInt(1, id);
		   ResultSet rs = pstmt.executeQuery();
		   
		   while(rs.next()){
			   city = new City(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_country"), rs.getString("city_name_ru"), rs.getString("city_name_en"));			   
		   }
		   
		   return city;
	   }catch (SQLException ex){
		   log.error("Error - " + ex);
		   return city;
	   }

}


@Override
public List<Places> getListByMainPlaceId(int mainPlaceId) {
	  
	   try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CITY_LIST_BY_REGION_ID)){
		   
		   List<Places> listOfCities = new ArrayList<>();
		   
		   pstmt.setInt(1, mainPlaceId);
		   ResultSet rs = pstmt.executeQuery();

		   while(rs.next()){
			   listOfCities.add(new City(rs.getInt("id"), 0, 0, rs.getString("city_name_ru"), rs.getString("city_name_en")));
		   }
		   
		   return listOfCities;
		   
	   }catch(SQLException ex){
		   //my Exception
		   return null;
	   }

}


@Override
public List<Places> getPlacesList() {
       try(Connection conn = SelectDBConnection.getMySQLConnection(); Statement stmt = conn.createStatement()){
    	   
    	   List<Places> cities = new ArrayList<>();
    	   
    	   ResultSet rs = stmt.executeQuery(GET_ALL_CITIES);
    	   
    	   while(rs.next()){
    		   cities.add(new City(rs.getInt("id"), 0, 0, rs.getString("city_name_ru"), rs.getString("city_name_en")));
    	   }
    	   
    	   return cities;
    	   
       }catch(SQLException ex){
    	   //my Exception
    	   return null;
       }
}

  
}
