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
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
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
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
				ev = resultSetToEntity(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ev;
	}
	
	public ArrayList<Event> queryByPeriod(Date dStart, Date dEnd){
		String sql = "SELECT * FROM event WHERE (date BETWEEN ? AND ?)";
		ArrayList<Event> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(dStart.getTime()));
			ps.setDate(2, new java.sql.Date(dEnd.getTime()));
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
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
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
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
		//e.setEndT(res.getTime("endT"));
		e.setPlace(res.getString("place"));
		e.setNote(res.getString("note"));
		int userID = res.getInt("enterpriser");
		e.setEnterpriser(ud.queryById(userID));
		return e;
	}
	
	public boolean insert(Event ev) {
		String sql = "INSERT TO event (id, date, name, enterpriser, startT, place, note) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ps.setDate(2, new java.sql.Date(ev.getDate().getTime()));
			ps.setString(3, ev.getName());
			ps.setInt(4, ev.getEnterpriser().getId());
			ps.setTime(5, ev.getStartT());
			ps.setString(6, ev.getPlace());
			ps.setString(7, ev.getNote());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Event ev) {
		String sql = "UPDATE event SET date=?, name=?, enterpriser=?, startT=?, place=?, note=? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(ev.getDate().getTime()));
			ps.setString(2, ev.getName());
			ps.setInt(3, ev.getEnterpriser().getId());
			ps.setTime(4, ev.getStartT());
			ps.setString(5, ev.getPlace());
			ps.setString(6, ev.getNote());
			ps.setInt(7, ev.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int id) {
		String sql = "DELETE FROM event WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
