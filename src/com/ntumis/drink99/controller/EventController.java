package com.ntumis.drink99.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.util.WebErrorException;

@WebServlet("/event")
public class EventController extends UserPageController {

	private Event ev;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {	
		getModel(request);
		if (ev==null){
			throw new WebErrorException("頁面不存在");
		} else {
			request.setAttribute("data", ev);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/event.jsp");
			rd.forward(request, response);
		}
	}
	
	private void getModel(HttpServletRequest request){
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			EventDAO dEvent = new EventDAO(conn);
			ev = dEvent.queryById(mId);
		} catch (Exception e){
		}
	}

}
