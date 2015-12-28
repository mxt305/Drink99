package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.Event;

@WebServlet({"/event/add", "/event/edit"})
public class EventActController extends UserPageController{
	SimpleDateFormat sdf;
	SimpleDateFormat sdf_time;
	private int mode;
	private static final long serialVersionUID = -312343616281541301L;
	
    public EventActController() {
        super();
        sdf = new SimpleDateFormat("y-M-d");
        sdf_time = new SimpleDateFormat("H:m:s");        
    }
	
	@Override
	protected void doGetProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object mId = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(mId.toString());
		} catch (Exception e){
		}
		Connection conn = DBConnector.createConnection();
		EventDAO dEvent = new EventDAO(conn);
		Event ev = null;
		if(id != -1){
			ev = dEvent.queryById(id);
			if (ev != null){
				ev = getFormData(request,ev);
			}
			//TODO dEvent.insert(ev);
		} else {
			ev = getFormData(request,ev);
			//TODO dEvent.update(ev);
		}	
	}
	
	private Event getFormData(HttpServletRequest request,Event ev){
		//boolean valid = true;
		try{
			Object mName = request.getParameter("name");
			Object mDate = request.getParameter("date");
			Object mStartT = request.getParameter("startT");
			Object mPlace  = request.getParameter("place");
			Object mNote = request.getParameter("note");
			if (ev == null){
				ev = new Event();
			}
			ev.setName(mName.toString());
			ev.setDate(sdf.parse(mDate.toString()));
			ev.setStartT(new Time(sdf_time.parse(mStartT.toString()).getTime()));
			ev.setPlace(mPlace.toString());
			ev.setNote(mNote.toString());
			return ev;
		} catch (Exception e){
		}
		return null;
	}
	
	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
