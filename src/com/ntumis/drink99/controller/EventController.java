package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.Event;

@WebServlet("/event")
public class EventController extends UserPageController {

	private Event ev;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBConnector.createConnection();		
		getModel(request,conn);
		if (ev==null){
			//if the event is not exist
			//TODO code here
		} else {
			request.setAttribute("evdata", ev);
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