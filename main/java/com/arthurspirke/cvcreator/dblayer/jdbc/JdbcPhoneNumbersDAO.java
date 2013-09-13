package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PhoneNumbersDAO;
import com.arthurspirke.cvcreator.entity.business.PhoneNumbers;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

import static com.arthurspirke.cvcreator.entity.enums.PhoneIcon.*;


public class JdbcPhoneNumbersDAO implements PhoneNumbersDAO{
	private final static String INSERT_FULL_PHONE_NUMBERS_TO_PERSON = "INSERT INTO phone_numbers (id, person_id, type, value) VALUES (?,?,?,?)";
	private final static String UPDATE_FULL_PHONE_NUMBERS_BY_ID = "UPDATE phone_numbers SET value = ?, type= ? WHERE id = ?";
	private final static String DELETE_PHONE_NUMBERS_BY_ID = "DELETE FROM phone_numbers WHERE id = ?";
	private final static String GET_PHONE_NUMBER_BY_PERSON_ID = "SELECT * FROM phone_numbers WHERE person_id= ?";
	private final static String GET_PHONE_NUMBER_BY_ID = "SELECT * FROM phone_numbers WHERE id= ?";
    Logger log = Logger.getLogger(JdbcPhoneNumbersDAO.class);
  

@Override
public PhoneNumbers getById(String id) throws ComponentAssemblyException{

	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PHONE_NUMBER_BY_ID)){
      
		pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        
       return new PhoneNumbers(rs.getString("id"), rs.getString("person_id"), getPhoneIcon(rs.getString("type")), rs.getString("value"));
	  
	  } catch(SQLException ex){
		  
		  throw new ComponentAssemblyException();
		  
	  }
}


@Override
public List<PhoneNumbers> getListByPersonId(String personId) throws ComponentAssemblyException{
	    
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PHONE_NUMBER_BY_PERSON_ID)){
        
		List<PhoneNumbers> phones = new ArrayList<>();
		  
		pstmt.setString(1, personId);
        ResultSet rs = pstmt.executeQuery();
        
		  while(rs.next()){
			  phones.add(new PhoneNumbers(rs.getString("id"), rs.getString("person_id"), getPhoneIcon(rs.getString("type")), rs.getString("value")));
		  }
		  
		  return phones;
		  
	  } catch(SQLException ex){
		  
		  throw new ComponentAssemblyException();
		  
	  }
	  
}


@Override
public void insert(PhoneNumbers entity) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PHONE_NUMBERS_TO_PERSON)){

		      pstmt.setString(1, entity.getId());
			  pstmt.setString(2, entity.getPersonId());
			  pstmt.setString(3, entity.getPhoneType().getIconName());
			  pstmt.setString(4, entity.getPhoneNumber());
			  pstmt.execute();
		  
	  } catch(SQLException ex){
		  throw new ComponentWriteException();
	  }	
	
}


@Override
public void insert(List<PhoneNumbers> list) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PHONE_NUMBERS_TO_PERSON)){
		 
		  for(PhoneNumbers ph : list){
			  
			  pstmt.setString(1, ph.getId());
			  pstmt.setString(2, ph.getPersonId());
			  pstmt.setString(3, ph.getPhoneType().getIconName());
			  pstmt.setString(4, ph.getPhoneNumber());
			  pstmt.execute();
		  }
		  
	  } catch(SQLException ex){
		  throw new ComponentWriteException();
	  }	
}


@Override
public void delete(String id) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PHONE_NUMBERS_BY_ID)){
			  
			  pstmt.setString(1, id);
			  pstmt.execute();

  } catch(SQLException ex){
	  throw new ComponentWriteException();
  }	
	
}


@Override
public void delete(String[] ids) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PHONE_NUMBERS_BY_ID)){

			  for(String id : ids){
				  
				  pstmt.setString(1, id);
				  pstmt.execute();
				  
			  }

	  } catch(SQLException ex){
		  throw new ComponentWriteException();
	  }	
}


@Override
public void update(PhoneNumbers entity) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PHONE_NUMBERS_BY_ID)){

			  pstmt.setString(1, entity.getPhoneNumber());
			  pstmt.setString(2, entity.getPhoneType().getIconName());
			  pstmt.setString(3, entity.getId());
			  pstmt.executeUpdate();

  } catch(SQLException ex){
	  throw new ComponentWriteException();
  }
	
}


@Override
public void update(List<PhoneNumbers> list) throws ComponentWriteException {
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PHONE_NUMBERS_BY_ID)){

			  for(PhoneNumbers number : list){
				  pstmt.setString(1, number.getPhoneNumber());
				  pstmt.setString(2, number.getPhoneType().getIconName());
				  pstmt.setString(3, number.getId());
				  pstmt.executeUpdate();
			  }

	  } catch(SQLException ex){
		  throw new ComponentWriteException();
	  }
}
}
