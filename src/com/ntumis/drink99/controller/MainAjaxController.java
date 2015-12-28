package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import com.ntumis.drink99.entity.Event;

/**
 * Servlet implementation class JSONController
 * URL pattern: /json/main?y={year}&v={month/week}&mode={mode}
 * Mode: 0=month 1=week
 */
@WebServlet("/json/main")
public class MainAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int mode = 0;
    private Calendar cal;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainAjaxController() {
        super();
        cal = Calendar.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "application/json");
		getDate(request);
		ArrayList<Event> events = getEvents();
		Gson gson = new Gson();
		String json_string = gson.toJson(events);
		PrintWriter out = response.getWriter();
		out.print(json_string);
	}
	
	
	private void getDate(HttpServletRequest request){
		Object oMode = request.getAttribute("mode");
		try {
			int mMode = (int) oMode;
			if (mMode >= 0 && mMode <= 1){
				mode = mMode;
			}
		} catch (Exception e){		
		}
		Object oYear = request.getAttribute("y");
		try {
			int mYear = (int) oYear;
			if (mYear >= 1 && mYear <= 9999){
				cal.set(Calendar.YEAR, mYear);;
			}
		} catch (Exception e){
		}
		Object oValue = request.getAttribute("v");
		try {
			int mValue = (int) oValue;
			if (mode == 1){
				if(mValue > 0 && mValue < cal.getWeeksInWeekYear()){
					cal.setWeekDate(cal.get(Calendar.YEAR), mValue, 1);
				}
			} else {
				if(mValue > 0 && mValue <= 12){
					cal.set(Calendar.MONTH, mValue-1);
				}			
			}
		} catch (Exception e){
		}
	}
	
	private ArrayList<Event> getEvents(){
		Connection conn = DBConnector.createConnection();
		EventDAO dEvent = new EventDAO(conn);
		Date dStart;
		Date dEnd;
		if (mode == 1){
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			dStart = cal.getTime();
			cal.add(Calendar.DATE, 6);
			dEnd = cal.getTime();
		} else {
			cal.set(Calendar.DAY_OF_MONTH, 1);
			dStart = cal.getTime();
			cal.set(Calendar.DAY_OF_MONTH, cal.get(cal.getActualMaximum(Calendar.DAY_OF_MONTH)));
			dEnd = cal.getTime();	
		}
		ArrayList<Event> al = dEvent.queryByPeriod(dStart, dEnd);
		return al;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
