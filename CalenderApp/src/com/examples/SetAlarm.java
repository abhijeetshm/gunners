package com.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class SetAlarm extends Activity implements OnClickListener {
	ImageButton btn;
	Button btn1;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setalarm);
		btn = (ImageButton)findViewById(R.id.imageButton1);
		btn1 = (Button)findViewById(R.id.setAlarmbutton1);
		btn1.setOnClickListener(this);
		btn.setOnClickListener(new View.OnClickListener() {  
	        public void onClick(View v) { 
	        	String str ="com.sonendra.mishra4";
	        	 Intent intent1 = new Intent(str);
	        	 startActivity(intent1);
                
        }  
});
	    
	   
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.setAlarmbutton1)
			finish();

}

	
		
	
}