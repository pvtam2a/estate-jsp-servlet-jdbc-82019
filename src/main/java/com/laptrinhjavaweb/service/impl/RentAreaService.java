package com.laptrinhjavaweb.service.impl;

import java.util.Date;

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
	public void save(Long buildingId, String rentArea) {
		if(StringUtils.isNotBlank(rentArea)) {
			String[] arr = rentArea.split("\\,");
			Date createdDate = new Date();
			for (String item : arr) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(item);
				rentAreaEntity.setBuildingId(buildingId);
				rentAreaEntity.setCreatedBy("system");
				rentAreaEntity.setCreatedDate(createdDate);
				rentAreaRepository.insert(rentAreaEntity);
			}
		}			
	}
	
}
