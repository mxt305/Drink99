package com.ntumis.drink99.entity;

public class JsonErrorModel {
	final private int success = 0;
	private String error;
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getSuccess() {
		return success;
	}
}
