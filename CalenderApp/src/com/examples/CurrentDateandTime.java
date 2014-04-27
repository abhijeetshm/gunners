package com.examples;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class CurrentDateandTime extends Activity{
	DatePicker dp;
	TimePicker tp;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.automessage);
		Log.i("Time " ,"Month" +dp.getMonth()+"Year"+dp.getYear()+ "Day" +dp.getDayOfMonth());
	}

}
