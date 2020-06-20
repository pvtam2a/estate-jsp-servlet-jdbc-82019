package com.laptrinhjavaweb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IRentAreaService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.service.impl.RentAreaService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private IBuildingService buildingService;
	private IBuildingRepository buildingRepository;
	private BuildingConverter buildingConverter;
	private IRentAreaService rentAreaService;
	public BuildingController() {
		buildingService = new BuildingService();
		buildingRepository = new BuildingRepository();
		buildingConverter = new BuildingConverter();
		rentAreaService = new RentAreaService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BuildingDTO building = FormUtil.toModel(BuildingDTO.class, request);		
		String action = request.getParameter("action");
		String url = "";
		if(action != null && action.equals("LIST")) {
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
			List<BuildingDTO> buildings = buildingService.findAll(buildingSearchBuilder, pageable);			
			int totalItems = buildingService.getTotalItem(buildingSearchBuilder);
			if(buildings.size() == 0 && totalItems > 0) {
				pageable.setPage(pageable.getPage() - 1);
				buildings = buildingService.findAll(buildingSearchBuilder, pageable);
			}
			building.setTotalPage((int) Math.ceil((double)totalItems/(double)pageable.getLimit()));
			building.setPage(pageable.getPage());	
			building.setLimit(pageable.getLimit());
			request.setAttribute("buildings", buildings);		
			url = "/views/admin/building/list.jsp";			
		}else if(action != null && action.equals("EDIT")) {
			if(building.getId() != null) {				
				building = buildingConverter.convertToDTO(buildingRepository.findById(building.getId()));
				building.setBuildingTypes(building.getType().split("//,"));
				building.setRentArea(rentAreaService.getRentAreaByBuildingId(building.getId()));
			}
			url = "/views/admin/building/edit.jsp";			
		}
		
		request.setAttribute("districts", buildingService.getDistricts());
		request.setAttribute("buildingTypes", buildingService.getBuildingTypes());
		request.setAttribute("model", building);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
