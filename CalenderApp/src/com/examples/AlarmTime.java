package com.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable.Factory;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;

public class AlarmTime extends Activity implements OnClickListener{
	TextView txt1 , txt2;
	Button btn  , submit;
	ToggleButton tb1 , tb2 ;
	TableRow tr ;
	AlarmTimeAdapter adapter;
	NotificationManager NM;
	boolean first = false;
	boolean two = false;
	boolean check= false;
	ImageView img1 , img2 ;
	String s;
	String str;
	String n;
	boolean test=false;
	int hourOfDay , minute;
	EditText et ;
	private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMMM dd, yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat(  "hh:mm a");
	private static final int DIALOG_DATE = 1;
	private static final int DIALOG_TIME = 2;
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.alarmtime);
	        txt1 = (TextView)findViewById(R.id.alarmtimetextView1);
	        txt2 = (TextView)findViewById(R.id.alarmtimetextView2);
	         btn = (Button)findViewById(R.id.alarmtimebutton1);
	         submit = (Button)findViewById(R.id.sonubutton1);
	         et = (EditText)findViewById(R.id.sonueditText1);
	         tr = (TableRow)findViewById(R.id.alarmtimetableRow2);
	         tb1 = (ToggleButton)findViewById(R.id.alarmtimetoggleButton1);
	         tb2 = (ToggleButton)findViewById(R.id.alarmtimetoggleButton2);
	         img1 = (ImageView)findViewById(R.id.alarmtimeimageView1);
	         img2 = (ImageView)findViewById(R.id.alarmtimeimageView2);
	       
	         btn.setOnClickListener(this);
	         tb1.setOnClickListener(this);
	         tb2.setOnClickListener(this);
	         submit.setOnClickListener(this);
	         adapter=MyApplication.getAlarmAdapter();
				adapter.open(AlarmTimeAdapter.readMode);
	        Cursor tcursor = adapter.getAllTime();
	        if(tcursor!= null)
	        {
	        if(tcursor.moveToLast())
	        {
	        	txt1.setText(tcursor.getString(1));
	        }
	      tcursor.deactivate();
	      adapter.close();
	        }
	      
	 }
	 protected void onStart() {
			super.onStart();
			adapter=MyApplication.getAlarmAdapter();
			adapter.open(AlarmTimeAdapter.writeMode);
	
	 }
	 protected void onStop() {
			super.onStop();
			adapter.close();
		}
	
				
	 public void displayNotification(String s)
		{
			Notification n= new Notification(R.drawable.alarmtime , s , 0);
			PendingIntent p1 = PendingIntent.getActivity(this, 0, 
				new Intent(this , AlarmTime.class), PendingIntent.FLAG_CANCEL_CURRENT);
			n.setLatestEventInfo(this, "Welcome", s, p1);
			NM.notify(2, n);
			
		}
	 
	 
	 protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DIALOG_DATE:
	            return new DatePickerDialog(this, new OnDateSetListener() {
	 
	                @Override
	                public void onDateSet(DatePicker view, int year,
	                        int monthOfYear, int dayOfMonth) {
	                    dateTime.set(year, monthOfYear, dayOfMonth);
	                    txt2.setText(dateFormatter
	                            .format(dateTime.getTime()));
	                   // str4 = txt2.getText().toString();
	            
	               
	                }
	            }, dateTime.get(Calendar.YEAR),
	               dateTime.get(Calendar.MONTH),
	               dateTime.get(Calendar.DAY_OF_MONTH));
	 
	        case DIALOG_TIME:
	            return new TimePickerDialog(this, new OnTimeSetListener() {
	 
	                @Override
	                public void onTimeSet(TimePicker view, int hourOfDay,
	                        int minute) {
	                	 Log.i("Alarm Time" ,"Start up of onTimeSet() method.");
	                    dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	                    dateTime.set(Calendar.MINUTE, minute);
	                    txt1.setText(timeFormatter
	                            .format(dateTime.getTime()));
	                 String    str3 = txt1.getText().toString();    
	                    AlarmTimeProp e = new AlarmTimeProp(str3);
	                    adapter.open(AlarmTimeAdapter.writeMode);
	                    adapter.save(e);
	                    Log.i("Alarm Time" ,"Set Alarm time in Dialog box ..@ = "+str3);
	                    String action = "com.sonendra.salil";
	                    Intent settime = new Intent(action);
	                    settime.putExtra("settime", str3);
	                    startActivity(settime);
	                   
	                   
	                }
	            }, dateTime.get(Calendar.HOUR_OF_DAY),
	               dateTime.get(Calendar.MINUTE), false);
	 
	        }
	       
	        return null;
	    }

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.alarmtimebutton1 :
          tr.setVisibility(0);
          check = true;
          break;
		case R.id.alarmtimetoggleButton1 :
				String value = tb1.getText().toString();
				if(check==false)
				{
				if(value.equalsIgnoreCase("ON"))
				{
					img1.setVisibility(0);
				    NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		
			 		displayNotification("Alarm On");
				
					showDialog(DIALOG_TIME);
		          
			 		
			 
					
				}
				else
				{
					img1.setVisibility(v.GONE);
				 NM.cancel(2);
				 
				}
				}
				else
				{
					
					String str1 = tb1.getText().toString();
					String str2 = tb2.getText().toString();
					if(str1.equalsIgnoreCase("ON") || str2.equalsIgnoreCase("ON"))
					{
						if(str1.equalsIgnoreCase("ON"))
						{
							img1.setVisibility(0);
							showDialog(DIALOG_TIME);	
						}
						 NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					 		displayNotification("Alarm On");
					}
					else if(str1.equalsIgnoreCase("OFF")&& str2.equalsIgnoreCase("OFF"))
					{
						img1.setVisibility(v.GONE);
						img2.setVisibility(v.GONE);
						NM.cancel(2);
						
					}
					else
					{
						 NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
					 		displayNotification("Alarm On");
					}
				}
		
			break;
	
		case R.id.alarmtimetoggleButton2 :
			String str1 = tb1.getText().toString();
			String str2 = tb2.getText().toString();
			
			if(str1.equalsIgnoreCase("ON") || str2.equalsIgnoreCase("ON"))
			{
				if(str2.equalsIgnoreCase("ON"))
				{
					img2.setVisibility(0);
					showDialog(DIALOG_TIME);	
				}
				else	if(str2.equalsIgnoreCase("OFF"))
				{
					img2.setVisibility(v.GONE);
			
				}	
				else if(str1.equalsIgnoreCase("ON"))
				{
					img1.setVisibility(0);
				}
				else if(str1.equalsIgnoreCase("OFF"))
				{
					img1.setVisibility(v.GONE);
				}
				 NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			 		displayNotification("Alarm On");
			 		
			}
			else if(str1.equalsIgnoreCase("OFF")&& str2.equalsIgnoreCase("OFF"))
			{
				img1.setVisibility(v.GONE);
				img2.setVisibility(v.GONE);
				NM.cancel(2);
			}
			else
			{
				 NM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			 		displayNotification("Alarm On");
			}
		}
	
	}

}
