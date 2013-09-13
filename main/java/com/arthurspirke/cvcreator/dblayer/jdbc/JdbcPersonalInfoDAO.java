package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalInfoDAO;
import com.arthurspirke.cvcreator.entity.business.PersonalInfo;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcPersonalInfoDAO implements PersonalInfoDAO {
    private static final String INSERT_PERSONAL_INFO = "INSERT INTO person_info (id, person_id, first_name, last_name, claim_position, e_mail, person_profile, hobbies) value (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_PERSONAL_INFO = "UPDATE person_info SET first_name= ?, last_name= ?, claim_position= ?, e_mail= ?, person_profile= ?, hobbies= ? WHERE id= ?";
    private static final String DELETE_PERSONAL_INFO = "DELETE FROM person_info WHERE id= ?";
    private static final String GET_PERSONAL_INFO_BY_ID = "SELECT * FROM person_info WHERE id= ?";
    private static final String GET_PERSONAL_INFO_BY_PERSON_ID = "SELECT * FROM person_info WHERE person_id= ?";
    
    
	@Override
	public PersonalInfo getById(String id) throws ComponentAssemblyException{
            try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PERSONAL_INFO_BY_ID)){
            	
            	pstmt.setString(1, id);
            	ResultSet rs = pstmt.executeQuery();
            	
            	rs.next();
            	return new PersonalInfo(rs.getString("id"), rs.getString("person_id"), rs.getString("first_name"), rs.getString("second_name"), rs.getString("claim_position"), rs.getString("e_mail"), rs.getString("person_profile"), rs.getString("hobbies"));
            
            } catch(SQLException ex){
            	throw new ComponentAssemblyException();
            }
	}

	@Override
	public List<PersonalInfo> getListByPersonId(String personId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void insert(PersonalInfo entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_PERSONAL_INFO)){
			
			pstmt.setString(1, entity.getId());
			pstmt.setString(2, entity.getPersonId());
			pstmt.setString(3, entity.getFirstName());
			pstmt.setString(4, entity.getSecondName());
			pstmt.setString(5, entity.getClaimPosition());
			pstmt.setString(6, entity.geteMail());
			pstmt.setString(7, entity.getProfile());
			pstmt.setString(8, entity.getHobbies());
			pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void insert(List<PersonalInfo> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_PERSONAL_INFO)){
			
			for(PersonalInfo entity : list){
				
				pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getFirstName());
				pstmt.setString(4, entity.getSecondName());
				pstmt.setString(5, entity.getClaimPosition());
				pstmt.setString(6, entity.geteMail());
				pstmt.setString(7, entity.getProfile());
				pstmt.setString(8, entity.getHobbies());
				pstmt.execute();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSONAL_INFO)){
			
			pstmt.setString(1, id);
			pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSONAL_INFO)){
			
			for(String id : ids){
				
				pstmt.setString(1, id);
				pstmt.execute();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void update(PersonalInfo entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSONAL_INFO)){
			
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getSecondName());
			pstmt.setString(3, entity.getClaimPosition());
			pstmt.setString(4, entity.geteMail());
			pstmt.setString(5, entity.getProfile());
			pstmt.setString(6, entity.getHobbies());
			pstmt.setString(7, entity.getId());
			pstmt.executeUpdate();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void update(List<PersonalInfo> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSONAL_INFO)){
			
			for(PersonalInfo entity : list){
				
				pstmt.setString(1, entity.getFirstName());
				pstmt.setString(2, entity.getSecondName());
				pstmt.setString(3, entity.getClaimPosition());
				pstmt.setString(4, entity.geteMail());
				pstmt.setString(5, entity.getProfile());
				pstmt.setString(6, entity.getHobbies());
				pstmt.setString(7, entity.getId());
				pstmt.executeUpdate();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

}
