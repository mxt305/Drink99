package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.CalEvent;
import com.ntumis.drink99.entity.CalModel;
import com.ntumis.drink99.entity.Event;

/**
 * Servlet implementation class JSONController
 * URL pattern: /json/main?from={time_form}&to={time_to}
 */
@WebServlet("/json/main")
public class MainAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private long time_from;
    private long time_to;
	protected Connection conn;

	@Override
	public void init() throws ServletException {
		super.init();
		conn = DBConnector.createConnection();
	}

	@Override
	protected void finalize() throws Throwable {
		if(conn != null && !conn.isClosed()){
			conn.close();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainAjaxController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "application/json");
		response.setCharacterEncoding("UTF-8");
		getDate(request);	
		ArrayList<Event> events = getEvents();
		CalModel model = new CalModel();
		model.setSuccess(1);
		ArrayList<CalEvent> alRes = new ArrayList<CalEvent>();
		String[] path_strs = request.getRequestURI().split("/");
		for(Event ev: events){
			CalEvent cEv = new CalEvent();
			cEv.setId(ev.getId());
			cEv.setTitle(String.format("%s (by %s)", ev.getName(), ev.getEnterpriser().getName()));
			cEv.setUrl(String.format("/%s/event?id=%d", path_strs[1], ev.getId()));
			cEv.setClass_name("event-info");
			cEv.setStart(mergeDate(ev.getDate(),ev.getStartT()).getTimeInMillis());
			cEv.setEnd(mergeDate(ev.getDate(),ev.getEndT()).getTimeInMillis());
			alRes.add(cEv);
		}
		model.setResult(alRes);
		Gson gson = new Gson();
		String json_string = gson.toJson(model);
		PrintWriter out = response.getWriter();
		out.print(json_string);
	}
	
	private Calendar mergeDate(Date d, Time t){
		  Calendar dCal = Calendar.getInstance();
		  dCal.setTime(d);
		  Calendar tCal = Calendar.getInstance();
		  tCal.setTime(t);

		  dCal.set(Calendar.HOUR_OF_DAY, tCal.get(Calendar.HOUR_OF_DAY));
		  dCal.set(Calendar.MINUTE, tCal.get(Calendar.MINUTE));
		  dCal.set(Calendar.SECOND, tCal.get(Calendar.SECOND));
		return dCal;  
	}
	
	
	private void getDate(HttpServletRequest request){
		Object mFrom = request.getParameter("from");
		try {
			time_from = Long.parseLong(mFrom.toString()) ;
		} catch (Exception e){		
		}
		Object mTo = request.getParameter("to");
		try {
			time_to = Long.parseLong(mTo.toString()) ;
		} catch (Exception e){		
		}
	}
	
	private ArrayList<Event> getEvents(){
		EventDAO dEvent = new EventDAO(conn);
		Date dStart = new Date(time_from);
		Date dEnd= new Date(time_to);	
		ArrayList<Event> al = dEvent.queryByPeriod(dStart, dEnd);
		return al;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
