package com.ntumis.drink99.entity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Event {
	private int id;
	private String name;
	private User enterpriser;
	private String note;
	private Date date;
	private Time startT;
	//private Time endT;
	private String place;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getEnterpriser() {
		return enterpriser;
	}
	public void setEnterpriser(User enterpriser) {
		this.enterpriser = enterpriser;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getStartT() {
		return startT;
	}
	public void setStartT(Time startT) {
		this.startT = startT;
	}
	/*public Time getEndT() {
		return endT;
	}
	public void setEndT(Time endT) {
		this.endT = endT;
	}*/
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public ArrayList<User> getParticipants(){
		return null;
	}
	
	public ArrayList<User> getInvitees(){
		return null;
	}
	
	public ArrayList<User> getRejectees(){
		return null;
	}
	
	public ArrayList<EventMsg> getMsg(){
		return null;
	}
}
