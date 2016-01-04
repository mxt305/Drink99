package com.ntumis.drink99.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ntumis.drink99.entity.Event;
import com.ntumis.drink99.entity.EventMsg;

public class EventMsgDAO {
	private Connection conn;

	public EventMsgDAO(Connection conn) {
		this.conn = conn;
	}

	public EventMsg queryById(int id){
		String sql = "SELECT * FROM event_msg WHERE id=?";
		EventMsg em = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
				em = resultSetToEntity(res);
			}
			res.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return em;
	}
	
	public ArrayList<EventMsg> queryByEvent(Event ev) {
		String sql = "SELECT * FROM event_msg WHERE EventID=? ORDER BY time DESC";
		ArrayList<EventMsg> al = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ev.getId());
			ResultSet res = ps.executeQuery();
			while (res != null && res.next()) {
				EventMsg em = resultSetToEntity(res);
				al.add(em);
			}
			res.close();
			ps.close();
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insert(EventMsg em) {
		String sql = "INSERT TO event_msg (time, eventID, memberID, title, content) VALUES ( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDate(1, new java.sql.Date(em.getTime().getTime()));
			ps.setInt(2, em.getEventID());
			ps.setInt(3, em.getAuthor().getId());
			ps.setString(4, em.getTitle());
			ps.setString(5, em.getContent());
			boolean b = ps.execute();
			ps.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(EventMsg em) {
		String sql = "UPDATE event_msg SET title=?, content=?, time=? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(4, em.getId());
			ps.setString(1, em.getTitle());
			ps.setString(2, em.getContent());
			ps.setDate(3, new java.sql.Date(em.getTime().getTime()));
			boolean b = ps.execute();
			ps.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int id) {
		String sql = "DELETE FROM event_msg WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			boolean b = ps.execute();
			ps.close();
			return b;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private EventMsg resultSetToEntity(ResultSet res) throws SQLException {
		EventMsg em = new EventMsg();
		em.setId(res.getInt("id"));
		UserDAO ud = new UserDAO(conn);
		em.setAuthor(ud.queryById(res.getInt("memberID")));
		em.setEventID(res.getInt("EventID"));
		em.setTitle(res.getString("title"));
		em.setContent(res.getString("content"));
		em.setTime(res.getDate("time"));
		return em;
	}

}
