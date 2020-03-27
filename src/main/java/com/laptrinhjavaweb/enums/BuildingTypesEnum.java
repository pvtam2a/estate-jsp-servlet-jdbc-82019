package com.laptrinhjavaweb.enums;

public enum BuildingTypesEnum {

	TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");
	
    private final String buildingTypeValue;
    
    BuildingTypesEnum(String buildingTypeValue) {
        this.buildingTypeValue = buildingTypeValue;
    }

	public String getBuildingTypeValue() {
		return buildingTypeValue;
	}
	public static String getBuildingTypeValue(String type) {
		String value = "";
		if(type != null && type != "") {
			String[] array = type.split("\\,", -1);
			for (int i = 0; i < array.length; i++) {
				for (BuildingTypesEnum item : BuildingTypesEnum.values()) {
					if(item.name().equals(array[i])) {
						if(i > 0) {
							value += "," +  item.getBuildingTypeValue();
						}else {
							value += item.getBuildingTypeValue();
						}
						
					}
				}
			}		
		}		
		return value;
	}
}
