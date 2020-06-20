package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.anotation.Entity;
import com.laptrinhjavaweb.anotation.Table;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.JpaRepository;

public class SimpleJpaRepository<T> implements JpaRepository<T> {

	private Class<T> zClass;

	@SuppressWarnings("unchecked")
	public SimpleJpaRepository() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll(Map<String, Object> properties, Pageable pageable, Object... where) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		//SQL
		StringBuilder sql = new StringBuilder(" SELECT * FROM " + tableName + " A ");
	
		sql.append(" WHERE 1=1 ");
		sql = createSQLfindAll(sql, properties);
		if(where != null && where.length == 1) {
			sql.append(where[0]);
		}
		if(pageable.getLimit() != null && pageable.getOffset() != null) {
			sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit() + " ");
		}
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
		// PreparedStatement statement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results;
		} catch (SQLException e) {
			return new ArrayList<>();
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
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}

	@Override
	public List<T> findAll(Map<String, Object> properties, Object... where) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		//SQL
		StringBuilder sql = new StringBuilder(" SELECT * FROM " + tableName + " A WHERE 1=1 ");
		sql = createSQLfindAll(sql, properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		//sql.append(" limit " + pageable.getOffset() + ", " + pageable.getLimit() + " ");
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
		// PreparedStatement statement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results;
		} catch (SQLException e) {
			return new ArrayList<>();
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
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}

	@Override
	public List<T> findAll(String sqlSearch, Pageable pageable, Object... where) {		
		//SQL
		StringBuilder sql = new StringBuilder(sqlSearch);
		if(pageable.getLimit() != null && pageable.getOffset() != null) {
			sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit() + " ");
		}
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
		// PreparedStatement statement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results;
		} catch (SQLException e) {
			return new ArrayList<>();
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
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}
	@Override
	public List<T> findAll(String sqlSearch) {		
		//SQL
		StringBuilder sql = new StringBuilder(sqlSearch);		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
		// PreparedStatement statement = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql.toString());
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results;
		} catch (SQLException e) {
			return new ArrayList<>();
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
			} catch (SQLException e) {
				return new ArrayList<>();
			}
		}
	}
	@Override
	public List<T> query(String sql, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();		
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results;
		} catch (SQLException e) {
			return null;
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
			} catch (SQLException e) {
				return null;
			}
		}
	}
	@Override
	public T findById(Long id) {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		//SQL
		String sql = " SELECT * FROM " + tableName + " WHERE id = ? ";		
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<>();
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement  statement= null;	
		ResultSet resultSet = null;
		try {
			connection = EntityManagerFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			results = resultSetMapper.mapRow(resultSet, this.zClass);
			return results.isEmpty() ? null : results.get(0);
		} catch (SQLException e) {
			return null;
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
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public Long insert(Object object) throws Exception {		
		String sql = createSqlInsert();		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);			
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				int index = i + 1;
				Field field = fields[i];
				field.setAccessible(true);
				statement.setObject(index, field.get(object));
			}
			Class<?> parentClass = aClass.getSuperclass();
			int indexParent = fields.length + 1;
			while(parentClass != null) {
				Field[] fieldsParent = parentClass.getDeclaredFields();
				for (int i = 0; i < fieldsParent.length; i++) {					
					Field field = fieldsParent[i];
					field.setAccessible(true);
					statement.setObject(indexParent, field.get(object));
					indexParent++;
				}
				parentClass = parentClass.getSuperclass();
			}
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();		
			return id;
		} catch (SQLException | IllegalAccessException e) {
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
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	private String createSqlInsert() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		for(Field field : zClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				if(fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}			
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());	
				params.append("?");
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field : parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {
					if(fields.length() > 1) {
						fields.append(",");
						params.append(",");
					}				
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());	
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = " INSERT INTO "+tableName+"("+fields.toString()+") VALUES("+params.toString()+")";
		return sql;
	}

	@Override
	public void update(Object object) throws Exception {
		String sql = createSqlUpdate();		
		Connection connection = null;
		PreparedStatement statement = null;	
		try {		
			Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);			
			Class<?> aClass = object.getClass();
			Field[] fields = aClass.getDeclaredFields();
			int index = 0;
			for (int i = 0; i < fields.length; i++) {				
				Field field = fields[i];
				field.setAccessible(true);
				String fieldName = field.getName();
				if("id".equals(fieldName)) {
					id = (Long) field.get(object);
				}
				if(!"id".equals(fieldName) && !"createdDate".equals(fieldName) && !"createdBy".equals(fieldName)) {
					index += 1;
					statement.setObject(index, field.get(object));
				}				
			}
			Class<?> parentClass = aClass.getSuperclass();			
			while(parentClass != null) {
				Field[] fieldsParent = parentClass.getDeclaredFields();
				for (int i = 0; i < fieldsParent.length; i++) {					
					Field field = fieldsParent[i];
					field.setAccessible(true);
					String fieldName = field.getName();
					if("id".equals(fieldName)) {
						id = (Long) field.get(object);
					}
					if(!"id".equals(fieldName) && !"createdDate".equals(fieldName) && !"createdBy".equals(fieldName)) {
						index += 1;
						statement.setObject(index, field.get(object));
					}	
				}
				parentClass = parentClass.getSuperclass();
			}
			statement.setObject(index + 1, id);
			statement.executeUpdate();			
			connection.commit();	
		} catch (SQLException | IllegalAccessException e) {
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
	private String createSqlUpdate() {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		StringBuilder fields = new StringBuilder("");
		for(Field field : zClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				if(!"id".equals(columnName) && !"createddate".equals(columnName) && !"createdby".equals(columnName)) {
					if(fields.length() > 1) {
						fields.append(",");
					}
					fields.append(column.name() + " = ?");
				}				
			}
		}
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field : parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					if(!"id".equals(columnName) && !"createddate".equals(columnName) && !"createdby".equals(columnName)) {
						if(fields.length() > 1) {
							fields.append(",");
						}
						fields.append(column.name() + " = ?");
					}				
				}				
			}
			parentClass = parentClass.getSuperclass();
		}
		String sql = " UPDATE "+tableName+" SET "+fields.toString()+ " WHERE id = ?";
		return sql;
	}

	@Override
	public void delete(Long id) throws SQLException {
		String tableName = "";
		if (zClass.isAnnotationPresent(Entity.class) && zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		String sql = "DELETE FROM "+tableName+" WHERE id = ?";		
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

	@Override
	public int count(String sql, Object... parameters) {
		//SQL		
		Connection connection = null;
		PreparedStatement statement = null;	
		ResultSet resultSet = null;
		int count = 0;
		try {			
			connection = EntityManagerFactory.getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return count;
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
			} catch (SQLException e) {
				return count;
			}
		}
	}
	protected StringBuilder createSQLfindAll(StringBuilder where, Map<String, Object> params) {		
		if (params != null && params.size() > 0) {
			String[] keys = new String[params.size()];
			Object[] values = new Object[params.size()];
			int i = 0;
			for (Map.Entry<String, Object> item : params.entrySet()) {
				keys[i] = item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for (int i1 = 0; i1 < keys.length; i1++) {
				if (values[i1] instanceof String && StringUtils.isNotBlank(values[i1].toString())) {
					where.append(" AND LOWER(A." + keys[i1] + ") LIKE '%" + values[i1].toString() + "%' ");
				} else if (values[i1] instanceof Integer && (values[i1] != null)) {
					where.append(" AND LOWER(A." + keys[i1] + ") = " + values[i1] + " ");
				} else if (values[i1] instanceof Long && (values[i1] != null)) {
					where.append(" AND LOWER(A." + keys[i1] + ") = " + values[i1] + " ");
				}
			}
		}
		return where;
	}
	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if (parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if (parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if (parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
