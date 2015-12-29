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

@WebServlet({ "/event/add", "/event/edit" })
public class EventActController extends UserPageController {
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
		getMode(request);

		switch (mode) {
		case 1: // add
			//TODO show add view
			break;
		case 2: // edit
			try {
				Connection conn = DBConnector.createConnection();
				EventDAO dEvent = new EventDAO(conn);
				Object mId = request.getParameter("id");
				int id = Integer.parseInt(mId.toString());
				Event ev = dEvent.queryById(id);
				request.setAttribute("data", ev);
				//TODO show add view
			} catch (Exception e) {

			}
			break;
		}

	}
	
	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getMode(request);
		Event ev = null;
		try {
			Connection conn = DBConnector.createConnection();
			EventDAO dEvent = new EventDAO(conn);
			switch (mode) {
			case 1: // add
				ev = getFormData(request, ev);
				// ev.setEnterpriser(user);
				dEvent.insert(ev);
				break;
			case 2: // edit
				Object mId = request.getParameter("id");
				int id = Integer.parseInt(mId.toString());
				ev = getFormData(request, dEvent.queryById(id));
				dEvent.update(ev);
				break;
			}
		} catch (Exception e) {

		}

	}

	private void getMode(HttpServletRequest request) {
		String path = request.getRequestURI();
		String sMode = path.split("/")[3];
		if (sMode.equals("add")) {
			mode = 0;
		} else if (sMode.equals("edit")) {
			mode = 1;
		} else if (sMode.equals("delete")) {
			mode = 2;
		}
		System.out.println(sMode);
	}

	private Event getFormData(HttpServletRequest request, Event ev) {
		// boolean valid = true;
		try {
			Object mName = request.getParameter("name");
			Object mDate = request.getParameter("date");
			Object mStartT = request.getParameter("startT");
			Object mPlace = request.getParameter("place");
			Object mNote = request.getParameter("note");
			if (ev == null) {
				ev = new Event();
			}
			ev.setName(mName.toString());
			ev.setDate(sdf.parse(mDate.toString()));
			ev.setStartT(new Time(sdf_time.parse(mStartT.toString()).getTime()));
			ev.setPlace(mPlace.toString());
			ev.setNote(mNote.toString());
			return ev;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
