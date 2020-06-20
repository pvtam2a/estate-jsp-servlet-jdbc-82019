package com.laptrinhjavaweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.service.impl.UserService;
import com.laptrinhjavaweb.utils.FormUtil;

@WebServlet(urlPatterns = { "/admin-home", "/login" })
public class HomeController extends HttpServlet {

	private IUserService userService;	

	public HomeController() {
		userService = new UserService();
	}
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}else if(action != null && action.equals("logout")) {
			
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			UserDTO model = FormUtil.toModel(UserDTO.class, request);		
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
			if(model != null) {
				System.out.println(1);
				response.sendRedirect(request.getContextPath() + "/admin-home");
			}else {
				response.sendRedirect(request.getContextPath() + "/login?action=login");
			}
			
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
