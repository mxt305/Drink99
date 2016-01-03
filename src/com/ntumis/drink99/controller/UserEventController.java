package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.User;

@WebServlet("/user/event")
public class UserEventController extends UserJsonPageController {

	private User user;
	private int status = 0;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getModel(request);
		EventDAO dEv = new EventDAO(conn);
		ArrayList<Event> evs = dEv.queryByUser(user, status);
		Gson gson = new Gson();
		String json_string = gson.toJson(evs);
		PrintWriter out = response.getWriter();
		out.print(json_string);
	}

	private void getModel(HttpServletRequest request) {
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			UserDAO dUser = new UserDAO(conn);
			user = dUser.queryById(mId);
		} catch (Exception e) {
			user = getUser();
		}
		Object oStatus= request.getParameter("s");
		try {
			int mStatus = Integer.parseInt(oStatus.toString());
			if (mStatus >= 0 && mStatus <= 2){
				status = mStatus;
			}
		} catch (Exception e){		
		}
	}

}
