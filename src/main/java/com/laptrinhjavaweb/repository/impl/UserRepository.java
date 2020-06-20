package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends SimpleJpaRepository<UserEntity> implements IUserRepository {

	@Override
	public UserEntity save(UserEntity userEntity) {
		String sql = "INSERT INTO user(username, fullname) VALUES (?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
			//setParameter(statement, parameters);
			statement.setString(1, userEntity.getUserName());
			statement.setString(2, userEntity.getFullName());			
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
		return new UserEntity();
	}

	@Override
	public UserEntity findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM user");
		sql.append(" WHERE username = ? AND password = ? AND status = ? ");
		List<UserEntity> users = query(sql.toString(), userName, password, status);		
		return users.isEmpty() ? null : users.get((0));
	}


}
