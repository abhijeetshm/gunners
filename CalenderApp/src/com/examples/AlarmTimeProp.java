package com.examples;

public class AlarmTimeProp {
	int _id;
	String time;

	public AlarmTimeProp() {
		
	}
	public AlarmTimeProp(String time) {
		super();

		this.time = time;
		

	}

	public int get_id() {
		return _id;
	}
	public void set_id(int id) {
		_id = id;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}


}
