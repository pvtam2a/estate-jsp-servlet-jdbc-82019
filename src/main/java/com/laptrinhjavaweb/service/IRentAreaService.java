package com.laptrinhjavaweb.service;

import java.sql.SQLException;

public interface IRentAreaService {
	void save(Long buildingId, String rentArea) throws Exception;
	void update(Long buildingId, String rentArea) throws Exception;
	String getRentAreaByBuildingId(Long id);
	void delete(Long[] ids) throws SQLException;
}
