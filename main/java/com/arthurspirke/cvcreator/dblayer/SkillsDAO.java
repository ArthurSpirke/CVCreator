package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.Skills;

public class SkillsDAO {
	private final static String INSERT_FULL_SKILLS_TO_PERSON = "INSERT INTO skills (person_id, name_of_skills, skills_values) VALUES (?,?,?)";
	private final static String UPDATE_FULL_SKILLS_BY_ID = "UPDATE skills SET name_of_skills= ?, skills_values= ? WHERE id = ?";
	private final static String DELETE_SKILLS_BY_ID = "DELETE FROM skills WHERE id = ?";
	private final static String GET_SKILLS_BY_PERSON_ID = "SELECT * FROM skills WHERE person_id= ?";
	
	Logger log = Logger.getLogger(SkillsDAO.class);

	public void addSkills(int personId, List<Skills> skills){
    	try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_SKILLS_TO_PERSON)){    	
    		for(Skills skill : skills){
    			pstmt.setInt(1, personId);
    			pstmt.setString(2, skill.getSkillsName());
    			pstmt.setString(3, skill.getSkillsValue());
    			pstmt.execute();
    			
    		}
    		
    	} catch(SQLException ex){
    		log.error("Error - " + ex);
    	}
    }
	
	public List<Skills> getSkillsByPersonId(int personId){
        List<Skills> skills = new ArrayList<>();

		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_SKILLS_BY_PERSON_ID)){
	        pstmt.setInt(1, personId);
	        pstmt.execute();
	        ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()){
                skills.add(new Skills(rs.getInt("id"), rs.getInt("person_id"), rs.getString("name_of_skills"), rs.getString("skills_values")));
			}
			
            
            
		} catch(Exception ex){
			log.error("Error - " + ex);
		}
		
		return skills;
	}
		
	public void updateSkillEntityById(List<Skills> skills){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_SKILLS_BY_ID)){
			try{
				for(Skills skill : skills){
					pstmt.setString(1, skill.getSkillsName());
					pstmt.setString(2, skill.getSkillsValue());
					pstmt.setInt(3, skill.getId());
					pstmt.execute();
				}
			} catch(SQLException ex){
				conn.rollback();
			}
		} catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	public void deleteSkillEntity(List<Skills> skills){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_SKILLS_BY_ID)){
			try{
				for(Skills skill : skills){
					pstmt.setInt(1, skill.getId());
					pstmt.execute();
				}
			} catch(SQLException ex){
				conn.rollback();
			}
		} catch (SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
}
