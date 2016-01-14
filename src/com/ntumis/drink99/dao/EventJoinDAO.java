package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.User;

public class EventJoinDAO {
	private Connection conn;

	public EventJoinDAO(Connection conn) {
		this.conn = conn;
	}

	public boolean insert(Event ev, User u, int status) {
		if (status < 3 || status >= 0) {
			String sql = "INSERT INTO event_join (eventID, memberID, status) VALUES ( ?, ?, ?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, ev.getId());
				ps.setInt(2, u.getId());
				ps.setInt(3, status);
				ps.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean update(Event ev, User u, int status) {
		if (status < 3 || status >= 0) {
			String sql = "UPDATE event_join SET status=? WHERE eventID=? AND memberID=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, status);
				ps.setInt(2, ev.getId());
				ps.setInt(3, u.getId());
				ps.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean delete(Event ev, User u) {
		String sql = "DELETE FROM event_join WHERE eventID=? AND memberID=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ps.setInt(2, u.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getUserJoinStatus(Event ev, User u){
		String sql = "SELECT * FROM `event_join` WHERE `eventID`=? AND `memberID`=?";
		int s = -1;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ps.setInt(2, u.getId());
			ResultSet res = ps.executeQuery();
			if (res.next() && res != null) {
				s = res.getInt("status");
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
		}
		return s;
	}

}
