package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.ICustomerRepository;
import com.laptrinhjavaweb.repository.impl.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;

public class CustomerService implements ICustomerService{

	private ICustomerRepository customerRepository;
	private CustomerConverter customerConverter;

	public CustomerService() {
		customerRepository = new CustomerRepository();
		customerConverter = new CustomerConverter();
	}
	@Override
	public List<CustomerDTO> findAll(CustomerSearchBuilder fieldSearch, Pageable pageable) {
		Map<String, Object> properties = convertToMapProperties(fieldSearch);		
		
		List<CustomerEntity> customerEntities = customerRepository.findAll(properties, pageable, fieldSearch);
		
		return customerEntities.stream().map(item -> customerConverter.convertToDTO(item)).collect(Collectors.toList());
		
	}
	
	private Map<String, Object> convertToMapProperties(CustomerSearchBuilder fieldSearch) {
		Map<String, Object> properties = new HashMap<>();	
		try {
			Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
			for (Field field: fields) {	
				if(!field.getName().startsWith("staffId")) {
					field.setAccessible(true);		
					if(field.get(fieldSearch) instanceof String) {						
						properties.put(field.getName().toLowerCase(), field.get(fieldSearch));						
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return properties;
	}
	@Override
	public CustomerDTO save(CustomerDTO customerDTO) {
		CustomerEntity buildingEntity = customerConverter.convertToEntity(customerDTO);
		return customerConverter.convertToDTO(customerRepository.save(buildingEntity));
	}	
}
