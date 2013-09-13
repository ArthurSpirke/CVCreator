package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalDAO;
import com.arthurspirke.cvcreator.entity.business.Person;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

import static com.arthurspirke.cvcreator.entity.enums.Language.*;

public class JdbcPersonalDAO implements PersonalDAO{
	private final static String INSERT_FULL_PERSON = "INSERT INTO person (id, pref_language) VALUES (?,?)";	
	private final static String GET_PERSON_BY_ID = "SELECT * FROM person WHERE id= ?";
	private final static String UPDATE_PERSON = "UPDATE person SET pref_language= ? WHERE id= ?";
	private final static String DELETE_PERSON_BY_ID = "DELETE FROM person WHERE id= ?";
	Logger log = Logger.getLogger(JdbcPersonalDAO.class);
    
	

	@Override
	public Person getById(String id) throws ComponentAssemblyException{

		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_PERSON_BY_ID)){
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
            rs.next();
            
			return new Person(rs.getString("id"), getLanguage(rs.getString("pref_language")));

		} catch (SQLException ex) {
			
			throw new ComponentAssemblyException();
			
		}
	}

	@Override
	public List<Person> getListByPersonId(String personId) {
          throw new UnsupportedOperationException();
	}

	@Override
	public void insert(Person entity) throws ComponentWriteException {
		
		try (Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_FULL_PERSON)){
            pstmt.setString(1, entity.getId());
            pstmt.setString(2, entity.getPrefLang().getLangName());
            
			pstmt.execute();

		} catch (SQLException ex) {
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void insert(List<Person> list) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSON_BY_ID)){

				pstmt.setString(1, id);
				pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_PERSON_BY_ID)){
			
			for(String id : ids){
				pstmt.setString(1, id);
				pstmt.execute();
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(Person entity) throws ComponentWriteException {
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSON)){
			
            pstmt.setString(1, entity.getPrefLang().getLangName());
            pstmt.setString(2, entity.getId());
            pstmt.executeUpdate();
            
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

	@Override
	public void update(List<Person> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_PERSON)){
		
			for(Person p : list) {

            pstmt.setString(1, p.getPrefLang().getLangName());
            pstmt.setString(2, p.getId());
            pstmt.executeUpdate();
            
			}
		} catch (SQLException ex){
			throw new ComponentWriteException();
		}
		
	}

}
