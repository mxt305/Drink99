package com.ntumis.drink99.entity;

import java.sql.Time;
import java.util.Date;

import org.jsoup.Jsoup;

public class Event {
	private int id;
	private String name;
	private User enterpriser;
	private String note;
	private Date date;
	private Time startT;
	private Time endT;
	private int category;
	public Time getEndT() {
		return endT;
	}
	public void setEndT(Time endT) {
		this.endT = endT;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		if (category >= 0 && category<=5)
			this.category = category;
		else
			this.category = 0;
	}

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
		this.note = Jsoup.parse(note).text();
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

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
}
