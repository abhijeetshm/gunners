package com.examples;

public class CmsgSendPro {

	int _id  ;
	String message,time , number , date;

	public CmsgSendPro() {
		
	}
	public CmsgSendPro(String number , String message, String time ,String date ) {
		super();
		this.number = number;
		this.message = message;
		this.date = date;
		this.time = time;
		

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
	

}
