package com.laptrinhjavaweb.dto;

public class RentAreaDTO extends AbstractDTO {

	private String value;
	private String buildingId;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}	
}
