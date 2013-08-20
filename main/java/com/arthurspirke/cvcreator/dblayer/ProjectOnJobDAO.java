package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;

public class ProjectOnJobDAO {
	Logger log = Logger.getLogger(ProjectOnJobDAO.class);
    private final static String INSERT_FULL_PROJECT_TO_COMPANY = "INSERT INTO projects (person_id, company_id, proj_title, proj_years, proj_position, proj_description) VALUES (?,?,?,?,?,?)";
	private final static String DELETE_FULL_PROJECT_BY_ID = "DELETE FROM projects WHERE id= ?";
    private final static String UPDATE_FULL_PROJECT_BY_ID = "UPDATE projects SET proj_title= ?, proj_years= ?, proj_position= ?, proj_description= ? WHERE id= ?";
	private final static String GET_PROJECT_BY_COMPANY_ID = "SELECT * FROM projects WHERE company_id= ?";
    
	public void addProjectOnJob(Integer personId, Integer companyId, List<ProjectOnJob> projectOnJob){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PROJECT_TO_COMPANY)){
			
			for(ProjectOnJob proj : projectOnJob){
				pstmt.setInt(1, personId);
				pstmt.setInt(2, companyId);
				pstmt.setString(3, proj.getProjTitle());
				pstmt.setString(4, proj.getProjYears());
				pstmt.setString(5, proj.getProjPosition());
				pstmt.setString(6, proj.getProjDescription());
				pstmt.execute();
			}
			
		} catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	public List<ProjectOnJob> getProjectsOnJobByCompanyId(Integer companyId){
		List<ProjectOnJob> returnList = new ArrayList<>();
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PROJECT_BY_COMPANY_ID)){
            pstmt.setInt(1, companyId);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
			while(rs.next()){
			    returnList.add(new ProjectOnJob(rs.getInt("id"), rs.getInt("company_id"), rs.getInt("person_id"), rs.getString("proj_title"), rs.getString("proj_position"), rs.getString("proj_years"), rs.getString("proj_description")));	
			}
			
			return returnList;
			
		} catch(SQLException ex){
			log.error("Error - " + ex);
		}
		return returnList;
	}
	
	public void updateProjectsOnJobById(List<ProjectOnJob> projectList){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PROJECT_BY_ID)){
			for(ProjectOnJob proj : projectList){
				pstmt.setString(1, proj.getProjTitle());
				pstmt.setString(2, proj.getProjYears());
				pstmt.setString(3, proj.getProjPosition());
				pstmt.setString(4, proj.getProjDescription());
				pstmt.setInt(5, proj.getId());
				pstmt.execute();
			}
		} catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	public void deleteProjectsOnJobById(List<ProjectOnJob> projectList){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_FULL_PROJECT_BY_ID)){
            for(ProjectOnJob proj : projectList){
            	pstmt.setInt(1, proj.getId());
            	pstmt.execute();
            }
		} catch (SQLException ex){
			log.error("Error - " + ex);
		}
	}
}
