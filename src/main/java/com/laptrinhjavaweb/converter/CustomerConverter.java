package com.laptrinhjavaweb.converter;

import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;

public class CustomerConverter {

	public CustomerDTO convertToDTO(CustomerEntity entity) {		
		ModelMapper modelMapper = new ModelMapper();		
		CustomerDTO dto = modelMapper.map(entity, CustomerDTO.class);		
		return dto;		
	}
	public CustomerEntity convertToEntity(CustomerDTO dto) {		
		ModelMapper modelMapper = new ModelMapper();		
		CustomerEntity entity = modelMapper.map(dto, CustomerEntity.class);		
		return entity;		
	}
}
