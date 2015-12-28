package com.ntumis.drink99.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/event/add", "/event/edit"})
public class EventActController extends UserPageController{

	private static final long serialVersionUID = -312343616281541301L;

	@Override
	protected void doGetProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}	

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
	}
	
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
