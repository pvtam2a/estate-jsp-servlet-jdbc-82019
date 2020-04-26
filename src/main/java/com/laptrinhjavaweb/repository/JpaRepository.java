package com.laptrinhjavaweb.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.paging.Pageable;

public interface JpaRepository<T> {
	List<T> findAll(Map<String, Object> properties, Pageable pageable, Object...objects);
	List<T> findAll(Map<String, Object> properties, Object...objects);
	List<T> findAll(String sql, Pageable pageable, Object...objects);
	T findById(Long id);
	Long insert(Object object) throws Exception;
	void update(Object object)  throws Exception;
	void delete(Long id)  throws SQLException;
	List<T> findAll(String sqlSearch);
	int count(String sql, Object...objects);
}
