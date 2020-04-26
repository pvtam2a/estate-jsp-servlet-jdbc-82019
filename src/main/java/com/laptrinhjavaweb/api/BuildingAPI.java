package com.laptrinhjavaweb.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.RentAreaService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.HttpUtil;

@WebServlet(urlPatterns = { "/api-building" })
public class BuildingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IBuildingService buildingService = new BuildingService();
	private IRentAreaService rentAreaService = new RentAreaService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
		response) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json"); 
		BuildingDTO building =	FormUtil.toModel(BuildingDTO.class, request); 
		BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
			.setName(building.getName()) .setDistrict(building.getDistrict())
			.setBuildingArea(building.getBuildingArea())
			.setNumberOfBasement(building.getNumberOfBasement())
			.setStreet(building.getStreet()) .setWard(building.getWard())
			.setBuildingTypes(building.getBuildingTypes())
			.setRentAreaFrom(building.getRentAreaFrom())
			.setRentAreaTo(building.getRentAreaTo())
			.setCostRentFrom(building.getCostRentFrom())
			.setCostRentTo(building.getCostRentTo()) .setStaffId(building.getStaffId())
			.build();

		Pageable pageable = new PageRequest(building.getPage(), building.getLimit());
		List<BuildingDTO> buildings = buildingService.findAll(buildingSearchBuilder,
		pageable);

		mapper.writeValue(response.getOutputStream(), buildings);
	}
	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
			buildingDTO = buildingService.save(buildingDTO);
			mapper.writeValue(response.getOutputStream(), buildingDTO);
		}catch (Exception e) {
			mapper.writeValue(response.getOutputStream(), e.getMessage());
		}	
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {	
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);			
			buildingDTO = buildingService.update(buildingDTO);
			mapper.writeValue(response.getOutputStream(), buildingDTO);
		}catch (Exception e) {
			mapper.writeValue(response.getOutputStream(), e.getMessage());
		}		
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		try {			
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			BuildingDTO buildingDTO = HttpUtil.of(request.getReader()).toModel(BuildingDTO.class);
			rentAreaService.delete(buildingDTO.getIds());
			buildingService.delete(buildingDTO.getIds());
			mapper.writeValue(response.getOutputStream(), "{}");
		}catch (Exception e) {
			mapper.writeValue(response.getOutputStream(), e.getMessage());
		}
	}
}
