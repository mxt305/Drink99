package com.ntumis.drink99.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ntumis.drink99.dao.DBConnector;
import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.User;

/**
 * Servlet implementation class SimpleController
 */
@WebServlet("/simple")
public class SimpleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * 
     * 
     * @see HttpServlet#HttpServlet()
     */
    public SimpleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnector.createConnection();
		UserDAO dUser = new UserDAO(conn);
		ArrayList<User> alUser = dUser.queryAll();
		request.setAttribute("users", alUser);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/DefaultPage.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
