package com.examples;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmTimeTone extends Activity {
	MediaPlayer mediaPlayer;
	Button  buttonQuit , buttonSnooze;
	
	private int stateMediaPlayer;
	PendingIntent p1;
	 AlarmManager manager;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alarmtimetone);
       buttonQuit = (Button)findViewById(R.id.alarmtimetonebutton2);
     //  buttonSnooze = (Button)findViewById(R.id.alarmtimetonebutton1);
       buttonQuit.setOnClickListener(buttonQuitOnClickListener);
     //  buttonSnooze.setOnClickListener(buttonSnoozeOnClickListener);
      
      // Intent launchIntent = new Intent(this, AlarmTimeTone.class);
  //     p1 = PendingIntent.getActivity(this, 02, launchIntent, 0);
       initMediaPlayer();
        
    }
    public void onStrat()
    {
    	super.onStart();
    	
    }
    public void onStop()
    {
    	super.onStop();
   
    }
    private void initMediaPlayer()
    {
    	mediaPlayer = new  MediaPlayer();
        mediaPlayer = MediaPlayer.create(AlarmTimeTone.this, R.raw.s);
       
		mediaPlayer.start();
    }
    /*
    
    Button.OnClickListener buttonSnoozeOnClickListener
	= new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			 manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
			long interval = 20*1000;
			manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+interval, interval, p1);
			finish();
		}	
    };
*/

    Button.OnClickListener buttonQuitOnClickListener
	= new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
		
			mediaPlayer.stop();
			mediaPlayer.release();//releases mediaplayer object,
		
			finish();
			
		}	
    };
}