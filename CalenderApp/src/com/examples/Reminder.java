package com.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Reminder extends Activity implements OnClickListener{
	ImageButton btn;
	Button btn1;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reminder);
		btn = (ImageButton)findViewById(R.id.reminderimageButton1);
		btn1 = (Button)findViewById(R.id.reminderbutton1);
		btn1.setOnClickListener(this);
		btn.setOnClickListener(new View.OnClickListener() {  
	        public void onClick(View v) { 
	        	String str ="com.sonendra.mishra6";
	        	 Intent intent3 = new Intent(str);
	        	 startActivity(intent3);
                
        }  
});
	    
	   
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.reminderbutton1)
			finish();

}

}
