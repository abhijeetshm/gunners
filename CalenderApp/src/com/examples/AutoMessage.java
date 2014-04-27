package com.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class AutoMessage extends Activity implements OnClickListener{
	ImageButton btn;
	Button btn1;
	String s;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.automessage);
		btn = (ImageButton)findViewById(R.id.automessageimageButton1);
		btn1 = (Button)findViewById(R.id.automessagebutton1);
		btn1.setOnClickListener(this);
		btn.setOnClickListener(new View.OnClickListener() {  
	        public void onClick(View v) { 
	        	String str ="com.sonendra.mishra5";
	        	 Intent intent2 = new Intent(str);
	        	 intent2.putExtra("date", s);
	        	 startActivity(intent2);
                
        }  
});
	    Intent intent = getIntent();
	    s= intent.getStringExtra("date");
	    Log.i("Auto Message" , "Selected date from calender" +s);
	   
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.automessagebutton1)
			finish();

}


}
