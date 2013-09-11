package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.entity.business.Certificate;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

import static com.arthurspirke.cvcreator.entity.enums.ImageSize.*;


public class JdbcCertificateDAO implements CertificateDAO{
	Logger log = Logger.getLogger(JdbcCertificateDAO.class);
    private final static String INSERT_FULL_CERTIFICATE_TO_PERSON = "INSERT INTO certificate (id, person_id, height, width, url_to_image, small_desc, full_desc) VALUES (?,?,?,?,?,?,?)";
	private final static String UPDATE_FULL_CERTIFICATE_BY_ID = "UPDATE certificate SET height= ?, width= ?, url_to_image= ?, small_desc= ?, full_desc= ? WHERE id= ?";
    private final static String DELETE_CERTIFICATE_BY_ID = "DELETE FROM certificate WHERE id = ?";
	private final static String GET_CERTIFICATES_BY_PERSON_ID = "SELECT * FROM certificate WHERE person_id= ?";
	private final static String GET_CERTIFICATE_BY_ID = "SELECT * FROM certificate WHERE id= ?";
    
	
	@Override
	public Certificate getById(String id) throws ComponentAssemblyException{
          try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CERTIFICATE_BY_ID)){
        	  
        	  pstmt.setString(1, id);
        	  ResultSet rs = pstmt.executeQuery();
        	  
        	  return new Certificate(rs.getString("id"), rs.getString("person_id"), getType(rs.getInt("height") + "x" + rs.getInt("width")), rs.getString("url_to_image"), rs.getString("small_desc"), rs.getString("full_desc"), rs.getString("state"));
          } catch(SQLException ex){
        	  throw new ComponentAssemblyException();
          }
	}

	@Override
	public List<Certificate> getListByPersonId(String personId) throws ComponentAssemblyException{
		
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_CERTIFICATES_BY_PERSON_ID)){
			
			List<Certificate> certificate = new ArrayList<>();
			
			pstmt.setString(1, personId);
			
            ResultSet rs = pstmt.executeQuery();
            
			while (rs.next()) {
				certificate.add(new Certificate(rs.getString("id"), rs.getString("person_id"), getType(rs.getInt("height") + "x" + rs.getInt("width")), rs.getString("url_to_image"), rs.getString("small_desc"), rs.getString("full_desc"), rs.getString("state")));
			}
			
			return certificate;
			
		} catch (SQLException ex) {
			throw new ComponentAssemblyException();
		}

	}

	
	@Override
	public void insert(Certificate entity) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_CERTIFICATE_TO_PERSON)){
				
			    pstmt.setString(1, entity.getId());
			    pstmt.setString(2, entity.getPersonId());
				pstmt.setInt(3, entity.getImageSize().height());
				pstmt.setInt(4, entity.getImageSize().width());
				pstmt.setString(5, entity.getImages().getUrl().getPath());
				pstmt.setString(6, entity.getSmallDescription());
				pstmt.setString(7, entity.getFullDescription());
				pstmt.execute();
				
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void insert(List<Certificate> list) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_CERTIFICATE_TO_PERSON)){
			
			for (Certificate cert : list) {
				
				pstmt.setString(1, cert.getId());
				pstmt.setString(2, cert.getPersonId());
				pstmt.setInt(3, cert.getImageSize().height());
				pstmt.setInt(4, cert.getImageSize().width());
				pstmt.setString(5, cert.getImages().getUrl().getPath());
				pstmt.setString(6, cert.getSmallDescription());
				pstmt.setString(7, cert.getFullDescription());
				pstmt.execute();
				
			}
			
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_CERTIFICATE_BY_ID)){
               
			pstmt.setString(1, id);
            pstmt.execute();
                
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_CERTIFICATE_BY_ID)){
                 
			for(String id : ids){
				
               pstmt.setString(1, id);
               pstmt.execute();
                	 
            }
			
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(Certificate entity) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_CERTIFICATE_BY_ID)){
			    	
			        pstmt.setInt(1, entity.getImageSize().height());
			    	pstmt.setInt(2, entity.getImageSize().width());
			    	pstmt.setString(3, entity.getImages().getUrl().getPath());
			    	pstmt.setString(4, entity.getSmallDescription());
			    	pstmt.setString(5, entity.getFullDescription());
			    	pstmt.setString(6, entity.getId());
			    	pstmt.executeUpdate();
			    	
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(List<Certificate> list) throws ComponentWriteException {
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_FULL_CERTIFICATE_BY_ID)){	
			    
			for(Certificate cert : list){
				
			    	pstmt.setInt(1, cert.getImageSize().height());
			    	pstmt.setInt(2, cert.getImageSize().width());
			    	pstmt.setString(3, cert.getImages().getUrl().getPath());
			    	pstmt.setString(4, cert.getSmallDescription());
			    	pstmt.setString(5, cert.getFullDescription());
			    	pstmt.setString(6, cert.getId());
			    	pstmt.executeUpdate();
			    	
			    }
		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}

}
