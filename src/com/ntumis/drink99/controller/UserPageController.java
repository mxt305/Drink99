package com.ntumis.drink99.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserPageController
 */
public abstract class UserPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean isLogin = false;
		Object o = session.getAttribute("isLogin");
		if(o != null && o instanceof Boolean){
			isLogin = (Boolean) o;
		}
		if(isLogin){
			process(request,response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	abstract protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	

}
