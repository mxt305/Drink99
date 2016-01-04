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
		ArrayList<User> al = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
				User u = resultSetToEntity(res);
				al.add(u);
			}
			res.close();
			ps.close();
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
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				u = resultSetToEntity(res);
			}
			res.close();
			ps.close();
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
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				u = resultSetToEntity(res);
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public boolean insert(User u) {
			String sql = "INSERT INTO member (id, name, fbID, lastIP, lastDate, regDate) VALUES ( ?, ?, ?, ?, ?, ?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, u.getId());
				ps.setString(2, u.getName());
				ps.setString(3, u.getFbid());
				ps.setString(4, u.getLastIP());
				ps.setDate(5, new java.sql.Date(u.getLastDate().getTime()));
				ps.setDate(6, new java.sql.Date(u.getRegDate().getTime()));
				boolean b = ps.execute();
				ps.close();
				return b;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	public boolean update(User u) {
		String sql = "UPDATE member SET name=?, lastIP=?, lastDate=? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getLastIP());
			ps.setDate(3, new java.sql.Date(u.getLastDate().getTime()));
			ps.setInt(4, u.getId());		
			boolean b = ps.execute();
			ps.close();
			return b;
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
			boolean b = ps.execute();
			ps.close();
			return b;
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
		u.setLastIP(res.getString("lastIP"));
		u.setLastDate(res.getDate("lastDate"));
		u.setRegDate(res.getDate("regDate"));
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
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<User> queryUserInviteByEvent(Event ev){
		String sql = "SELECT * FROM event_join JOIN member ON event_join.memberID = member.id WHERE eventID=? AND status=0";
		ArrayList<User> al = new ArrayList<User>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				User u = resultSetToEntity(res);
				al.add(u);
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	
	public ArrayList<User> queryUserJoinByEvent(Event ev){
		String sql = "SELECT * FROM event_join JOIN member ON event_join.memberID = member.id WHERE eventID=? AND status=1";
		ArrayList<User> al = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				User u = resultSetToEntity(res);
				al.add(u);
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}
	public int getNewId(){
		String sql = "SELECT id FROM member Order BY id DESC LIMIT 1";
		int i = 1;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				int lastId = res.getInt("id");
				i = lastId+1;
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
