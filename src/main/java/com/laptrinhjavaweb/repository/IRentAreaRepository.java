package com.laptrinhjavaweb.repository;

import java.sql.SQLException;
import java.util.List;

import com.laptrinhjavaweb.entity.RentAreaEntity;

public interface IRentAreaRepository extends JpaRepository<RentAreaEntity>{
	List<RentAreaEntity> findByBuildingId(Long id);
	void deleteByBuildingId(Long id) throws SQLException;
}
