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
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.impl.BuildingService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-building"})
public class BuildingController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private IBuildingService buildingService = new BuildingService();

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
			request.setAttribute("buildings", buildings);
			url = "/views/admin/building/list.jsp";			
		}else if(action != null && action.equals("EDIT")) {
			url = "/views/admin/building/edit.jsp";			
		}
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
