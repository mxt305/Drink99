package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.util.WebErrorException;

@WebServlet({ "/event/add", "/event/edit" })
public class EventActController extends UserPageController {
	SimpleDateFormat sdf;
	SimpleDateFormat sdf_time;
	private int mode;
	private static final long serialVersionUID = -312343616281541301L;

	public EventActController() {

		sdf = new SimpleDateFormat("y-M-d");
		sdf_time = new SimpleDateFormat("H:m");
	}

	@Override
	protected void doGetProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {
		getMode(request);
		request.setAttribute("mode", mode);
		request.setAttribute("today", sdf.format(new Date()));
		switch (mode) {
		case 0: // add
			showView(request, response);
			break;
		case 1: // edit
			try {
				EventDAO dEvent = new EventDAO(conn);
				Object mId = request.getParameter("id");
				int id = Integer.parseInt(mId.toString());
				Event ev = dEvent.queryById(id);
				if (ev.getEnterpriser().equals(getUser())) {
					request.setAttribute("data", ev);
					showView(request, response);
				} else {
					throw new WebErrorException("�v������");
				}
			} catch (Exception e) {
				throw new WebErrorException(e.getMessage());
			}
			break;
		}

	}

	private void showView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/event_form.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {
		getMode(request);
		Event ev = null;
		EventDAO dEvent = new EventDAO(conn);
		switch (mode) {
		case 0: // add
			ev = getFormData(request, ev);
			ev.setEnterpriser(getUser());
			ev.setId(dEvent.getNewId());
			dEvent.insert(ev);
			break;
		case 1: // edit
			Object mId = request.getParameter("id");
			int id = Integer.parseInt(mId.toString());
			ev = dEvent.queryById(id);
			if (getUser().equals(ev.getEnterpriser())) {
				ev = getFormData(request, ev);
				dEvent.update(ev);
			} else {
				throw new WebErrorException("�v������");
			}
			break;
		}
		redirct(request, response, "/event?id=" + ev.getId());
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
		;
	}

	private Event getFormData(HttpServletRequest request, Event ev) {
		// boolean valid = true;
		try {
			Object mName = request.getParameter("name");
			Object mCat = request.getParameter("cat");
			Object mDate = request.getParameter("date");
			Object mStartT = request.getParameter("startT");
			Object mEndT = request.getParameter("endT");
			Object mPlace = request.getParameter("place");
			Object mNote = request.getParameter("note");
			if (ev == null) {
				ev = new Event();
			}
			ev.setName(mName.toString());
			ev.setCategory(Integer.parseInt(mCat.toString()));
			ev.setDate(sdf.parse(mDate.toString()));
			ev.setStartT(new Time(sdf_time.parse(mStartT.toString()).getTime()));
			ev.setEndT(new Time(sdf_time.parse(mEndT.toString()).getTime()));
			ev.setPlace(mPlace.toString());
			ev.setNote(mNote.toString());
			return ev;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void redirct(HttpServletRequest request, HttpServletResponse response, String url)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + url);
	}
}
