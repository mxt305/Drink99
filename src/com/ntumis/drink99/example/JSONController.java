package com.ntumis.drink99.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class JSONController
 */
@WebServlet("/json")
public class JSONController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JSONController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-type", "application/json");
		ArrayList<Data> al = new ArrayList<>();
		al.add(this.new Data(1,"Mary"));
		al.add(this.new Data(3,"Joe"));
		al.add(this.new Data(4,"Ted"));
		al.add(this.new Data(10,"Tom"));
		al.add(this.new Data(22,"Candy"));
		Gson gson = new Gson();
		String json_string = gson.toJson(al);
		PrintWriter out = response.getWriter();
		out.print(json_string);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private class Data{
		public int id;
		public String name;
		public Data(int id, String name){
			this.id = id;
			this.name = name;
		}
	}
}
