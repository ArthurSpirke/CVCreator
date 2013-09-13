package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonLinksDAO;
import com.arthurspirke.cvcreator.entity.business.PersonLinks;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

import static com.arthurspirke.cvcreator.entity.enums.LinkIcon.*;

public class JdbcPersonLinksDAO implements PersonLinksDAO{
	Logger log = Logger.getLogger(JdbcPersonLinksDAO.class);
	private final static String ADD_FULL_LINKS_TO_PERSON = "INSERT INTO links (id, person_id, type, value) VALUES (?,?,?,?)";
	private final static String SELECT_FULL_LINKS_BY_PERSON = "SELECT * FROM links WHERE person_id= ?";
	private final static String UPDATE_LINKS_BY_ID = "UPDATE links SET type= ?, value= ? WHERE id= ?";
	private final static String DELETE_LINKS_BY_ID = "DELETE FROM links WHERE id= ?";
	private final static String GET_PERSON_LINK_BY_ID = "SELECT * FROM links WHERE id= ?";



	@Override
	public PersonLinks getById(String id) throws ComponentAssemblyException{

		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PERSON_LINK_BY_ID)){
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
            rs.next();
            
			return new PersonLinks(rs.getString("id"), rs.getString("person_id"), getIconObject(rs.getString("type")), rs.getString("value"));

		}catch(SQLException ex){
			
			throw new ComponentAssemblyException();
			
		}
	}

	@Override
	public List<PersonLinks> getListByPersonId(String personId) throws ComponentAssemblyException{
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_FULL_LINKS_BY_PERSON)){
			
			List<PersonLinks> returnList = new ArrayList<>();
			
			pstmt.setString(1, personId);
			ResultSet rs = pstmt.executeQuery();

			
			while(rs.next()){
				returnList.add(new PersonLinks(rs.getString("id"), rs.getString("person_id"), getIconObject(rs.getString("type")), rs.getString("value")));
			}
			
			return returnList;
			
		}catch(SQLException ex){
			
			throw new ComponentAssemblyException();
			
		}

	}

	@Override
	public void insert(PersonLinks entity) throws ComponentWriteException {
        try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(ADD_FULL_LINKS_TO_PERSON)){
			
        	    pstmt.setString(1, entity.getId());
				pstmt.setString(2, entity.getPersonId());
				pstmt.setString(3, entity.getLinkType().getIconName());
				pstmt.setString(4, entity.getLinkName());
				
				pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void insert(List<PersonLinks> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(ADD_FULL_LINKS_TO_PERSON)){
			
			for(PersonLinks link : list){
				
				pstmt.setString(1, link.getId());
				pstmt.setString(2, link.getPersonId());
				pstmt.setString(3, link.getLinkType().getIconName());
				pstmt.setString(4, link.getLinkName());			
				pstmt.execute();
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_LINKS_BY_ID)){

				pstmt.setString(1, id);
				pstmt.execute();

		}catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
		
	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_LINKS_BY_ID)){
			
			for(String id : ids){
				
				pstmt.setString(1, id);
				pstmt.execute();
			}
		}catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(PersonLinks entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_LINKS_BY_ID)){
				
				pstmt.setString(1, entity.getLinkType().getIconName());
				pstmt.setString(2, entity.getLinkName());
				pstmt.setString(3, entity.getId());	
				pstmt.executeUpdate();

		}catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(List<PersonLinks> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_LINKS_BY_ID)){
			
			for(PersonLinks links : list){
				
				pstmt.setString(1, links.getLinkType().getIconName());
				pstmt.setString(2, links.getLinkName());
				pstmt.setString(3, links.getId());	
				pstmt.executeUpdate();
			}
		}catch(SQLException ex){
			throw new ComponentWriteException();
		}
	}
}
