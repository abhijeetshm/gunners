package com.examples;

public class DateSettingFields {
	
	int _id;
	String date;

	public DateSettingFields() {
		
	}
	public DateSettingFields(String date) {
		super();
		this.date = date;
	
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

}
