package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.SkillsDAO;
import com.arthurspirke.cvcreator.entity.business.Skills;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcSkillsDAO implements SkillsDAO{
	private final static String INSERT_FULL_SKILLS_TO_PERSON = "INSERT INTO skills (id, person_id, name, value) VALUES (?,?,?,?)";
	private final static String UPDATE_FULL_SKILLS_BY_ID = "UPDATE skills SET name= ?, value= ? WHERE id = ?";
	private final static String DELETE_SKILLS_BY_ID = "DELETE FROM skills WHERE id = ?";
	private final static String GET_SKILLS_BY_PERSON_ID = "SELECT * FROM skills WHERE person_id= ?";
	private final static String GET_SKILLS_BY_ID = "SELECT * FROM skills WHERE id= ?";
	
	Logger log = Logger.getLogger(JdbcSkillsDAO.class);



	@Override
	public Skills getById(String id) throws ComponentAssemblyException{

		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_SKILLS_BY_ID)){
	        
			pstmt.setString(1, id);
	        ResultSet rs = pstmt.executeQuery();
            rs.next();
            
            return new Skills(rs.getString("id"), rs.getString("person_id"), rs.getString("name"), rs.getString("value"), "");

		} catch(Exception ex){
			
			throw new ComponentAssemblyException();
			
		}
	}

	@Override
	public List<Skills> getListByPersonId(String personId) throws ComponentAssemblyException{
        
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_SKILLS_BY_PERSON_ID)){
	       
			List<Skills> skills = new ArrayList<>();
			pstmt.setString(1, personId);
	        ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
                skills.add(new Skills(rs.getString("id"), rs.getString("person_id"), rs.getString("name"), rs.getString("value"), ""));
			}   
            
			return skills;
			
		} catch(Exception ex){
			
			throw new ComponentAssemblyException();
			
		}

	}

	@Override
	public void insert(Skills entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_SKILLS_TO_PERSON)){    	

			    pstmt.setString(1, entity.getId());
    			pstmt.setString(2, entity.getPersonId());
    			pstmt.setString(3, entity.getSkillsName());
    			pstmt.setString(4, entity.getSkillsValue());
    			pstmt.execute();

    	} catch(SQLException ex){
    		throw new ComponentWriteException();
    	}
		
	}

	@Override
	public void insert(List<Skills> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_SKILLS_TO_PERSON)){    	
    		
			for(Skills skill : list){
				
				pstmt.setString(1, skill.getId());
    			pstmt.setString(2, skill.getPersonId());
    			pstmt.setString(3, skill.getSkillsName());
    			pstmt.setString(4, skill.getSkillsValue());
    			pstmt.execute();
    			
    		}	
    		
    	} catch(SQLException ex){
    		throw new ComponentWriteException();
    	}
		
	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_SKILLS_BY_ID)){

				pstmt.setString(1, id);
				pstmt.execute();

	} catch (SQLException ex){
		throw new ComponentWriteException();
	}	
 }

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_SKILLS_BY_ID)){

				for(String id : ids){
					pstmt.setString(1, id);
					pstmt.execute();
				}

		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
	}

	@Override
	public void update(Skills entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_SKILLS_BY_ID)){

				pstmt.setString(1, entity.getSkillsName());
				pstmt.setString(2, entity.getSkillsValue());
				pstmt.setString(3, entity.getId());
				pstmt.executeUpdate();

	} catch(SQLException ex){
		throw new ComponentWriteException();
	}
		
	}

	@Override
	public void update(List<Skills> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_SKILLS_BY_ID)){

				for(Skills skill : list){
					pstmt.setString(1, skill.getSkillsName());
					pstmt.setString(2, skill.getSkillsValue());
					pstmt.setString(3, skill.getId());
					pstmt.executeUpdate();
				}

		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
	}


	
}
