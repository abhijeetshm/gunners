package com.examples;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
public class TimeSetting extends Activity implements OnInitListener{
ListView lv;
TimeSettingAdapter adapter;
DateSettingAdapter dadapter;
private Calendar dateTime = Calendar.getInstance();
private SimpleDateFormat dateFormatter = new SimpleDateFormat( "MMMM dd, yyyy");
private SimpleDateFormat timeFormatter = new SimpleDateFormat(  "hh:mm a");
private static final int DIALOG_DATE = 1;
private static final int DIALOG_TIME = 2;
TextView txt1 , txt2;
private int MY_DATA_CHECK_CODE = 0;
private TextToSpeech tts;
String time , str1 , str2 , str3 , str4;
String str33 , str44;
String[] values;
boolean first = false ;
boolean two= false ;
   public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.timesetting);
	        lv=(ListView)findViewById(R.id.timesettinglistView1);
	        txt1 = (TextView)findViewById(R.id.timesettingtextView1);
	        txt2 = (TextView)findViewById(R.id.timesettingtextView2);
			Intent checkIntent = new Intent();
		      checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		      startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
		     txt2.setText(dateFormatter.format(dateTime.getTime()));
		      txt1.setText(timeFormatter.format(dateTime.getTime()));
		      str1 = txt1.getText().toString();
		      str2 = txt2.getText().toString();
		      adapter=MyApplication.getAdapter();
		      adapter.open(TimeSettingAdapter.readMode);
		      Cursor tcursor = adapter.getAllTime();
              if(tcursor.moveToLast())
            {
              	 str33 = tcursor.getString(1);
              	 Log.i("Time setting Database" , "Time = @@"+str33);
              }
              tcursor.close();
              adapter.close();
              dadapter=MyApplication.getdAdapter();
  		    dadapter.open(DateSettingAdapter.readMode);
              Cursor cursor = dadapter.getAllDate();
          	Log.i("Time setting Database" , "Cursor  = @@"+cursor);
          	if(cursor.moveToLast())
          	{
                   str44 = cursor.getString(1);
              	 Log.i("Time setting Database" , "Date = @@"+str44);
          	}
		      MyTimeSettingAdapter.setTimeAndDate(str33, str44);
		    cursor.deactivate();
		      dadapter.close();
	          }
           public void  onStart()
	      {
		 super.onStart();
		 test();
		 adapter=MyApplication.getAdapter();
		 dadapter=MyApplication.getdAdapter();
			dadapter.open(TimeSettingAdapter.writeMode);
			adapter.open(TimeSettingAdapter.writeMode);
		  }
           
           public void test ()
           {
        	   
        	   if(first&& !two)
   			{
   				MyTimeSettingAdapter.setTime(str33);
   				values = new String[] {"Clock", "Date Setting"};
   				lv.setAdapter(new MyTimeSettingAdapter(this, values));
   			}
   			else if(two && !first)
   			{
   				MyTimeSettingAdapter.setDate(str4);
   				values = new String[] {"Clock", "Date Setting"};
   				lv.setAdapter(new MyTimeSettingAdapter(this, values));	
   			}
   			else if(first && two)
   			{
   				MyTimeSettingAdapter.setTimeAndDate(str33, str4);
   				values = new String[] {"Clock", "Date Setting"};
   				lv.setAdapter(new MyTimeSettingAdapter(this, values));	
   			}
   			else
   			{
   				 values = new String[] {"Clock", "Date Setting"};
   					lv.setAdapter(new MyTimeSettingAdapter(this, values));
   			}
   	     lv.setTextFilterEnabled(true);
   	     lv.setOnItemClickListener(new OnItemClickListener() {
   	    	 @Override
   	    	 public void onItemClick(AdapterView<?> a, View v, int position, long id) {
   	    
   	               String s = lv.getItemAtPosition(position).toString();
   	            Log.i("time setting " , "selected value = "+s);
   	            if(s.equalsIgnoreCase("Clock"))
   	            {
   	            	  showDialog(DIALOG_TIME);
                           
                             
                           
   	            }
   	            else 
   	            {
   	            	time ="Welcome user ! You selected Date Setting Option.";
   	            	
   	                if (time!=null && time.length()>0) {
   	            // Toast.makeText(SetAlarmHome.this, "Saying: " + time, Toast.LENGTH_LONG).show();
   	             tts.speak(time, TextToSpeech.QUEUE_FLUSH, null);
   	             showDialog(DIALOG_DATE);
   	           
   	            
   	           
   	            }
   	               
   	           
   	    	 }
   	    	 
   	    	 }
   	    	 }
   	     );
           }
           
           
         
           public void onStop()
	 {
		 super.onStop();
		 adapter.close();
		 dadapter.close();
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
	                    str4 = txt2.getText().toString();
	                    DateSettingFields ds = new DateSettingFields(str4);
	                    dadapter.save(ds);
	                    dadapter.open(TimeSettingAdapter.readMode);
	                    Cursor cursor = dadapter.getAllDate();
	                	Log.i("Time setting Database" , "Cursor  = @@"+cursor);
	                	if(cursor.isLast())
	                	{
	                         str44 = cursor.getString(1);
	                    	 Log.i("Time setting Database" , "Date = @@"+str44);
	                	}
	                	cursor.deactivate();
	                   two = true;
	                   test();
	               
	                }
	            }, dateTime.get(Calendar.YEAR),
	               dateTime.get(Calendar.MONTH),
	               dateTime.get(Calendar.DAY_OF_MONTH));
	 
	        case DIALOG_TIME:
	            return new TimePickerDialog(this, new OnTimeSetListener() {
	 
	                @Override
	                public void onTimeSet(TimePicker view, int hourOfDay,
	                        int minute) {
	                	
	                    dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	                    dateTime.set(Calendar.MINUTE, minute);
	                    txt1.setText(timeFormatter
	                            .format(dateTime.getTime()));
	                    str3 = txt1.getText().toString();
	                    TimeSettingFields ts = new TimeSettingFields(str3);
	                    adapter.save(ts);
	                    adapter.open(TimeSettingAdapter.readMode);
	                    Cursor tcursor = adapter.getAllTime();
	                    if(tcursor.moveToLast())
	                    {
	                    	 str33 = tcursor.getString(1);
	                    	 Log.i("Time setting Database" , "Time = @@"+str33);
	                    }
	                 first = true;
	                 test();
	                }
	            }, dateTime.get(Calendar.HOUR_OF_DAY),
	               dateTime.get(Calendar.MINUTE), false);
	 
	        }
	        return null;
	    }

	
	@Override
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
           // Toast.makeText(TimeSetting.this,    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
        }
        else if (status == TextToSpeech.ERROR) {
          //  Toast.makeText(TimeSetting.this,  "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
        }
    }
           }
