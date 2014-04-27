package com.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmTimeOK  extends Activity implements OnClickListener{
	 private TextView txt1;
	 Button btn;
 String setalarmtime , str;
 int hourOfDay , minute;
 private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMMM dd, yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat(  "hh:mm a");

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.alarmtimeok);
	        txt1 = (TextView)findViewById(R.id.oktextView1);
	        btn = (Button)findViewById(R.id.okbutton1);
	        btn.setOnClickListener(this);
	        Intent settime = getIntent();
	        setalarmtime = settime.getStringExtra("settime");
	        Toast.makeText(this, "Alarm set  at time = "+setalarmtime , Toast.LENGTH_LONG).show();
	 }

	
	 
	 public void startAlert(View view) {
		
		 hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
        minute = dateTime.get(Calendar.MINUTE);
     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
     dateTime.set(Calendar.MINUTE, minute);
        String s = timeFormatter.format(dateTime.getTime());
        str = setalarmtime;
        
    int difference =0;
	     	try{
		      String s1 = s.substring(3);
		      String s2 = s.substring(0, 2);
		       int a2 = Integer.parseInt(s2);
		       String s3 = s1.substring(0, 2);
		         int a3 = Integer.parseInt(s3);
		       String s4 = s1.substring(2);
		      String str1 = str.substring(3);
		      String str2 = str.substring(0, 2);
		         int b2 = Integer.parseInt(str2);
		       String str3 = str1.substring(0, 2);
		         int b3 = Integer.parseInt(str3);
		       String str4 = str1.substring(2);
		  
		       if(a2==b2)
		       {
		       if((s4.equalsIgnoreCase(" PM")&&str4.equalsIgnoreCase(" PM"))||(s4.equalsIgnoreCase(" AM")&&str4.equalsIgnoreCase(" AM")))
		       {
		          difference = b3-a3;
		        Log.i("Alarm time " , "Difference Minute = "+difference);
		       }
		       else    if((s4.equalsIgnoreCase(" PM")&&str4.equalsIgnoreCase(" AM"))||(s4.equalsIgnoreCase(" AM")&&str4.equalsIgnoreCase(" PM")))
		       {
		           difference = b3-a3 +(12*60);
		         System.out.println("Difference Minute = "+difference);
		       }
		       }
		       else if((a2&b2 )<=12)
		       {
		           if(b2>a2)
		           {
		                if((s4.equalsIgnoreCase(" PM")&&str4.equalsIgnoreCase(" PM"))||(s4.equalsIgnoreCase(" AM")&&str4.equalsIgnoreCase(" AM")))
		       {
		         
		               int diff1 = b2-a2;
		          difference = (b3-a3)+(60*diff1);
		          Log.i("Alarm time " , "Difference Minute = "+difference);
		                 
		       }
		                else
		                {
		                  int diff1 = (12-a2)+b2;
		                    difference = (b3-a3)+(60*diff1);
		                    Log.i("Alarm time " , "Difference Minute = "+difference);
		                  
		                }
		           }
		       
		       }
			
		
	     	}
	     	catch(Exception e)
	     	{
	     		Toast.makeText(this, " Errors = " +e,
						Toast.LENGTH_LONG).show();
	     	}
		//	int i = 10;
	     	int i = (difference*60);
			Intent intent = new Intent(this, MyBroadcastReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					this.getApplicationContext(), 234324243, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
			alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
					+ (i * 1000), pendingIntent);
			Toast.makeText(this, "Alarm set in " + i + " seconds",
					Toast.LENGTH_LONG).show();
			Intent intent2 = new Intent(this , AlarmTimeTone.class);
			PendingIntent p1 = PendingIntent.getActivity(this.getApplicationContext(), 21, intent2, 0);
			AlarmManager alarmManager2 = (AlarmManager) getSystemService(ALARM_SERVICE);
			alarmManager2.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
					+ (i * 1000), p1);
			Toast.makeText(this, "Alarm tone set in " + i + " seconds",
					Toast.LENGTH_LONG).show();
			
				}
	
	@Override
	public void onClick(View v) {
		startAlert(v);
	
		finish();
	}

}
