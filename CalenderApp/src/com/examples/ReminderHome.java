package com.examples;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class ReminderHome extends Activity implements OnInitListener{
ListView lv;
private int MY_DATA_CHECK_CODE = 0;
private TextToSpeech tts;
String time ;
public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.reminderhome);
	        lv=(ListView)findViewById(R.id.reminderhomelistView1);
			Intent checkIntent = new Intent();
		      checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		      startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	     
	    }
	 public void  onStart()
	 {
		 super.onStart();
		 String[] values = new String[] {"Birth Day", "Meeting" , "Important Dates" , "Others" , "Delete Reminder"};
			lv.setAdapter(new MyArrayAdapterAutoMessageHome(this, values));
	     lv.setTextFilterEnabled(true);
	     lv.setOnItemClickListener(new OnItemClickListener() {
	    	 @Override
	    	 public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	    
	               String s = lv.getItemAtPosition(position).toString();
	            Log.i("Reminder Home " , "selected value = "+s);
	            if(s.equalsIgnoreCase("Birth Day"))
	            {
	            	String str = "com.sonendra.birthday";
	            	Intent birthday = new Intent(str);
	            	startActivity(birthday);
	            }
	            else if(s.equalsIgnoreCase("Meeting"))
	            {
	            	String str = "com.sonendra.meeting";
	            	Intent meeting = new Intent(str);
	            	startActivity(meeting);
	            }
	            else  if(s.equalsIgnoreCase("Important Dates"))
	            {
	            	String str = "com.sonendra.importantdates";
	            	Intent importantdates = new Intent(str);
	            	startActivity(importantdates);
	            }
	            else if(s.equalsIgnoreCase("Others"))
	            {
	            	String str = "com.sonendra.others";
	            	Intent others = new Intent(str);
	            	startActivity(others);
	            }
	            else 
	            {
	            	time ="Welcome user ! You selected Delete Reminder Option.";
	           
	                if (time!=null && time.length()>0) {
	            // Toast.makeText(SetAlarmHome.this, "Saying: " + time, Toast.LENGTH_LONG).show();
	             tts.speak(time, TextToSpeech.QUEUE_FLUSH, null);
	            	String str = "com.sonendra.deletereminder";
	            	Intent deletereminder = new Intent(str);
	            	startActivity(deletereminder);
	            }
	               
	           
	    	 }
	    	 
	    	 }
	    	 }
	     );
	 }
	 public void onStop()
	 {
		 super.onStop();
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
            //Toast.makeText(SetAlarmHome.this,    "Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
        }
        else if (status == TextToSpeech.ERROR) {
         //   Toast.makeText(SetAlarmHome.this,  "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
        }
    }




}
