package com.ntumis.drink99.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		if(act!=null && act.equals("logout")){
			HttpSession session = request.getSession();
			session.invalidate();
		}
		redirct(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		redirct(request, response);
	}
	
	private void redirct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect(request.getContextPath() + "/simple");
	}

}
