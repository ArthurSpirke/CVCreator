package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.EducationDAO;
import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.entity.business.Education;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

import static com.arthurspirke.cvcreator.entity.enums.EducationType.*;

public class JdbcEducationDAO implements EducationDAO{
	private final static String INSERT_FULL_EDUCATION_TO_PERSON = "INSERT INTO education (id, person_id, type, title, years, degree, description) VALUES (?,?,?,?,?,?,?)";
	private final static String UPDATE_FULL_EDUCATION_BY_ID = "UPDATE education SET type = ?, title = ?, years = ?, degree = ?, description = ? WHERE id = ?";
	private final static String DELETE_EDUCATION_BY_ID = "DELETE FROM education WHERE id = ?";
	private final static String GET_EDUCATION_BY_PERSON_ID = "SELECT * FROM education WHERE person_id= ?";
	private final static String GET_EDUCATION_BY_ID = "SELECT * FROM education WHERE id= ?";
	Logger log = Logger.getLogger(JdbcEducationDAO.class);

	

	@Override
	public Education getById(String id) throws ComponentAssemblyException{
        try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EDUCATION_BY_ID)){
      	  
      	  pstmt.setString(1, id);
      	  ResultSet rs = pstmt.executeQuery();
      	  
      	  return new Education(rs.getString("id"), rs.getString("person_id"), getType(rs.getString("type")), rs.getString("title"), rs.getString("years"), rs.getString("degree"), rs.getString("description"));
        } catch(SQLException ex){
        	 throw new ComponentAssemblyException();
        }
	}


	@Override
	public List<Education> getListByPersonId(String personId) throws ComponentAssemblyException{


		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EDUCATION_BY_PERSON_ID)){
           
			List<Education> education = new ArrayList<>();
			
			pstmt.setString(1, personId);
            ResultSet rs = pstmt.executeQuery();
            
			while (rs.next()) {
				education.add(new Education(rs.getString("id"), rs.getString("person_id"), getType(rs.getString("type")), rs.getString("title"), rs.getString("years"), rs.getString("degree"), rs.getString("description")));
			}
			
			return education;
			
		} catch (SQLException ex) {
			 throw new ComponentAssemblyException();
		}

	}


	@Override
	public void insert(Education entity) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EDUCATION_TO_PERSON)){
			
			    pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getType().getEduTypeName());
				pstmt.setString(4, entity.getTitle());
				pstmt.setString(5, entity.getYears());
				pstmt.setString(6, entity.getDegree());
				pstmt.setString(7, entity.getDescription());
				pstmt.execute();
				
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void insert(List<Education> list) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EDUCATION_TO_PERSON)){
			for (Education edu : list) {
				
				pstmt.setString(1, edu.getId());
				pstmt.setString(2, edu.getPersonId());
				pstmt.setString(3, edu.getType().getEduTypeName());
				pstmt.setString(4, edu.getTitle());
				pstmt.setString(5, edu.getYears());
				pstmt.setString(6, edu.getDegree());
				pstmt.setString(7, edu.getDescription());
				pstmt.execute();
			}
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EDUCATION_BY_ID)){
			
            	pstmt.setString(1, id);
            	pstmt.execute();
            	
	} catch (SQLException ex){
		throw new ComponentWriteException();
	}
		
	}


	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EDUCATION_BY_ID)){
			
                for(String id : ids){
                	pstmt.setString(1, id);
                	pstmt.execute();
                }

		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void update(Education entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EDUCATION_BY_ID)){

				pstmt.setString(1, entity.getType().getEduTypeName());
				pstmt.setString(2, entity.getTitle());
				pstmt.setString(3, entity.getYears());
				pstmt.setString(4, entity.getDegree());
				pstmt.setString(8, entity.getDescription());
				pstmt.setString(9, entity.getId());
				pstmt.executeUpdate();
			
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void update(List<Education> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EDUCATION_BY_ID)){
			
			for(Education edu : list){
				pstmt.setString(1, edu.getType().getEduTypeName());
				pstmt.setString(2, edu.getTitle());
				pstmt.setString(3, edu.getYears());
				pstmt.setString(4, edu.getDegree());
				pstmt.setString(8, edu.getDescription());
				pstmt.setString(9, edu.getId());
				pstmt.executeUpdate();
			}
			
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}


}
