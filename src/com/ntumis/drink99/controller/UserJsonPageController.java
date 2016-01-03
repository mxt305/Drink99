package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.ntumis.drink99.entity.JsonErrorModel;
import com.ntumis.drink99.util.WebErrorException;

abstract public class UserJsonPageController extends UserPageController{
	
	private static final long serialVersionUID = -7957742911689502662L;
	
	@Override
	protected void doPreProcess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, WebErrorException {
		response.setHeader("Content-type", "application/json");
		response.setCharacterEncoding("UTF-8");	
	}	
	
	@Override
	protected void error(String msg, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		Gson gson = new Gson();
		JsonErrorModel model = new JsonErrorModel();
		String json_string = gson.toJson(model);
		PrintWriter out = response.getWriter();
		out.print(json_string);
	}
	
	protected void success(String msg, HttpServletResponse response) throws IOException, ServletException{
		JSONObject jo = new JSONObject();
		jo.put("success", 1);
		jo.put("msg", msg);
		PrintWriter out = response.getWriter();
		out.print(jo);
	}
	
}
