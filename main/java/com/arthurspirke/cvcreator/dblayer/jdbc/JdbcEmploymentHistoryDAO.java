package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.EmploymentHistoryDAO;
import com.arthurspirke.cvcreator.entity.business.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcEmploymentHistoryDAO implements EmploymentHistoryDAO{
	private final static String INSERT_FULL_EMPLOYMENT_HISTORY_TO_PERSON = "INSERT INTO employment_history (id, person_id, title, years, position, description) VALUES (?,?,?,?,?,?)";
	private final static String UPDATE_FULL_EMPLOYMENT_HISTORY_BY_ID = "UPDATE employment_history SET title = ?, years = ?, position = ?, description = ? WHERE id = ?";
	private final static String DELETE_EMPLOYMENT_HISTORY_BY_ID = "DELETE FROM employment_history WHERE id = ?";
	private final static String GET_EMPLOYMENT_BY_PERSON_ID = "SELECT * FROM employment_history WHERE person_id= ?";
	private final static String GET_EMPLOYMENT_HISTORY_BY_ID = "SELECT * FROM employment_history WHERE id= ?";
	
	Logger log = Logger.getLogger(JdbcEmploymentHistoryDAO.class);


	@Override
	public EmploymentHistory getById(String id) throws ComponentAssemblyException{
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EMPLOYMENT_HISTORY_BY_ID)){
			
			pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();

			return new EmploymentHistory(rs.getString("id"), rs.getString("person_id"), rs.getString("title"), rs.getString("years"), rs.getString("position"), rs.getString("description"));
			
		} catch (SQLException ex) {
			throw new ComponentAssemblyException();
		}

	}


	@Override
	public List<EmploymentHistory> getListByPersonId(String personId) throws ComponentAssemblyException{

		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EMPLOYMENT_BY_PERSON_ID)){
			
			List<EmploymentHistory> employment = new ArrayList<>();
			
			pstmt.setString(1, personId);
            ResultSet rs = pstmt.executeQuery();
            
			while (rs.next()) {
				employment.add(new EmploymentHistory(rs.getString("id"), rs.getString("person_id"), rs.getString("title"), rs.getString("years"), rs.getString("position"), rs.getString("description")));

			}
			
			return employment;
			
		} catch (SQLException ex) {
			throw new ComponentAssemblyException();
		}

	}


	@Override
	public void insert(EmploymentHistory entity) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EMPLOYMENT_HISTORY_TO_PERSON)){
			
			    pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getTitle());
				pstmt.setString(4, entity.getYears());
				pstmt.setString(5, entity.getPosition());
				pstmt.setString(6, entity.getDescription());
				pstmt.execute();
								
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void insert(List<EmploymentHistory> list) throws ComponentWriteException {
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EMPLOYMENT_HISTORY_TO_PERSON)){
			
			for (EmploymentHistory emp : list) {
				
				pstmt.setString(1, emp.getId());
				pstmt.setString(2, emp.getPersonId());
				pstmt.setString(3, emp.getTitle());
				pstmt.setString(4, emp.getYears());
				pstmt.setString(5, emp.getPosition());
				pstmt.setString(6, emp.getDescription());
				pstmt.execute();
					
			}
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EMPLOYMENT_HISTORY_BY_ID)){

				pstmt.setString(1, id);
				pstmt.execute();

	} catch (SQLException ex){
		throw new ComponentWriteException();
	}
		
	}


	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EMPLOYMENT_HISTORY_BY_ID)){

				for(String id : ids){
					
					pstmt.setString(1, id);
					pstmt.execute();

				}

		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}


	@Override
	public void update(EmploymentHistory entity) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EMPLOYMENT_HISTORY_BY_ID)){

				pstmt.setString(1, entity.getTitle());
				pstmt.setString(2, entity.getYears());
				pstmt.setString(3, entity.getPosition());
				pstmt.setString(7, entity.getDescription());
		    	pstmt.setString(8, entity.getId());
		    	pstmt.execute();	


	} catch (SQLException ex) {
		throw new ComponentWriteException();
	}
		
	}


	@Override
	public void update(List<EmploymentHistory> list) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EMPLOYMENT_HISTORY_BY_ID)){

			    for(EmploymentHistory emp : list){
					pstmt.setString(1, emp.getTitle());
					pstmt.setString(2, emp.getYears());
					pstmt.setString(3, emp.getPosition());
					pstmt.setString(7, emp.getDescription());
			    	pstmt.setString(8, emp.getId());
			    	pstmt.execute();	
			    	
			    }

		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}
}
