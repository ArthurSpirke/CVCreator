package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.PersonLinks;
import com.arthurspirke.cvcreator.entity.enums.LinkIcon;

public class PersonLinksDAO {
	Logger log = Logger.getLogger(PersonLinksDAO.class);
	private final static String ADD_FULL_LINKS_TO_PERSON = "INSERT INTO links (person_id, link_type, link) VALUES (?,?,?)";
	private final static String SELECT_FULL_LINKS_BY_PERSON = "SELECT * FROM links WHERE person_id= ?";
	private final static String UPDATE_LINKS_BY_ID = "UPDATE links SET link_type= ?, link= ? WHERE id= ?";
	private final static String DELETE_LINKS_BY_ID = "DELETE FROM links WHERE id= ?";

	public void addPersonLinks(Integer personId, List<PersonLinks> personLinks){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(ADD_FULL_LINKS_TO_PERSON)){
			
			for(PersonLinks link : personLinks){
				pstmt.setInt(1, personId);
				pstmt.setString(2, link.getLinkType().getIconName());
				pstmt.setString(3, link.getLinkName());
				pstmt.execute();
			}
			
		} catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	public List<PersonLinks> getPersonLinksByPersonId(Integer personId){
		List<PersonLinks> returnList = new ArrayList<>();
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_FULL_LINKS_BY_PERSON)){
			pstmt.setInt(1, personId);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()){
				returnList.add(new PersonLinks(rs.getInt("id"), rs.getInt("person_id"), LinkIcon.getIconObject(rs.getString("link_type")), rs.getString("link")));
			}
		}catch(SQLException ex){
			log.error("Error - " + ex);
		}
		
		return returnList;
	}
	
	public void updatePersonLinksById(List<PersonLinks> personLinks){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_LINKS_BY_ID)){
			
			for(PersonLinks links : personLinks){
				pstmt.setString(1, links.getLinkType().getIconName());
				pstmt.setString(2, links.getLinkName());
				pstmt.setInt(3, links.getId());
				pstmt.execute();
			}
		}catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
	
	
	public void deletePersonLinksById(List<PersonLinks> personLinks){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_LINKS_BY_ID)){
			
			for(PersonLinks links : personLinks){
				pstmt.setInt(1, links.getId());
				pstmt.execute();
			}
		}catch(SQLException ex){
			log.error("Error - " + ex);
		}
	}
}
