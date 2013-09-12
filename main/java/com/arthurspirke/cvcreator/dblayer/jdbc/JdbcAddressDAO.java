package com.arthurspirke.cvcreator.dblayer.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.arthurspirke.cvcreator.dblayer.AddressDAO;
import com.arthurspirke.cvcreator.dblayer.core.DBConnection;
import com.arthurspirke.cvcreator.entity.business.Address;
import com.arthurspirke.cvcreator.entity.enums.Language;
import com.arthurspirke.cvcreator.entity.exception.ComponentAssemblyException;
import com.arthurspirke.cvcreator.entity.exception.ComponentWriteException;

public class JdbcAddressDAO implements AddressDAO {
    private static final String INSERT_ADDRESS = "INSERT INTO address (id, person_id, host_id, country_id, region_id, city_id, street_address, postal_code) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ADDRESS = "UPDATE address SET country_id= ?, region_id= ?, city_id= ?, street_address= ?, postal_code= ? WHERE id= ?";
    private static final String DELETE_ADDRESS = "DELETE FROM address WHERE id= ?";
    private static final String GET_ADDRESS_BY_ID = "SELECT * FROM address WHERE id= ?";
    private static final String GET_ADDRESS_BY_PERSON_ID = "SELECT * FROM address WHERE person_id= ?";
    private static final String GET_ADDRESS_BY_HOST_ID = "SELECT * FROM address WHERE host_id= ?";
	
	@Override
	public Address getById(String id) throws ComponentAssemblyException {
		
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ADDRESS_BY_ID)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();

			return new Address(rs.getString("id"), rs.getString("host_id"), rs.getString("person_id"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getInt("city_id"), rs.getInt("postal_code"), rs.getString("street_address"), Language.getLanguage(rs.getString("pref_lang")), rs.getString("state"));
		
		} catch(SQLException ex){
             throw new ComponentAssemblyException();
		}
	}

	public Address getByHostId(String hostId) throws ComponentAssemblyException {
       try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(GET_ADDRESS_BY_HOST_ID)){
 
    	   pstmt.setString(1, hostId);
    	   ResultSet rs = pstmt.getResultSet();
    	   rs.next();
    	   
    	   return new Address(rs.getString("id"), rs.getString("host_id"), rs.getString("person_id"), rs.getInt("country_id"), rs.getInt("region_id"), rs.getInt("city_id"), rs.getInt("postal_code"), rs.getString("street_address"), Language.getLanguage(rs.getString("pref_lang")), rs.getString("state"));
    	   
       } catch(SQLException ex){
    	   throw new ComponentAssemblyException();
       }
	}

	@Override
	public List<Address> getListByPersonId(String personId){
             throw new UnsupportedOperationException();
	}

	@Override
	public void insert(Address entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_ADDRESS)){
			pstmt.setString(1, entity.getId());
			pstmt.setString(2, entity.getPersonId());
			pstmt.setString(3, entity.getHostId());
			pstmt.setInt(4, entity.getCountryId());
			pstmt.setInt(5, entity.getRegionId());
			pstmt.setInt(6, entity.getCityId());
			pstmt.setString(7, entity.getStreet());
			pstmt.setInt(8, entity.getPostalCode());
			pstmt.execute();
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void insert(List<Address> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(INSERT_ADDRESS)){
			
			for(Address entity : list){
				
			pstmt.setString(1, entity.getId());
			pstmt.setString(2, entity.getPersonId());
			pstmt.setString(3, entity.getHostId());
			pstmt.setInt(4, entity.getCountryId());
			pstmt.setInt(5, entity.getRegionId());
			pstmt.setInt(6, entity.getCityId());
			pstmt.setString(7, entity.getStreet());
			pstmt.setInt(8, entity.getPostalCode());
			pstmt.execute();
			
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String id) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_ADDRESS)){
			
			pstmt.setString(1, id);
			pstmt.execute();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void delete(String[] ids) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE_ADDRESS)){
			
			for(String id : ids){
				
				pstmt.setString(1, id);
				pstmt.execute();
				
			}

			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void update(Address entity) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_ADDRESS)){
			
			pstmt.setInt(1, entity.getCountryId());
			pstmt.setInt(2, entity.getRegionId());
			pstmt.setInt(3, entity.getCityId());
			pstmt.setString(4, entity.getStreet());
			pstmt.setInt(5, entity.getPostalCode());
			pstmt.setString(6, entity.getId());
			pstmt.executeUpdate();
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}

	@Override
	public void update(List<Address> list) throws ComponentWriteException {
		try(Connection conn = DBConnection.getMySQLConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE_ADDRESS)){
			
			for(Address entity : list){
				
				pstmt.setInt(1, entity.getCountryId());
				pstmt.setInt(2, entity.getRegionId());
				pstmt.setInt(3, entity.getCityId());
				pstmt.setString(4, entity.getStreet());
				pstmt.setInt(5, entity.getPostalCode());
				pstmt.setString(6, entity.getId());
				pstmt.executeUpdate();
				
			}
			
		} catch(SQLException ex){
			throw new ComponentWriteException();
		}

	}


}
