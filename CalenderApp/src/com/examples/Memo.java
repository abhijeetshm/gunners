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

public class Memo extends Activity implements OnClickListener{
	ImageButton btn;
	Button btn1;
	String s;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.memo);
		btn = (ImageButton)findViewById(R.id.memoimageButton1);
		btn1 = (Button)findViewById(R.id.memobutton1);
		btn1.setOnClickListener(this);
		btn.setOnClickListener(new View.OnClickListener() {  
	        public void onClick(View v) { 
	        	String str ="com.sonendra.mishra7";
	        	 Intent intent4 = new Intent(str);
	        	 intent4.putExtra("date", s);
	        	 startActivity(intent4);
                
        }  
});
		Intent intent = getIntent();
		 s= intent.getStringExtra("date");
		Log.i("Memo" ,"Date = "+s);
	   
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.memobutton1)
			finish();

}


}
