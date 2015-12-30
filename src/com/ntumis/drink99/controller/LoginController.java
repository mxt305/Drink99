package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.User;

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
		try{
			String uid = request.getParameter("userid");
			Connection conn = DBConnector.createConnection();
			UserDAO dUser = new UserDAO(conn);
			User ud = dUser.queryById(Integer.parseInt(uid));
			HttpSession session = request.getSession();
			if (ud != null){
				session.setAttribute("user", ud.getId());
			} else {
				request.setAttribute("errMsg", "invalid uid");
			}
		} catch(Exception e){
			
		}
		redirct(request, response);
	}
	
	private void redirct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect(request.getContextPath() + "/simple");
	}

}
