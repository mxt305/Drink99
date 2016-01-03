package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.dao.EventMsgDAO;
import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.EventMsg;

@WebServlet({ "/event/join/data", "/event/join/join", "/event/join/invite", "/event/join/cancel", "/event/join/opt_out" })
public class EventJoinController extends UserPageController {

	private int mode;
	private Event ev;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventMsgDAO dMsg = new EventMsgDAO(conn);
			EventMsg em = null;
			switch (mode) {
			case 0: // add
				em = getFormData(request, em);
				em.setAuthor(getUser());
				em.setTime(new Date());
				em.setEventID(ev.getId());
				dMsg.insert(em);
				break;
			case 1: // edit
				Object mId = request.getParameter("id");
				int id = Integer.parseInt(mId.toString());
				em = getFormData(request, dMsg.queryById(id));
				dMsg.update(em);
				break;
			}
		} catch (Exception e) {

		}
	}

	private void getMode(HttpServletRequest request) {
		String path = request.getRequestURI();
		String[] sMode_s = path.split("/");
		String sMode = sMode_s[sMode_s.length - 1];
		if (sMode.equals("join")) {
			mode = 0;
		} else if (sMode.equals("invite")) {
			mode = 1;
		} else if (sMode.equals("cancel")) {
			mode = 2;
		} else if (sMode.equals("opt_out")) {
			mode = 3;
		} else {
			mode = -1;
		}
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (mode == -1) {
			EventMsgDAO dMsg = new EventMsgDAO(conn);
			ArrayList<EventMsg> msgs = dMsg.queryByEvent(ev);
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String json_string = gson.toJson(msgs);
			PrintWriter out = response.getWriter();
			out.print(json_string);
		} else {

		}
	}

	@Override
	protected void doPreProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getMode(request);
		getModel(request, conn);
		if (ev == null) {
			// if the event is not exist
			// TODO code here
		}
		response.setHeader("Content-type", "application/json");
		response.setCharacterEncoding("UTF-8");
	}

	private EventMsg getFormData(HttpServletRequest request, EventMsg em) {
		// boolean valid = true;
		try {
			Object mTitle = request.getParameter("title");
			Object mContent = request.getParameter("content");
			if (em == null) {
				em = new EventMsg();
			}
			em.setTitle(mTitle.toString());
			em.setContent(mContent.toString());
			return em;
		} catch (Exception e) {
		}
		return null;
	}

	private void getModel(HttpServletRequest request, Connection conn) {
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			EventDAO dEvent = new EventDAO(conn);
			ev = dEvent.queryById(mId);
		} catch (Exception e) {
		}
	}

}
