package com.laptrinhjavaweb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {
	List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable);
	BuildingDTO save(BuildingDTO buildingDTO) throws Exception;
	BuildingDTO update(BuildingDTO buildingDTO) throws Exception;
	void delete(Long[] ids) throws SQLException;
	Map<String, String> getDistricts();
	Map<String, String> getBuildingTypes();
	int getTotalItem(BuildingSearchBuilder fieldSearch);
}
