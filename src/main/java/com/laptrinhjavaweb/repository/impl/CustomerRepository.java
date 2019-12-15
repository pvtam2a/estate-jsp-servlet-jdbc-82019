package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.ICustomerRepository;

public class CustomerRepository extends SimpleJpaRepository<CustomerEntity> implements ICustomerRepository{

	@Override
	public List<CustomerEntity> findAll(Map<String, Object> params, Pageable pageable, CustomerSearchBuilder fieldSearch) {	
	
		StringBuilder sqlSearch = new StringBuilder(" SELECT * from customer A ");
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())){
			sqlSearch.append(" INNER JOIN customer_user CU ON A.id = CU.customerid ");
		}
		sqlSearch.append(" WHERE 1=1 ");
		sqlSearch = this.createSQLfindAll(sqlSearch, params);
		String sqlSpecial = buildSqlSpecial(fieldSearch);	
		sqlSearch.append(sqlSpecial);
		return this.findAll(sqlSearch.toString(), pageable);
	}
	private String buildSqlSpecial(CustomerSearchBuilder fieldSearch) {
		StringBuilder result = new StringBuilder();		
		if(StringUtils.isNotBlank(fieldSearch.getStaffId())){
			result.append(" AND CU.staffid = "+fieldSearch.getStaffId()+" ");
		}
		return result.toString();
	}
	@Override
	public CustomerEntity save(CustomerEntity customerEntity) {
		String sql = "INSERT INTO customer(name, address, tel, email) VALUES (?,?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//setParameter(statement, parameters);
			statement.setString(1, customerEntity.getName());
			statement.setString(2, customerEntity.getAddress());
			statement.setString(3, customerEntity.getTel());
			statement.setString(4, customerEntity.getEmail());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();		
			return this.findById(id);
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return new CustomerEntity();
	}
}
