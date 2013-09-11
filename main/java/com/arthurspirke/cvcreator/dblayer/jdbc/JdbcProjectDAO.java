package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.ProjectDAO;
import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.entity.business.Project;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcProjectDAO implements ProjectDAO{
	Logger log = Logger.getLogger(JdbcProjectDAO.class);
    private final static String INSERT_FULL_PROJECT_TO_COMPANY = "INSERT INTO projects (id, person_id, company_id, title, years, position, description) VALUES (?,?,?,?,?,?,?)";
	private final static String DELETE_FULL_PROJECT_BY_ID = "DELETE FROM projects WHERE id= ?";
    private final static String UPDATE_FULL_PROJECT_BY_ID = "UPDATE projects SET title= ?, years= ?, position= ?, description= ? WHERE id= ?";
	private final static String GET_PROJECT_BY_ID = "SELECT * FROM projects WHERE id= ?";
	private final static String GET_PROJECTS_BY_PERSON_ID = "SELECT * FROM projects WHERE person_id= ?";
    

	@Override
	public Project getById(String id) throws ComponentAssemblyException{
        
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PROJECT_BY_ID)){
			
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            
			return new Project(rs.getString("id"), rs.getString("company_id"), rs.getString("person_id"), rs.getString("title"), rs.getString("position"), rs.getString("years"), rs.getString("description"));	

		} catch(SQLException ex){
			
			throw new ComponentAssemblyException();
			
		}

	}
	

	@Override
	public List<Project> getListByPersonId(String personId) throws ComponentAssemblyException{
		
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PROJECTS_BY_PERSON_ID)){
			
			List<Project> returnList = new ArrayList<>();
			
            pstmt.setString(1, personId);
            ResultSet rs = pstmt.executeQuery();

			while(rs.next()){
			    returnList.add(new Project(rs.getString("id"), rs.getString("company_id"), rs.getString("person_id"), rs.getString("title"), rs.getString("position"), rs.getString("years"), rs.getString("description")));	
			}
			
			return returnList;
			
		} catch(SQLException ex){
			
			throw new ComponentAssemblyException();
			
		}

	}

	@Override
	public void insert(Project entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PROJECT_TO_COMPANY)){
			
			    pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getCompanyId());
				pstmt.setString(4, entity.getTitle());
				pstmt.setString(5, entity.getYears());
				pstmt.setString(6, entity.getPosition());
				pstmt.setString(7, entity.getDescription());
				pstmt.execute();

		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void insert(List<Project> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PROJECT_TO_COMPANY)){
			
			for(Project proj : list){
				
				pstmt.setString(1, proj.getId());
				pstmt.setString(2, proj.getPersonId());
				pstmt.setString(3, proj.getCompanyId());
				pstmt.setString(4, proj.getTitle());
				pstmt.setString(5, proj.getYears());
				pstmt.setString(6, proj.getPosition());
				pstmt.setString(7, proj.getDescription());
				pstmt.execute();
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_FULL_PROJECT_BY_ID)){
            
            	pstmt.setString(1, id);
            	pstmt.execute();

		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_FULL_PROJECT_BY_ID)){
            
			for(String id : ids){
            	pstmt.setString(1, id);
            	pstmt.execute();
			}
			
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(Project entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PROJECT_BY_ID)){
			
				pstmt.setString(1, entity.getTitle());
				pstmt.setString(2, entity.getYears());
				pstmt.setString(3, entity.getPosition());
				pstmt.setString(4, entity.getDescription());
				pstmt.setString(5, entity.getId());
				pstmt.executeUpdate();
	
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}		
	}

	@Override
	public void update(List<Project> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_PROJECT_BY_ID)){
			
			for(Project proj : list){
				
				pstmt.setString(1, proj.getTitle());
				pstmt.setString(2, proj.getYears());
				pstmt.setString(3, proj.getPosition());
				pstmt.setString(4, proj.getDescription());
				pstmt.setString(5, proj.getId());
				pstmt.executeUpdate();
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}		
	}
}
