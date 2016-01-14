package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.dao.EventJoinDAO;
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
			EventJoinDAO dEj = new EventJoinDAO(conn);
			int status = dEj.getUserJoinStatus(ev, getUser());
			Calendar evStartDate = mergeDate(ev.getDate(), ev.getStartT());
			request.setAttribute("outOfDate", evStartDate.getTime().before(new Date()));
			request.setAttribute("status", status);
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

	private Calendar mergeDate(Date d, Time t){
		  Calendar dCal = Calendar.getInstance();
		  dCal.setTime(d);	  
		  if (t == null){
			  return dCal;
		  }
		  Calendar tCal = Calendar.getInstance();
		  tCal.setTime(t);

		  dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		  dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		  dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));
		return dCal;  
	}
}
