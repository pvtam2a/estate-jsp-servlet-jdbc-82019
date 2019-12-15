package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface IBuildingService {
	List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable);
	BuildingDTO save(BuildingDTO buildingDTO);
	BuildingDTO update(BuildingDTO buildingDTO);
	void delete(Long[] ids);
}
