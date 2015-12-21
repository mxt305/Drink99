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
	
	private User resultSetToEntity(ResultSet res) throws SQLException{
		User u = new User();
		u.setId(res.getInt("id"));
		u.setName(res.getString("name"));
		u.setFbid(res.getString("fbID"));
		return u;
	}
	
	public ArrayList<User> queryByEvent(Event e,int status){
		return null;
	}

}
