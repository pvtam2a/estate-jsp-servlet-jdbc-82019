package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.impl.CustomerService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-customer" })
public class CustomerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	
	private ICustomerService customerService = new CustomerService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CustomerDTO customer = FormUtil.toModel(CustomerDTO.class, request);		
		CustomerSearchBuilder customerSearchBuilder = new CustomerSearchBuilder.Builder()
				.setName(customer.getName())
				.setTel((customer.getTel()))
				.setEmail(customer.getEmail())
				.setStaffId(customer.getStaffId())
				.build();

		Pageable pageable = new PageRequest(customer.getPage(), customer.getLimit());
		List<CustomerDTO> customers = customerService.findAll(customerSearchBuilder, pageable);

		mapper.writeValue(response.getOutputStream(), customers);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		CustomerDTO customerDTO =  HttpUtil.of(request.getReader()).toModel(CustomerDTO.class);
		customerDTO = customerService.save(customerDTO);
		mapper.writeValue(response.getOutputStream(), customerDTO);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
