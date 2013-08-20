package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.Places;
import com.arthurspirke.cvcreator.entity.Region;

public class RegionDAO {
	
  private static final String GET_REGION_LIST_BY_COUNTRY_ID = "SELECT * FROM region_ WHERE id_country= ?";
  private static final String GET_REGION_BY_ID = "SELECT * FROM region_ WHERE id= ?";
  Logger log = Logger.getLogger(RegionDAO.class);
	
  public List<Places> getListOfRegionsByCountryId(Integer countryId){
	  List<Places> listOfRegions = new ArrayList<>();
	

	  
	  try (Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_REGION_LIST_BY_COUNTRY_ID)){ 
		  pstmt.setInt(1, countryId);
		  pstmt.execute();
		  ResultSet rs = pstmt.getResultSet();
		  while(rs.next()){
			  listOfRegions.add(new Region(rs.getInt("id"), countryId, rs.getString("region_name_ru"), rs.getString("region_name_en")));
		  }
	  } catch (SQLException ex){
		  log.error("Error - " + ex);
	  }
	  
	  return listOfRegions;
  }
  
  public Region getRegionById(Integer regionId){
	  Region region = null;

	  
	  try(Connection conn = SelectDBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_REGION_BY_ID)){
          pstmt.setInt(1, regionId);
          pstmt.execute();
          ResultSet rs = pstmt.getResultSet();
		  rs.next();		  
		  region = new Region(rs.getInt("id"), rs.getInt("id_country"), rs.getString("region_name_ru"), rs.getString("region_name_en"));
	  }catch (SQLException ex){
		  log.error("Error - " + ex);
	  }
	  
	  return region;
  }
}
