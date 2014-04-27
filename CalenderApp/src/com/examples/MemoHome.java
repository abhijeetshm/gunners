package com.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;

public class MemoHome extends Activity implements OnClickListener{
	private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
	TextView txt1 , txt2;
	EditText et;
	Button btn1 , btn2 ;
	String s;
	Memoadapter adapter;
	String time , date ;
	int hourOfDay , minute , year , monthOfYear , dayOfMonth;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.memohome);
		btn1 = (Button)findViewById(R.id.memohomebutton1);
		et = (EditText)findViewById(R.id.memohomeeditText1);
		btn2 = (Button)findViewById(R.id.memohomebutton2);
		  txt1 = (TextView)findViewById(R.id.timesettingtextView1);
	        txt2 = (TextView)findViewById(R.id.timesettingtextView2);
	        Log.i("Memo Home" ,"hour");
	      
	     
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		Intent intent4 = getIntent();
		s= "Selected date from calendar -> " +intent4.getStringExtra("date");

	 
	}
	protected void onStart() {
		super.onStart();
		adapter=MyApplication.getDbAdapter();
		adapter.open(Memoadapter.writeMode);
		  hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
	        Log.i("Memo Home" ,"hour = " + hourOfDay );
          minute = dateTime.get(Calendar.MINUTE);
          Log.i("Memo Home" ,"Minute = " + minute );
	     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
       dateTime.set(Calendar.MINUTE, minute);
     // txt1.setText(timeFormatter.format(dateTime.getTime()));
      time = " at " +timeFormatter.format(dateTime.getTime()).toString();
      Log.i("Memo Home" ,"Time = " + time );
    // time = txt1.getText().toString();
      
      year =  dateTime.get(Calendar.YEAR);
      monthOfYear =  dateTime.get(Calendar.MONTH);
       dayOfMonth = dateTime.get(Calendar.DAY_OF_MONTH);
       dateTime.set(year, monthOfYear, dayOfMonth);
       //txt2.setText(dateFormatter.format(dateTime.getTime()));
      // date = txt2.getText().toString();
       date = "created in " +dateFormatter.format(dateTime.getTime()).toString();
      
	}
    
	@Override
	protected void onStop() {
		super.onStop();
		adapter.close();
	}
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		case R.id.memohomebutton1 :
			et.setVisibility(1);
			btn2.setVisibility(1);
			btn1.setVisibility(v.GONE);
		
			break;
		case R.id.memohomebutton2 :
			
			String msg = et.getText().toString();
			MemoProp mp = new MemoProp(msg , time , date ,s);
			adapter.open(Memoadapter.writeMode);
			adapter.save(mp);
			Intent date = new Intent(this , MemoDisplay.class);
			startActivity(date);
			btn1.setVisibility(1);
			et.setVisibility(v.GONE);
			btn2.setVisibility(v.GONE);
			
			
		}
	
		
	}
}