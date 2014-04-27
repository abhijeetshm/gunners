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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SpeakingClock extends Activity {
	TextView tx;
	    Button btn;
	    private int MY_DATA_CHECK_CODE = 0;
	    private TextToSpeech tts;
	    String time , time1;
	    int hourOfDay , minute;
	    private Calendar dateTime = Calendar.getInstance();
	    private SimpleDateFormat timeFormatter = new SimpleDateFormat(  "hh:mm a");

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.speakingclock);
	        tx = (TextView)findViewById(R.id.speakingclocktextView2);
	        btn = (Button)findViewById(R.id.speakingclockbutton1);
	        hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
		        Log.i("Memo Home" ,"hour = " + hourOfDay );
	        minute = dateTime.get(Calendar.MINUTE);
	        Log.i("Memo Home" ,"Minute = " + minute );
		     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	     dateTime.set(Calendar.MINUTE, minute);
	    time = timeFormatter.format(dateTime.getTime()).toString(); 
	    time1 = "The Time is "+timeFormatter.format(dateTime.getTime()).toString();
            tx.setText(time);
             btn.setOnClickListener(new OnClickListener() {            
	        	   @Override
	        	   public void onClick(View v) {
	        		   
	        		  
	        	     finish();
	        	   }
	        	      });
	        	  
	        	        
	        	    }
	        }
