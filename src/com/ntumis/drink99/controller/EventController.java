package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.Event;

@WebServlet("/event")
public class EventController extends UserPageController {

	private Event ev;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		getModel(request,conn);
		if (ev==null){
			//if the event is not exist
			//TODO code here
		} else {
			request.setAttribute("data", ev);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/event.jsp");
			rd.forward(request, response);
			//TODO show event view
		}
	}
	
	private void getModel(HttpServletRequest request,Connection conn){
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			EventDAO dEvent = new EventDAO(conn);
			ev = dEvent.queryById(mId);
		} catch (Exception e){
		}
	}

}
