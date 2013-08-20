package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.PersonalInfo;
import com.arthurspirke.cvcreator.entity.enums.Language;

public class MainPersonalDAO {
	private final static String INSERT_FULL_PERSON = "INSERT INTO person_info (id, pref_lang, first_name, second_name, claim_position, e_mail, country_id, region_id, city_id, address, zip_code, user_profile, hobbies) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";	
	private final static String GET_PERSON_BY_ID = "SELECT * FROM person_info WHERE id= ?";
	private final static String UPDATE_PERSON = "UPDATE person_info SET first_name= ?, second_name= ?, claim_position= ?, user_profile= ?, hobbies= ?, e_mail= ?, country_id= ?, region_id= ?, city_id= ?, address= ?, zip_code= ?, pref_lang= ? WHERE id= ?";
	Logger log = Logger.getLogger(MainPersonalDAO.class);
    
	
	PhoneNumbersDAO phonesDAO = new PhoneNumbersDAO();
	PersonLinksDAO personLinksDAO = new PersonLinksDAO();
	SkillsDAO skillsDAO = new SkillsDAO();
	EducationDAO eduDAO = new EducationDAO();
	EmploymentHistoryDAO empDAO = new EmploymentHistoryDAO();
	CertificateDAO certDAO = new CertificateDAO();
	LinksToFilesDAO linksToFilesDAO = new LinksToFilesDAO();
	
	public boolean addNewPerson(PersonalInfo p) {
		Integer personId = p.getId();

		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PERSON)){
            pstmt.setInt(1, p.getId());
            pstmt.setString(2, p.getPrefLang().getLangName());
            pstmt.setString(3, p.getFirstName());
            pstmt.setString(4, p.getSecondName());
            pstmt.setString(5, p.getClaimPosition());
            pstmt.setString(6, p.geteMail());
            pstmt.setInt(7, p.getCountry());
            pstmt.setInt(8, p.getRegion());
            pstmt.setInt(9, p.getCity());
            pstmt.setString(10, p.getAddress());
            pstmt.setInt(11, p.getZipCode());
            pstmt.setString(12, p.getProfile());
            pstmt.setString(13, p.getHobbies());
            
			pstmt.execute();

			//TODO: bad implementation
			phonesDAO.addPhoneNumbers(personId, p.getPhoneNumbers());
			personLinksDAO.addPersonLinks(personId, p.getPersonLinks());
			skillsDAO.addSkills(personId, p.getSkills());
			eduDAO.addEducation(personId, p.getEducation());
			empDAO.addEmploymentHistory(personId, p.getEmploymentHistory());
			certDAO.addCertificateLinks(personId, p.getCertificate());
			linksToFilesDAO.addFutureLinksToFilesToPerson(personId, p.getLinksToFiles());
			

			return true;
		} catch (SQLException ex) {
			log.error("Error - " + ex);
			return false;
		}
	}

	public PersonalInfo getPersonById(int personId) {
		PersonalInfo person = null;
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PERSON_BY_ID)){
			pstmt.setInt(1, personId);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			rs.next();
			person = new PersonalInfo(rs.getInt("id"), rs.getString("first_name"), rs.getString("second_name"), rs.getString("claim_position"), rs.getString("e_mail"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getInt("city_id"), rs.getInt("zip_code"), rs.getString("address"), rs.getString("user_profile"), rs.getString("hobbies"), Language.getLanguage(rs.getString("pref_lang")));

		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}

		return person;
	}


	
	public void updateStandaloneDataFromPerson(PersonalInfo p){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSON)){
              pstmt.setString(1, p.getFirstName());
              pstmt.setString(2, p.getSecondName());
              pstmt.setString(3, p.getClaimPosition());
              pstmt.setString(4, p.getProfile());
              pstmt.setString(5, p.getHobbies());
              pstmt.setString(6, p.geteMail());
              pstmt.setInt(7, p.getCountry());
              pstmt.setInt(8, p.getRegion());
              pstmt.setInt(9, p.getCity());
              pstmt.setString(10, p.getAddress());
              pstmt.setInt(11, p.getZipCode());
              pstmt.setString(12, p.getPrefLang().getLangName());
              pstmt.setInt(13, p.getId());
              pstmt.executeUpdate();
		} catch (SQLException ex){
			log.error("Error - " + ex);
		}
	}

}
