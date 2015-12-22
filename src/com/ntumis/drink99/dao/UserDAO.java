package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.User;

public class UserDAO {
	private Connection conn;

	public UserDAO(Connection conn) {
		this.conn = conn;
	}
	
	public ArrayList<User> queryAll(){
		String sql = "SELECT * FROM member";
		ArrayList<User> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.getResultSet();
			while (res.next() && res != null) {
				User u = resultSetToEntity(res);
				al.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public User queryById(int id){
		String sql = "SELECT * FROM member WHERE id=?";
		User u = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ResultSet res = ps.getResultSet();
			if (res.next() && res != null) {
				u = resultSetToEntity(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public User queryByFbId(String fbid){
		String sql = "SELECT * FROM member WHERE fbID=?";
		User u = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fbid);
			ps.execute();
			ResultSet res = ps.getResultSet();
			if (res.next() && res != null) {
				u = resultSetToEntity(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public boolean insert(User u) {
			String sql = "INSERT TO member (id, name, fbID) VALUES ( ?, ?, ?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, u.getId());
				ps.setString(2, u.getName());
				ps.setString(3, u.getFbid());
				return ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	public boolean update(User u) {
		String sql = "UPDATE member SET name=? WHERE eventID=? AND memberID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setInt(2, u.getId());		
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return false;
	}
	
	public boolean delete(User u) {
		String sql = "DELETE FROM member WHERE memberID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getId());
			return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	User resultSetToEntity(ResultSet res) throws SQLException{
		User u = new User();
		u.setId(res.getInt("id"));
		u.setName(res.getString("name"));
		u.setFbid(res.getString("fbID"));
		return u;
	}
	
	public ArrayList<User> queryByEvent(Event ev,int status){
		String sql = "SELECT * FROM event_join JOIN member ON event_join.memberID = member.id  WHERE event_join.eventID=? AND event_join.status=?";
		ArrayList<User> al = new  ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ps.setInt(2, status);
			ResultSet res = ps.getResultSet();
			while (res.next() && res != null) {
				User u = resultSetToEntity(res);
				al.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

}
