package com.arthurspirke.cvcreator.dblayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.entity.domain.Certificate;
import com.arthurspirke.cvcreator.entity.enums.ImageSize;


import static com.arthurspirke.cvcreator.util.AppProperties.*;


public class CertificateDAO {
	Logger log = Logger.getLogger(CertificateDAO.class);
    private final static String INSERT_FULL_CERTIFICATE_TO_PERSON = "INSERT INTO certificate (person_id, height, width, url_to_image, small_desc, full_desc) VALUES (?,?,?,?,?,?)";
	private final static String UPDATE_FULL_CERTIFICATE_BY_ID = "UPDATE certificate SET height= ?, width= ?, url_to_image= ?, small_desc= ?, full_desc= ? WHERE id= ?";
    private final static String DELETE_CERTIFICATE_BY_ID = "DELETE FROM certificate WHERE id = ?";
	private final static String GET_CERTIFICATES_BY_PERSON_ID = "SELECT * FROM certificate WHERE person_id= ?";
    
	public void addCertificateLinks(int personId, List<Certificate> certificate) {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_CERTIFICATE_TO_PERSON)){
			for (Certificate cert : certificate) {
				pstmt.setInt(1, personId);
				pstmt.setInt(2, cert.getImageSize().height());
				pstmt.setInt(3, cert.getImageSize().width());
				pstmt.setString(4, cert.getImages().getUrl().getPath());
				pstmt.setString(5, cert.getSmallDescription());
				pstmt.setString(6, cert.getFullDescription());
				pstmt.execute();
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}
	}
	
	public List<Certificate> getCertificateByPersonId(int personId) {
		List<Certificate> certificate = new ArrayList<>();
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CERTIFICATES_BY_PERSON_ID)){
            pstmt.setInt(1, personId);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
			String urlPath = getSiteUrl();
			while (rs.next()) {
				certificate.add(new Certificate(rs.getInt("id"), rs.getInt("person_id"), ImageSize.getType(rs.getInt("height") + "x" + rs.getInt("width")), rs.getString("url_to_image"), rs.getString("small_desc"), rs.getString("full_desc"), rs.getString("state")));
			}
		} catch (SQLException ex) {
			log.error("Error - " + ex);
		}

		return certificate;
	}


	
	public void updateCertificateEntityById(List<Certificate> certificate) {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_CERTIFICATE_BY_ID)){
			try{				
			    for(Certificate cert : certificate){
			    	pstmt.setInt(1, cert.getImageSize().height());
			    	pstmt.setInt(2, cert.getImageSize().width());
			    	pstmt.setString(3, cert.getImages().getUrl().getPath());
			    	pstmt.setString(4, cert.getSmallDescription());
			    	pstmt.setString(5, cert.getFullDescription());
			    	pstmt.setInt(6, cert.getId());
			    	pstmt.execute();
			    }
			} catch(SQLException ex){
                conn.rollback();                
			}			
		} catch (SQLException ex) {
			log.error("Error - " + ex);	
		}
	}
	
	
	public void deleteCertificateEntity(List<Certificate> certificate){
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_CERTIFICATE_BY_ID)){
			try{
                 for(Certificate cert : certificate){
                	 pstmt.setInt(1, cert.getId());
                 }
			} catch(SQLException ex){
				conn.rollback();
			}

		} catch (SQLException ex){
			log.error("Error - " + ex);

		}
	}

}
