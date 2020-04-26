package com.laptrinhjavaweb.converter;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.service.impl.RentAreaService;

public class BuildingConverter {
	private IRentAreaService rentAreaService = new RentAreaService();
	public BuildingDTO convertToDTO(BuildingEntity entity) {		
		ModelMapper modelMapper = new ModelMapper();		
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);
		return dto;		
	}
	public BuildingDTO convertToDTOSearch(BuildingEntity entity) {		
		ModelMapper modelMapper = new ModelMapper();		
		BuildingDTO dto = modelMapper.map(entity, BuildingDTO.class);	
		dto.setAddress(dto.getStreet() + ", " + dto.getWard() + ", " + DistrictsEnum.getValueByName(dto.getDistrict()));
		dto.setType(BuildingTypesEnum.getBuildingTypeValue(dto.getType()));
		dto.setRentArea(rentAreaService.getRentAreaByBuildingId(dto.getId()));
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
