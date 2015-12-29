package com.ntumis.drink99.entity;

import java.util.ArrayList;

public class CalModel {
	private int success;
	private ArrayList<CalEvent> result;
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public ArrayList<CalEvent> getResult() {
		return result;
	}
	public void setResult(ArrayList<CalEvent> result) {
		this.result = result;
	}
	
}
