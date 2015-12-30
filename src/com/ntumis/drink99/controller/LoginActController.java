package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Time;

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
@WebServlet("/login/fb")
public class LoginActController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginActController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String act = request.getParameter("act");
		if(act!=null && act.equals("logout")){
			HttpSession session = request.getSession();
			session.invalidate();
		}
		redirct(request, response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = getFBUserData(request);
		if(u != null){
			Connection conn = DBConnector.createConnection();
			UserDAO dUser = new UserDAO(conn);
			User ud = dUser.queryByFbId(u.getFbid());
			if (ud == null){
				dUser.insert(u);
				ud = dUser.queryByFbId(u.getFbid());
			}else{
				if (!u.getName().equals(ud.getName())){
					ud.setName(u.getName());
					dUser.update(ud);
				}
			}
			if(ud != null){
				HttpSession session = request.getSession();
				session.setAttribute("user", ud.getId());
			}
		}
		redirct(request, response);
	}
	
	private User getFBUserData(HttpServletRequest request) {
		// boolean valid = true;
		try {
			Object mName = request.getParameter("name");
			Object mFbID = request.getParameter("id");
			//Object mToken = request.getParameter("token");
			User u = new User();
			u.setName(mName.toString());
			u.setFbid(mFbID.toString());			
			return u;
		} catch (Exception e) {
		}
		return null;
	}

	
	private void redirct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect(request.getContextPath() + "/simple");
	}

}
