package com.laptrinhjavaweb.converter;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public class BuildingConverter {

	public BuildingDTO convertToDTO(BuildingEntity entity) {		
		ModelMapper modelMapper = new ModelMapper();		
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);		
		return dto;		
	}
	public BuildingEntity convertToEntity(BuildingDTO dto) {		
		ModelMapper modelMapper = new ModelMapper();		
		BuildingEntity entity = modelMapper.map(dto, BuildingEntity.class);		
		return entity;		
	}
	public String convertBuildingTypeToString(String[] arrType) {
		StringBuilder type = new StringBuilder();
		for (String item : arrType) {
			if(StringUtils.isNotBlank(type.toString())){
				type.append(",");
			}
			type.append(item);
		}
		return type.toString();
	}
	public String[] convertBuildingTypeToArray(String type) {
		return type.split("\\,");		
	}
}
