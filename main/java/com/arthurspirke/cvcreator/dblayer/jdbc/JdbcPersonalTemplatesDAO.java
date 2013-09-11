package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.arthurspirke.cvcreator.dblayer.PersonalTemplatesDAO;
import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.entity.business.PersonalTemplates;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcPersonalTemplatesDAO implements PersonalTemplatesDAO {
    private static final String INSERT_PERSONAL_TEMPLATES = "INSERT INTO templates (id, person_id, pdf, html, doc) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PERSONAL_TEMPLATES = "UPDATE templates SET pdf= ?, html= ?, doc= ? WHERE id= ?";
    private static final String DELETE_PERSONAL_TEMPLATES = "DELETE FROM templates WHERE id= ?";
    private static final String GET_PERSONAL_TEMPLATES_BY_ID = "SELECT * FROM templates WHERE id= ?";
    private static final String GET_PERSONAL_TEMPLATES_BY_PERSON_ID = "SELECT * FROM templates WHERE person_id= ?";
    
	@Override
	public PersonalTemplates getById(String id) throws ComponentAssemblyException{
		
           try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PERSONAL_TEMPLATES_BY_ID)){
        	   
        	   pstmt.setString(1, id);
        	   ResultSet rs = pstmt.executeQuery();
        	   
        	   rs.next();
        	   return new PersonalTemplates(rs.getString("id"), rs.getString("person_id"), rs.getString("pdf"), rs.getString("html"), rs.getString("doc"));
           
           } catch(SQLException ex){
        	   throw new ComponentAssemblyException();
           }
	}

	@Override
	public List<PersonalTemplates> getListByPersonId(String personId) {
              throw new IllegalArgumentException();
	}

	@Override
	public void insert(PersonalTemplates entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_PERSONAL_TEMPLATES)){
			
			pstmt.setString(1, entity.getId());
			pstmt.setString(2, entity.getPersonId());
			pstmt.setString(3, entity.getTemplatePDF().toString());
			pstmt.setString(4, entity.getTemplateHTML().toString());
			pstmt.setString(5, entity.getTemplateDOC().toString());
			pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void insert(List<PersonalTemplates> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_PERSONAL_TEMPLATES)){
			
			for(PersonalTemplates entity : list){
				
				pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getTemplatePDF().toString());
				pstmt.setString(4, entity.getTemplateHTML().toString());
				pstmt.setString(5, entity.getTemplateDOC().toString());
				pstmt.execute();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSONAL_TEMPLATES)){
			
			pstmt.setString(1, id);
			pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSONAL_TEMPLATES)){
			
			for(String id : ids){
				
				pstmt.setString(1, id);
				pstmt.execute();
				
			}

		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
	}

	@Override
	public void update(PersonalTemplates entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSONAL_TEMPLATES)){
           
			pstmt.setString(1, entity.getTemplatePDF().toString());
			pstmt.setString(2, entity.getTemplateHTML().toString());
			pstmt.setString(3, entity.getTemplateDOC().toString());
			pstmt.setString(4, entity.getId());
			pstmt.executeUpdate();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void update(List<PersonalTemplates> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSONAL_TEMPLATES)){
	           
			for(PersonalTemplates entity : list){
				
				pstmt.setString(1, entity.getTemplatePDF().toString());
				pstmt.setString(2, entity.getTemplateHTML().toString());
				pstmt.setString(3, entity.getTemplateDOC().toString());
				pstmt.setString(4, entity.getId());
				pstmt.executeUpdate();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

}
