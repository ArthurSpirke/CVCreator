package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.enums.PhoneIcon;


public class PhoneNumbersDAO {
	private final static String INSERT_FULL_PHONE_NUMBERS_TO_PERSON = "INSERT INTO phone_numbers (person_id, phone_type, number) VALUES (?,?,?)";
	private final static String UPDATE_FULL_PHONE_NUMBERS_BY_ID = "UPDATE phone_numbers SET number = ?, phone_type= ? WHERE id = ?";
	private final static String DELETE_PHONE_NUMBERS_BY_ID = "DELETE FROM phone_numbers WHERE id = ?";
	private final static String GET_PHONE_NUMBER_BY_PERSON_ID = "SELECT * FROM phone_numbers WHERE person_id= ?";
	
  Logger log = Logger.getLogger(PhoneNumbersDAO.class);
  
  public void addPhoneNumbers(Integer personId, List<PhoneNumbers> phones){
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PHONE_NUMBERS_TO_PERSON)){
		  for(PhoneNumbers ph : phones){
			  pstmt.setInt(1, personId);
			  pstmt.setString(2, ph.getPhoneType().getIconName());
			  pstmt.setString(3, ph.getPhoneNumber());
			  pstmt.execute();
		  }
	  } catch(SQLException ex){
		  log.error("Error - " + ex.getMessage());
	  }
  }
  
  
  public List<PhoneNumbers> getPhoneNumbersByPersonId(Integer personId){
	  List<PhoneNumbers> phones = new ArrayList<>();
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PHONE_NUMBER_BY_PERSON_ID)){
          pstmt.setInt(1, personId);
          pstmt.execute();
          ResultSet rs = pstmt.getResultSet();
		  while(rs.next()){
			  phones.add(new PhoneNumbers(rs.getInt("id"), rs.getInt("person_id"), PhoneIcon.getPhoneIcon(rs.getString("phone_type")), rs.getString("number")));
		  }
	  } catch(SQLException ex){
		  log.error("Error - " + ex.getMessage());
	  }
	  
	  return phones;
  }
  
  
  public void updatePhonesNumbersById(List<PhoneNumbers> listOfNumbers){
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PHONE_NUMBERS_BY_ID)){
		  try{
			  for(PhoneNumbers number : listOfNumbers){
				  pstmt.setString(1, number.getPhoneNumber());
				  pstmt.setString(2, number.getPhoneType().getIconName());
				  pstmt.setInt(3, number.getId());
				  pstmt.execute();
			  }
		  } catch(SQLException ex){
			  conn.rollback();
		  }
	  } catch(SQLException ex){
		  log.error("Error - " + ex);
	  }
  }
  
  public void deletePhonesNumbersById(List<PhoneNumbers> listOfNumbers){
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PHONE_NUMBERS_BY_ID)){
		  try{
			  for(PhoneNumbers number : listOfNumbers){
				  pstmt.setInt(1, number.getId());
				  pstmt.execute();
			  }
		  } catch(SQLException ex){
			  conn.rollback();
		  }
	  } catch(SQLException ex){
		  log.error("Error - " + ex);
	  }
  }
}
