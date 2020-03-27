package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;

public class RentAreaRepository extends SimpleJpaRepository<RentAreaEntity> implements IRentAreaRepository{

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		StringBuilder sqlSearch = new StringBuilder(" SELECT * FROM rentarea WHERE buildingid = "+id+"");		
		return this.findAll(sqlSearch.toString());
	}

	@Override
	public void deleteByBuildingId(Long id) throws SQLException {
		String sql = "DELETE FROM rentarea WHERE buildingid = ?";		
		Connection connection = null;
		PreparedStatement statement = null;	
		try {					
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);		
			statement.setObject(1, id);
			statement.executeUpdate();			
			connection.commit();	
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			throw e;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}		
		
	}
	
}
