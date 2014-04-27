package com.examples;
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
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class AutoMessageHome extends Activity implements OnInitListener{
ListView lv;
private int MY_DATA_CHECK_CODE = 0;
private TextToSpeech tts;
String time , date;
public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.automessagehome);
	        lv=(ListView)findViewById(R.id.automessagehomelistView1);
	        
	        
	        Intent intent2 = getIntent();
		    date= intent2.getStringExtra("date");
		    Log.i("Auto Message Home" , "Selected date from calender" +date);

			Intent checkIntent = new Intent();
		      checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		      startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	     
	    }
	 public void  onStart()
	 {
		 super.onStart();
		 String[] values = new String[] {"Create message", "Inbox" , "Sent items" , "Drafts" , "Delete message"};
		 
			lv.setAdapter(new MyArrayAdapterAutoMessageHome(this, values));
	     lv.setTextFilterEnabled(true);
	     lv.setOnItemClickListener(new OnItemClickListener() {
	    	 @Override
	    	 public void onItemClick(AdapterView<?> a, View v, int position, long id) {
	    
	               String s = lv.getItemAtPosition(position).toString();
	            Log.i("Auto message " , "selected value = "+s);
	            if(s.equalsIgnoreCase("Create message"))
	            {
	            	String str = "com.sonendra.createmessage";
	            	Intent createmessage = new Intent(str);
	            	createmessage.putExtra("date", date);
	            	startActivity(createmessage);
	            }
	            else if(s.equalsIgnoreCase("Inbox"))
	            {
	            	String str = "com.sonendra.inbox";
	            	Intent inbox = new Intent(str);
	            	startActivity(inbox);
	            }
	            else  if(s.equalsIgnoreCase("Sent items"))
	            {
	            	String str = "com.sonendra.sentitems";
	            	Intent sentitems = new Intent(str);
	            	startActivity(sentitems);
	            }
	            else if(s.equalsIgnoreCase("Drafts"))
	            {
	            	String str = "com.sonendra.drafts";
	            	Intent drafts = new Intent(str);
	            	startActivity(drafts);
	            }
	            else 
	            {
	            	time ="Welcome user ! You selected Delete Message Option.";
	           
	                if (time!=null && time.length()>0) {
	            // Toast.makeText(SetAlarmHome.this, "Saying: " + time, Toast.LENGTH_LONG).show();
	             tts.speak(time, TextToSpeech.QUEUE_FLUSH, null);
	            	String str = "com.sonendra.deletemessage";
	            	Intent deletemessage = new Intent(str);
	            	startActivity(deletemessage);
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
