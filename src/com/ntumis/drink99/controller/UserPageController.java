package com.ntumis.drink99.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserPageController
 */
public abstract class UserPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static private int METHOD_GET = 1;
    static private int METHOD_POST = 2;
    
    public UserPageController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAuth(request, response, METHOD_GET);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAuth(request, response, METHOD_POST);
	}
	
	private void doAuth(HttpServletRequest request, HttpServletResponse response,int method) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		boolean isLogin = false;
		Object o = request.getAttribute("isLogin");
		if(o != null && o instanceof Boolean){
			isLogin = (Boolean) o;
		}
		if(isLogin){
			switch(method){
			case 1:
				doGetProcess(request,response);
				break;
			case 2:
				doPostProcess(request,response);
				break;
			}
			process(request,response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doGetProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPostProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	abstract protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	

}
