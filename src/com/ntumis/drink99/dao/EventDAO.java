package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.User;

public class EventDAO {
	private Connection conn;
	private UserDAO ud;

	public EventDAO(Connection conn) {
		this.conn = conn;
		ud = new UserDAO(conn);
	}
	
	public ArrayList<Event> queryAll(){
		String sql = "SELECT * FROM event";
		ArrayList<Event> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.getResultSet();
			while (res.next() && res != null) {
				Event ev = resultSetToEntity(res);
				al.add(ev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public Event queryById(int id){
		String sql = "SELECT * FROM member WHERE id=?";
		Event ev = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ResultSet res = ps.getResultSet();
			if (res.next() && res != null) {
				ev = resultSetToEntity(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	public ArrayList<Event> queryByMonth(Date sD, Date eD){
		String sql = "SELECT * FROM event WHERE (date BETWEEN ? AND ?)";
		ArrayList<Event> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(sD.getTime()));
			ps.setDate(2, new java.sql.Date(eD.getTime()));
			ResultSet res = ps.getResultSet();
			while (res.next() && res != null) {
				Event ev = resultSetToEntity(res);
				al.add(ev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<Event> queryByUser(User u, int status){
		String sql = "SELECT * FROM event_join JOIN event ON event_join.eventID = event.id  WHERE event_join.memberID=?";
		if(status == 1){
			sql += "AND event.date>=?";
		} else if (status == 2){
			sql += "AND event.date<?";
		}
		ArrayList<Event> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			if(status == 1 || status == 2){
				ps.setDate(2, new java.sql.Date(new Date().getTime()));
			}
			ResultSet res = ps.getResultSet();
			while (res.next() && res != null) {
				Event ev = resultSetToEntity(res);
				al.add(ev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	Event resultSetToEntity(ResultSet res) throws SQLException{
		Event e = new Event();
		e.setId(res.getInt("id"));
		e.setName(res.getString("name"));
		e.setDate(res.getDate("date"));
		e.setStartT(res.getTime("startT"));
		e.setEndT(res.getTime("endT"));
		e.setPlace(res.getString("place"));
		e.setNote(res.getString("note"));
		int userID = res.getInt("enterpriser");
		e.setEnterpriser(ud.queryById(userID));
		return e;
	}	
	
}
