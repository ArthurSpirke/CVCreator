package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.EmploymentHistory;
import com.arthurspirke.cvcreator.entity.domain.ProjectOnJob;
import com.arthurspirke.cvcreator.util.Utils;

public class EmploymentHistoryDAO {
	private final static String INSERT_FULL_EMPLOYMENT_HISTORY_TO_PERSON = "INSERT INTO employment_history (person_id, emp_title, emp_years, emp_position, country_id, region_id, city_id, emp_description) VALUES (?,?,?,?,?,?,?,?)";
	private final static String UPDATE_FULL_EMPLOYMENT_HISTORY_BY_ID = "UPDATE employment_history SET emp_title = ?, emp_years = ?, emp_position = ?, country_id = ?, region_id = ?, city_id = ?, emp_description = ? WHERE id = ?";
	private final static String DELETE_EMPLOYMENT_HISTORY_BY_ID = "DELETE FROM employment_history WHERE id = ?";
	private final static String GET_EMPLOYMENT_BY_PERSON_ID = "SELECT * FROM employment_history WHERE person_id= ?";
	
	Logger log = Logger.getLogger(EmploymentHistoryDAO.class);
    ProjectOnJobDAO projectDAO = new ProjectOnJobDAO();
	
	
	public void addEmploymentHistory(int personId,
			List<EmploymentHistory> employment) {
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EMPLOYMENT_HISTORY_TO_PERSON)){
			for (EmploymentHistory emp : employment) {
				pstmt.setInt(1, personId);
				pstmt.setString(2, emp.getEmpTitle());
				pstmt.setString(3, emp.getEmpYears());
				pstmt.setString(4, emp.getEmpPosition());
				pstmt.setInt(5, emp.getEmpCountry());
				pstmt.setInt(6, emp.getEmpRegion());
				pstmt.setInt(7, emp.getEmpCity());
				pstmt.setString(8, emp.getEmpDescription());
				pstmt.execute();
				
				//TODO: bad implementation
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM cvmaker.employment_history WHERE person_id='" + personId + "' AND emp_title='" + emp.getEmpTitle() + "'");
				rs.next();
				projectDAO.addProjectOnJob(personId, rs.getInt("id"), emp.getProjects());				
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}
	}
	

	public List<EmploymentHistory> getEmploymentHistoryByPersonId(int personId) {
		List<EmploymentHistory> employment = new ArrayList<>();
		List<ProjectOnJob> projectList;
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EMPLOYMENT_BY_PERSON_ID)){
			pstmt.setInt(1, personId);
			pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				projectList = projectDAO.getProjectsOnJobByCompanyId(rs.getInt("id"));
				employment.add(new EmploymentHistory(rs.getInt("id"), rs.getInt("person_id"), rs.getString("emp_title"), rs.getString("emp_years"), rs.getString("emp_position"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getInt("city_id"), rs.getString("emp_description"), projectList));

			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}

		return employment;
	}
	
	
	

	public void updateEmploymentHistoryEntityById(List<EmploymentHistory> employment) {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EMPLOYMENT_HISTORY_BY_ID)){
			try{
			    for(EmploymentHistory emp : employment){
					pstmt.setString(1, emp.getEmpTitle());
					pstmt.setString(2, emp.getEmpYears());
					pstmt.setString(3, emp.getEmpPosition());
					pstmt.setInt(4, emp.getEmpCountry());
					pstmt.setInt(5, emp.getEmpRegion());
					pstmt.setInt(6, emp.getEmpCity());
					pstmt.setString(7, emp.getEmpDescription());
			    	pstmt.setInt(8, emp.getId());
			    	pstmt.execute();	
			    	
			    	log.debug("SIZE in EMP DAO - " + emp.getProjects().size());
                    Utils.projectChangerInDB(emp.getProjects(), emp.getPersonId(), emp.getId());
			    	
			    }
			} catch(SQLException ex){
				conn.rollback();
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}
	}
	
	
	public void deleteEmploymentHistoryEntity(List<EmploymentHistory> employment){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EMPLOYMENT_HISTORY_BY_ID)){
			try{
				for(EmploymentHistory emp : employment){
					pstmt.setInt(1, emp.getId());
					pstmt.execute();
					
					Utils.projectChangerInDB(emp.getProjects(), emp.getPersonId(), emp.getId());
				}
			} catch(SQLException ex){
				conn.rollback();
			}
		} catch (SQLException ex){
			log.error("Error - " + ex);
		}
	}
}
