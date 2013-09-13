package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.SelectDBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.RegionDAO;
import com.arthurspirke.cvcreator.entity.support.Places;
import com.arthurspirke.cvcreator.entity.support.Region;

public class JdbcRegionDAO implements RegionDAO{
	
  private static final String GET_REGION_LIST_BY_COUNTRY_ID = "SELECT * FROM region_ WHERE id_country= ?";
  private static final String GET_REGION_BY_ID = "SELECT * FROM region_ WHERE id= ?";
  private static final String GET_ALL_REGIONS = "SELECT * FROM region_";
  Logger log = Logger.getLogger(JdbcRegionDAO.class);



@Override
public Region getById(int id) {
	  Region region = null;
  
	  try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_REGION_BY_ID)){
        
		pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
		while(rs.next()){
		   region = new Region(rs.getInt("id"), rs.getInt("id_country"), rs.getString("region_name_ru"), rs.getString("region_name_en"));
		}
		
		return region;  
	  }catch (SQLException ex){
		  log.error("Error - " + ex);
		return region;
	  }
	  
}

@Override
public List<Places> getListByMainPlaceId(int mainPlaceId) {	
	  
	  try (Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_REGION_LIST_BY_COUNTRY_ID)){ 
		 
		  List<Places> listOfRegions = new ArrayList<>();
		  
		  pstmt.setInt(1, mainPlaceId);
		  ResultSet rs = pstmt.executeQuery();
		  
		  while(rs.next()){
			  listOfRegions.add(new Region(rs.getInt("id"), mainPlaceId, rs.getString("region_name_ru"), rs.getString("region_name_en")));
		  }
		  
		  return listOfRegions;
		  
	  } catch (SQLException ex){
		  
		  log.error("Error - " + ex);
		  return Collections.EMPTY_LIST;
		  
	  }

}

@Override
public List<Places> getPlacesList() {
   try(Connection conn = SelectDBConnection.getMySQLConnection(); Statement stmt = conn.createStatement()){
	   
	   List<Places> regionList = new ArrayList<>();
	   
	   ResultSet rs = stmt.executeQuery(GET_ALL_REGIONS);
	   
	   while(rs.next()){
		   regionList.add(new Region(rs.getInt("id"), rs.getInt("id_country"), rs.getString("region_name_ru"), rs.getString("region_name_en")));
	   }
	   
	   return regionList;
	   
   } catch(SQLException ex){
	   //throw my exception
	   return null;
   }
}

  
}
