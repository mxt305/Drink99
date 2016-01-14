package com.ntumis.drink99.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ntumis.drink99.dao.EventDAO;
import com.ntumis.drink99.dao.EventJoinDAO;
import com.ntumis.drink99.dao.UserDAO;
import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.User;
import com.ntumis.drink99.util.WebErrorException;

@WebServlet({ "/event/join/data", "/event/join/join", "/event/join/invite", "/event/join/cancel", "/event/join/opt_out" })
public class EventJoinController extends UserJsonPageController {

	private int mode;
	private Event ev;
	private EventJoinDAO dEj;
	private UserDAO dUser;
	private EventDAO dEv;
	private static final long serialVersionUID = 2379818348149455003L;

	@Override
	protected void doPostProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {
		int s = dEj.getUserJoinStatus(ev, getUser());
		try {
			switch (mode) {
			case 0: // join
				if(s == -1){
					boolean b = onInsertData(getUser());
					if(b){
						success("成功加入活動", response);
					} else{
						throw new WebErrorException("加入失敗");
					}
				} else {
					boolean b = onUpdateData(1);
					if(b){
						success("成功加入活動", response);
					} else {
						throw new WebErrorException("加入失敗");
					}
				}
				break;
			case 1: // invite
				User oUser = getUser(request);
				s =  dEj.getUserJoinStatus(ev, oUser);
				if (!getUser().equals(oUser)){
					if(s == -1){
						boolean b = onInsertData(oUser);
						if(b){
							success("邀請成功", response);
						} else{
							throw new WebErrorException("");
						}
					} else if(s == 0) {
						throw new WebErrorException("該用戶已被邀請");
					} else if(s == 1) {
						throw new WebErrorException("該用戶已參加活動");
					} else if(s == 2) {
						throw new WebErrorException("該用戶已退出活動，無法再被邀請");
					} else {
						throw new WebErrorException("參數錯誤");
					}
				} else {
					throw new WebErrorException("無法邀請自己");
				}
				break;
			case 2: // cancel
				if(s == -1){
					throw new WebErrorException("資料不存在");
				} else {
					boolean b = onCancelData();
					if(b){
						success("取消活動成功", response);
					} else{
						throw new WebErrorException("取消失敗");
					}
				}
				break;
			case 3: // opt_out
				if(s == -1){
					throw new WebErrorException("資料不存在");
				} else if(s == 2) {
					throw new WebErrorException("你退出活動失敗");
				} else {
					boolean b = onUpdateData(2);
					if(b){
						success("成功退出活動", response);
					} else{
						throw new WebErrorException("退出活動失敗");
					}
				}
				break;
			}
		} catch (Exception e) {
			throw new WebErrorException(e.getMessage());
		}
	}
	
	private boolean onInsertData(User u){
		if(u.equals(getUser())){
			return dEj.insert(ev, u, 1);
		} else {
			return dEj.insert(ev, u, 0);
		}
	}
	
	private boolean onUpdateData(int status){
		return dEj.update(ev, getUser(), status);
	}
	
	private boolean onCancelData(){
		return dEj.delete(ev, getUser());
	}

	private void getMode(HttpServletRequest request) {
		String path = request.getRequestURI();
		String[] sMode_s = path.split("/");
		String sMode = sMode_s[sMode_s.length - 1];
		if (sMode.equals("join")) {
			mode = 0;
		} else if (sMode.equals("invite")) {
			mode = 1;
		} else if (sMode.equals("cancel")) {
			mode = 2;
		} else if (sMode.equals("opt_out")) {
			mode = 3;
		} else {
			mode = -1;
		}
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (mode == -1) {
			HashMap<String, ArrayList<User>> map = new HashMap<>();
			map.put("invitees", dUser.queryUserInviteByEvent(ev));
			map.put("participants", dUser.queryUserJoinByEvent(ev));
			Gson gson = new Gson();
			String json_string = gson.toJson(map);
			PrintWriter out = response.getWriter();
			out.print(json_string);
		} else {

		}
	}

	@Override
	protected void doPreProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, WebErrorException {
		super.doPreProcess(request, response);
		dUser = new UserDAO(conn);
		dEv = new EventDAO(conn);
		dEj = new EventJoinDAO(conn);
		getMode(request);
		getModel(request);		
		if (ev == null) {
			throw new WebErrorException("該活動不存在");
		}

	}


	private void getModel(HttpServletRequest request) {
		Object oId = request.getParameter("ev");
		try {
			int mId = Integer.parseInt(oId.toString());
			ev = dEv.queryById(mId);
		} catch (Exception e) {
		}
	}
	
	private User getUser(HttpServletRequest request) {
		Object oId = request.getParameter("u");
		try {
			int mId = Integer.parseInt(oId.toString());
			User user = dUser.queryById(mId);
			return user;
		} catch (Exception e) {
		}
		return null;
	}

}

