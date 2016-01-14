package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ntumis.drink99.util.WebErrorException;

@WebServlet({ "/event/msg/data", "/event/msg/edit", "/event/msg/add" })
public class MsgController extends UserJsonPageController {

	private int mode;
	private Event ev;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {
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
				success("留言成功", response);
				break;
			case 1: // edit
				Object mId = request.getParameter("id");
				int id = Integer.parseInt(mId.toString());
				em = getFormData(request, dMsg.queryById(id));
				success("編輯留言成功", response);
				dMsg.update(em);
				break;
			case 2: // delete
				Object mId1 = request.getParameter("id");
				int id1 = Integer.parseInt(mId1.toString());
				em = getFormData(request, dMsg.queryById(id1));
				dMsg.delete(id1);
				success("刪除留言成功", response);
			}
		} catch (Exception e) {
			throw new WebErrorException(e.getMessage());
		}
	}

	private void getMode(HttpServletRequest request) {
		String path = request.getRequestURI();
		String[] sMode_s = path.split("/");
		String sMode = sMode_s[sMode_s.length - 1];
		if (sMode.equals("add")) {
			mode = 0;
		} else if (sMode.equals("edit")) {
			mode = 1;
		} else if (sMode.equals("delete")) {
			mode = 2;
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
			throws ServletException, IOException, WebErrorException {
		getMode(request);
		getModel(request);
		if (ev == null) {
			throw new WebErrorException("the event is not exist");
		}
		response.setHeader("Content-type", "application/json");
		response.setCharacterEncoding("UTF-8");
	}

	private EventMsg getFormData(HttpServletRequest request, EventMsg em) throws WebErrorException {
		try {
			Object mTitle = request.getParameter("title");
			Object mContent = request.getParameter("content");
			if (em == null) {
				em = new EventMsg();
			}
			em.setTitle(mTitle.toString());
			if (mContent == null || mContent.toString().equals("")) {
				throw new WebErrorException("內容不能為空");
			}
			em.setContent(mContent.toString());
			return em;
		} catch (NullPointerException e1) {
			throw new WebErrorException("表單內容不正確");
		} catch (Exception e2) {
			throw new WebErrorException(e2.getMessage());
		}
	}

	private void getModel(HttpServletRequest request) {
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			EventDAO dEvent = new EventDAO(conn);
			ev = dEvent.queryById(mId);
		} catch (Exception e) {

		}
	}
}
