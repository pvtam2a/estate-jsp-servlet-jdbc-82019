package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.anotation.Column;
import com.laptrinhjavaweb.anotation.Entity;
import com.laptrinhjavaweb.anotation.Table;

@Entity
@Table(name = "rentarea")
public class RentAreaEntity extends BaseEntity {	
	@Column(name = "value")
	private String value;
	
	@Column(name = "buildingid")
	private Long buildingId;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}	
	
	
}

