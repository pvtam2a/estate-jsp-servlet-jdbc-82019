package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.paging.Pageable;

public interface ICustomerService {
	List<CustomerDTO> findAll(CustomerSearchBuilder fieldSearch, Pageable pageable);	
	CustomerDTO save(CustomerDTO customerDTO);
}
