package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.City;
import com.arthurspirke.cvcreator.entity.Places;

public class CityDAO {
   private static final String GET_CITY_LIST_BY_REGION_ID = "SELECT * FROM city_ WHERE id_region= ?";
   private static final String GET_CITY_BY_ID = "SELECT * FROM city_ WHERE city_.id= ?";
   Logger log = Logger.getLogger(CityDAO.class);
	
   public List<Places> getCityListByRegionId(Integer regionId){
	   List<Places> listOfCities = new ArrayList<>();

	   
	   try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CITY_LIST_BY_REGION_ID)){
		   pstmt.setInt(1, regionId);
		   pstmt.execute();
		   ResultSet rs = pstmt.getResultSet();
		   while(rs.next()){
			   listOfCities.add(new City(rs.getInt("id"), 0, 0, rs.getString("city_name_ru"), rs.getString("city_name_en")));
		   }
	   }catch(SQLException ex){
		   log.error("Error - " + ex);
	   }
	   
	   return listOfCities;
   }
   
   public City getCityById(Integer cityId){
	   City city = null;

	   try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CITY_BY_ID)){
		   pstmt.setInt(1, cityId);
		   pstmt.execute();
		   ResultSet rs = pstmt.getResultSet();
		   while(rs.next()){
			   city = new City(rs.getInt("id"), rs.getInt("id_region"), rs.getInt("id_country"), rs.getString("city_name_ru"), rs.getString("city_name_en"));			   
		   }
	   }catch (SQLException ex){
		   log.error("Error - " + ex);
	   }
	   
	   return city;
   }
}
