package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.enums.BuildingTypesEnum;
import com.laptrinhjavaweb.enums.DistrictsEnum;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;

public class BuildingService implements IBuildingService{

	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;
	private IRentAreaService rentAreaService;
	public BuildingService() {
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
		rentAreaService = new RentAreaService();
	}
	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder fieldSearch, Pageable pageable) {
		
		Map<String, Object> properties = convertToMapProperties(fieldSearch);
						
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(properties, pageable, fieldSearch);
		
		return buildingEntities.stream().map(item -> buildingConverter.convertToDTOSearch(item)).collect(Collectors.toList());
		
	}
	
	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();	
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field: fields) {	
				if(!field.getName().equals("buildingTypes") && !field.getName().startsWith("costRent") && !field.getName().startsWith("rentArea") && !field.getName().startsWith("staffId")) {
					field.setAccessible(true);		
					if(field.get(fieldSearch) instanceof String) {
						if(field.getName().equals("buildingArea") || field.getName().equals("numberOfBasement")) {
							if(field.get(fieldSearch) != null && StringUtils.isNotBlank((String)field.get(fieldSearch))) {
								properties.put(field.getName().toLowerCase(), Integer.parseInt((String) field.get(fieldSearch)));
							}
							
						}else {
							properties.put(field.getName().toLowerCase(), field.get(fieldSearch));
						}
						
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return properties;
	}
	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) throws Exception {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Date());
		buildingEntity.setCreatedBy("system");
		//set building type
		buildingEntity.setType(buildingConverter.convertBuildingTypeToString(buildingDTO.getBuildingTypes()));
		Long id = buildingRepository.insert(buildingEntity);
		//save rentArea
		rentAreaService.save(id, buildingDTO.getRentArea());		
		BuildingDTO dto = buildingConverter.convertToDTO(buildingRepository.findById(id));
		return dto;
	}
	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) throws Exception {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setModifiedDate(new Date());
		buildingEntity.setModifiedBy("pvtam");
		Long id = buildingEntity.getId();
		buildingEntity.setType(buildingConverter.convertBuildingTypeToString(buildingDTO.getBuildingTypes()));
		
		rentAreaService.update(id, buildingDTO.getRentArea());
		buildingRepository.update(buildingEntity);
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}
	@Override
	public void delete(Long[] ids) throws SQLException {
		for (Long id : ids) {
			buildingRepository.delete(id);
		}		
	}
	@Override
	public Map<String, String> getDistricts() {
		Map<String, String> districts = new HashMap<>();
		for (DistrictsEnum item : DistrictsEnum.values()) {
			districts.put(item.name(), item.getDistrictValue());
		}	
		return districts;
	}
	@Override
	public Map<String, String> getBuildingTypes() {
		Map<String, String> buildingTypes = new HashMap<>();
		for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
			buildingTypes.put(item.name(), item.getBuildingTypeValue());
		}	
		return buildingTypes;
	}
	/*
	 * public String buildAddress(String district, String ward, String street) {
	 * String address = ""; if(StringUtils.isNotBlank(street)) address += street;
	 * if(StringUtils.isNotBlank(ward)) address += "," + ward; String districtVal =
	 * DistrictsEnum.getValueByName(district);
	 * if(StringUtils.isNotBlank(districtVal)) address += "," + districtVal; return
	 * address; }
	 */
	@Override
	public int getTotalItem(BuildingSearchBuilder fieldSearch) {
		Map<String, Object> properties = convertToMapProperties(fieldSearch);		
		return buildingRepository.getTotalItem(properties, fieldSearch);
	}
}
