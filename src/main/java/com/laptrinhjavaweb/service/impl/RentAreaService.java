package com.laptrinhjavaweb.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IRentAreaService;

public class RentAreaService implements IRentAreaService{

	private IRentAreaRepository rentAreaRepository;
	public RentAreaService() {
		rentAreaRepository = new RentAreaRepository();
	}
	@Override
	public void save(Long buildingId, String rentArea) throws Exception {		
		if(StringUtils.isNotBlank(rentArea)) {
			String[] arr = rentArea.split("\\,");
			Date createdDate = new Date();
			for (String item : arr) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(item.trim());
				rentAreaEntity.setBuildingId(buildingId);
				rentAreaEntity.setCreatedBy("system");
				rentAreaEntity.setCreatedDate(createdDate);
				rentAreaRepository.insert(rentAreaEntity);
			}
		}		
	}
	@Override
	public void update(Long buildingId, String rentArea) throws Exception {
		// TODO Auto-generated method stub
		rentAreaRepository.deleteByBuildingId(buildingId);
		if(StringUtils.isNotBlank(rentArea)) {
			String[] arr = rentArea.split("\\,");
			Date createdDate = new Date();
			for (String item : arr) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(item.trim());
				rentAreaEntity.setBuildingId(buildingId);
				rentAreaEntity.setCreatedBy("system");
				rentAreaEntity.setCreatedDate(createdDate);
				rentAreaRepository.insert(rentAreaEntity);
			}
		}	
	}
	@Override
	public String getRentAreaByBuildingId(Long id) {
		StringBuilder val = new StringBuilder("");
		List<RentAreaEntity> lists = rentAreaRepository.findByBuildingId(id);
		int i=0;
		for(RentAreaEntity item: lists) {
			if(i==0) {
				val.append(item.getValue());
				
			}else {
				val.append(","+item.getValue());
			}
			i++;
		}
		return val.toString();
	}
	@Override
	public void delete(Long[] ids) throws SQLException {
		for (Long id : ids) {
			rentAreaRepository.deleteByBuildingId(id);
		}		
	}	
}
