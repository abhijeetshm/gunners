package com.examples;

public class CmsgSavePro {
	int _id ;
	String message,time ,number , date , selecteddate , selectedtime;

	public CmsgSavePro() {
		
	}
	public CmsgSavePro(String number , String message, String time ,String date , String selecteddate , String selectedtime) {
		super();
		this.number = number;
		this.message = message;
		this.date = date;
		this.time = time;
		this.selecteddate = selecteddate;
		this.selectedtime = selectedtime;

	}

	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getSelectedTime() {
		return selectedtime;
	}
	public void setSelectedTime(String selectedtime) {
		this.selectedtime = selectedtime;
	}

}
