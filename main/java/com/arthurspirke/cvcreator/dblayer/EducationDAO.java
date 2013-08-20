package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.Education;
import com.arthurspirke.cvcreator.entity.enums.EducationType;

public class EducationDAO {
	private final static String INSERT_FULL_EDUCATION_TO_PERSON = "INSERT INTO education (person_id, edu_type, edu_title, edu_years, edu_degree, country_id, region_id, city_id, edu_description) VALUES (?,?,?,?,?,?,?,?,?)";
	private final static String UPDATE_FULL_EDUCATION_BY_ID = "UPDATE education SET edu_type = ?, edu_title = ?, edu_years = ?, edu_degree = ?, country_id = ?, region_id = ?, city_id = ?, edu_description = ? WHERE id = ?";
	private final static String DELETE_EDUCATION_BY_ID = "DELETE FROM education WHERE id = ?";
	private final static String GET_EDUCATION_BY_PERSON_ID = "SELECT * FROM education WHERE person_id= ?";
	Logger log = Logger.getLogger(EducationDAO.class);

	public void addEducation(int personId, List<Education> education) {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_EDUCATION_TO_PERSON)){
			for (Education edu : education) {
				pstmt.setInt(1, personId);
				pstmt.setString(2, edu.getEduType().getEduTypeName());
				pstmt.setString(3, edu.getEduTitle());
				pstmt.setString(4, edu.getEduYears());
				pstmt.setString(5, edu.getEduDegree());
				pstmt.setInt(6, edu.getEduCountry());
				pstmt.setInt(7, edu.getEduRegion());
				pstmt.setInt(8, edu.getEduCity());
				pstmt.setString(9, edu.getEduDescription());
				pstmt.execute();
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}
	}
	

	public List<Education> getEducationByPersonId(int personId) {
		List<Education> education = new ArrayList<>();

		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_EDUCATION_BY_PERSON_ID)){
            pstmt.setInt(1, personId);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
			while (rs.next()) {
				education.add(new Education(rs.getInt("id"), rs.getInt("person_id"), EducationType.getType(rs.getString("edu_type")), rs.getString("edu_title"), rs.getString("edu_years"), rs.getString("edu_degree"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getInt("city_id"), rs.getString("edu_description")));
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}

		return education;
	}

	
	public void updateEducationEntityBy(List<Education> education){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_EDUCATION_BY_ID)){
			try{
			for(Education edu : education){
				pstmt.setString(1, edu.getEduType().getEduTypeName());
				pstmt.setString(2, edu.getEduTitle());
				pstmt.setString(3, edu.getEduYears());
				pstmt.setString(4, edu.getEduDegree());
				pstmt.setInt(5, edu.getEduCountry());
				pstmt.setInt(6, edu.getEduRegion());
				pstmt.setInt(7, edu.getEduCity());
				pstmt.setString(8, edu.getEduDescription());
				pstmt.setInt(9, edu.getId());
				pstmt.execute();
			}
			} catch(SQLException ex){
				conn.rollback();
			}
		} catch (SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	public void deleteEducationEntity(List<Education> education){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_EDUCATION_BY_ID)){
			try{
                for(Education edu : education){
                	pstmt.setInt(1, edu.getId());
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
