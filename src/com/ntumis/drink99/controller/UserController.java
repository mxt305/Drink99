package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.User;

@WebServlet("/user")
public class UserController extends UserPageController {

	private User user;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getModel(request, conn);
		request.setAttribute("data", user);
		// show view
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user.jsp");
		rd.forward(request, response);
		
	}

	private void getModel(HttpServletRequest request, Connection conn) {
		Object oId = request.getParameter("id");
		try {
			int mId = Integer.parseInt(oId.toString());
			UserDAO dUser = new UserDAO(conn);
			user = dUser.queryById(mId);
		} catch (Exception e) {
			user = getUser();
		}
	}

}
