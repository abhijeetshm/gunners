package com.examples;

public class MemoProp {
	int _id;
	String message,time , date , selecteddate;

	public MemoProp() {
		
	}
	public MemoProp(String message, String time ,String date , String selecteddate) {
		super();
		this.message = message;
		this.date = date;
		this.time = time;
		this.selecteddate = selecteddate;

	}

	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSelectedDate() {
		return selecteddate;
	}
	public void setSelectedDate(String selecteddate) {
		this.selecteddate = selecteddate;
	}

}
