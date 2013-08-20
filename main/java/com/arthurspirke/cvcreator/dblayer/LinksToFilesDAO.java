package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.LinksToFiles;

public class LinksToFilesDAO {
	private final static String INSERT_FULL_LINKS_TO_PERSON = "INSERT INTO files (person_id, url_to_pdf, url_to_doc, url_to_html) VALUES (?,?,?,?)";
	private final static String GET_LINKS_BY_PERSON_ID = "SELECT * FROM files WHERE person_id= ?";
	
  Logger log = Logger.getLogger(LinksToFilesDAO.class);
  
  public void addFutureLinksToFilesToPerson(int personId, LinksToFiles linksToFiles){
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_LINKS_TO_PERSON)){
		  pstmt.setInt(1, personId);
		  pstmt.setString(2, linksToFiles.getPdfFile());
		  pstmt.setString(3, linksToFiles.getDocFile());
		  pstmt.setString(4, linksToFiles.getHtmlFile());
		  pstmt.execute();
		  
	  } catch(SQLException ex){
		  log.error("Error - " + ex);
	  }
  }
  
  public LinksToFiles getFutureLinksToFilesByPersonId(int personId){
	  LinksToFiles linksToFiles = null;
	  try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_LINKS_BY_PERSON_ID)){
          pstmt.setInt(1, personId);
		  pstmt.execute();
		  ResultSet rs = pstmt.getResultSet();
		  rs.next();
		  linksToFiles = new LinksToFiles(rs.getInt("id"), rs.getInt("person_id"), rs.getString("url_to_pdf"), rs.getString("url_to_doc"), rs.getString("url_to_html"));
	  } catch(SQLException ex){
		  log.error("Error - " + ex);
	  }
	  
	  return linksToFiles;
  }
}
