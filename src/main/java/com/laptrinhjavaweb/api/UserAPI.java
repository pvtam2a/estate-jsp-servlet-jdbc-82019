package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.api.output.StaffOutput;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-user" })
public class UserAPI extends HttpServlet{
	
	private IUserService userService;
	public UserAPI() {
		userService = new UserService();
	}
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String type = request.getParameter("type");		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		if(type != null && type.equals("SHOW_STAFF_ASSIGNMENT")) {
			List<StaffOutput> staffs = new ArrayList<>();
			StaffOutput staffOutput1 = new StaffOutput();
			staffOutput1.setStaffId(2L);
			staffOutput1.setFullName("Nguyễn Văn B");
			staffOutput1.setChecked("checked");
			staffs.add(staffOutput1);
			StaffOutput staffOutput2 = new StaffOutput();
			staffOutput2.setStaffId(3L);
			staffOutput2.setFullName("Nguyễn Văn C");
			staffOutput2.setChecked("");
			staffs.add(staffOutput2);
			StaffOutput staffOutput3 = new StaffOutput();
			staffOutput3.setStaffId(4L);
			staffOutput3.setFullName("Nguyễn Văn D");
			staffOutput3.setChecked("checked");
			staffs.add(staffOutput3);
			mapper.writeValue(response.getOutputStream(), staffs);
		}	
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		UserDTO userDTO =  HttpUtil.of(request.getReader()).toModel(UserDTO.class);
		userDTO = userService.save(userDTO);
		mapper.writeValue(response.getOutputStream(), userDTO);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
}
