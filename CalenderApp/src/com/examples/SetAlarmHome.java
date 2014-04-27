package com.examples;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class SetAlarmHome extends Activity implements   OnInitListener {
ListView lv;
TextView txt1;
private int MY_DATA_CHECK_CODE = 0;
private TextToSpeech tts;
String time , time1;
int hourOfDay , minute;
private Calendar dateTime = Calendar.getInstance();
private SimpleDateFormat timeFormatter = new SimpleDateFormat(  "hh:mm a");

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.setalarmhome);
	        lv=(ListView)findViewById(R.id.setalarmhomelistView1);
	      txt1 = (TextView)findViewById(R.id.setalarmhometextView1);
	      Intent checkIntent = new Intent();
	      checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	      startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	
	    }
	 public void  onStart()
	 {
		 super.onStart();
		 String[] values = new String[] {"Alarm Time", "Alarm Tone" , "Repaet Alarm" , "Speaking Clock" , "Time Setting"};
			lv.setAdapter(new MyArrayAdapter(this, values));
	     lv.setTextFilterEnabled(true);
	     lv.setOnItemClickListener(new OnItemClickListener() {
	    	 @Override
	    	 public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	    
	               String s = lv.getItemAtPosition(position).toString();
	            Log.i("SetAlarm" , "selected value = "+s);
	            if(s.equalsIgnoreCase("Alarm Time"))
	            {
	            	String str = "com.sonendra.alarmtime";
	            	Intent alarmtime = new Intent(str);
	            	startActivity(alarmtime);
	            }
	            else if(s.equalsIgnoreCase("Alarm Tone"))
	            {
	            	String str = "com.sonendra.alarmtone";
	            	Intent alarmtone = new Intent(str);
	            	startActivity(alarmtone);
	            }
	            else  if(s.equalsIgnoreCase("Repaet Alarm"))
	            {
	            	String str = "com.sonendra.repeatalarm";
	            	Intent repeatalarm = new Intent(str);
	            	startActivity(repeatalarm);
	            }
	            else if(s.equalsIgnoreCase("Time Setting"))
	            {
	            	String str = "com.sonendra.timesetting";
	            	Intent timesetting = new Intent(str);
	            	startActivity(timesetting);
	            }
	            else 
	            {
	            	 hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
	 		        Log.i("Memo Home" ,"hour = " + hourOfDay );
	 	        minute = dateTime.get(Calendar.MINUTE);
	 	        Log.i("Memo Home" ,"Minute = " + minute );
	 		     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	 	     dateTime.set(Calendar.MINUTE, minute);
	 	    time = timeFormatter.format(dateTime.getTime()).toString(); 
	 	    time1 = "The Time is "+timeFormatter.format(dateTime.getTime()).toString();
	 	   if (time1!=null && time1.length()>0) {
          	    Toast.makeText(SetAlarmHome.this, "Saying: " + "The Time is " +time, Toast.LENGTH_LONG).show();
          	    tts.speak(time1, TextToSpeech.QUEUE_FLUSH, null);
          	       }
  	       
	            	  	String str = "com.sonendra.speakingclock";
	            	Intent speakingclock = new Intent(str);
	            	startActivityForResult(speakingclock , 2);
	            }
	               
	           
	    	 }
	    	 
	    	 
	    	 }
	     );
	 }
	 public void onStop()
	 {
		 super.onStop();
	 }
	
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
	        if (requestCode == MY_DATA_CHECK_CODE) {
	            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
	                // success, create the TTS instance
	                tts = new TextToSpeech(this, this);
	            } 
	            else {
	                // missing data, install it
	                Intent installIntent = new Intent();
	                installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
	                startActivity(installIntent);
	            }
	        }

	    }

	    @Override
	    public void onInit(int status) {        
	        if (status == TextToSpeech.SUCCESS) {
	            Toast.makeText(SetAlarmHome.this, 
	                    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
	        }
	        else if (status == TextToSpeech.ERROR) {
	            Toast.makeText(SetAlarmHome.this, 
	                    "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
	        }
	    }
	    			




}
