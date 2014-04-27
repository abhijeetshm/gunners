package com.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GetSelectedDateandTime extends Activity implements OnClickListener{
	TextView txt1 , txt2;
	String time , date , msg  , settime , setdate , initialselecteddate  ;
	CmsgAdapter adapter;
	String num;
	Button btn;
	Intent cmsg;
	private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
	int hourOfDay , minute , year , monthOfYear , dayOfMonth;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.getselecteddateandtime);
        btn = (Button)findViewById(R.id.getselectedbutton1);
        txt1 = (TextView)findViewById(R.id.getselectedtextView1);
        txt2 = (TextView)findViewById(R.id.getselectedtextView2);
        btn.setOnClickListener(this);
         cmsg = getIntent();
        initialselecteddate = cmsg.getStringExtra("initialselecteddate");
        msg = cmsg.getStringExtra("message");
        num = cmsg.getStringExtra("contactnum");
        settime = cmsg.getStringExtra("selectedtime");
       // txt1.setText("Selected time = "+settime);
        //txt2.setText("Selected Date = "+setdate);
       
 
	}
	 protected void onStart() {
			super.onStart();
			adapter=MyApplication.getcAdapter();
			
		
	
	 }
	 protected void onStop() {
			super.onStop();
			adapter.close();
		}

	@Override
	public void onClick(View v) {
		  hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
	        Log.i("Memo Home" ,"hour = " + hourOfDay );
        minute = dateTime.get(Calendar.MINUTE);
        Log.i("Memo Home" ,"Minute = " + minute );
	     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
     dateTime.set(Calendar.MINUTE, minute);
    time = " at " +timeFormatter.format(dateTime.getTime()).toString(); 
    year =  dateTime.get(Calendar.YEAR);
    monthOfYear =  dateTime.get(Calendar.MONTH);
     dayOfMonth = dateTime.get(Calendar.DAY_OF_MONTH);
     dateTime.set(year, monthOfYear, dayOfMonth);
     date = "created in " +dateFormatter.format(dateTime.getTime()).toString();
    setdate= cmsg.getStringExtra("selecteddate");
     	Log.i("Get" , " SEt date %%%%%%%%%% = " + setdate);
      if(setdate!=null)
      {
    		Log.i("Get" , " Not nullllllllllll = " + setdate);  
      }
      else
      {
    	  setdate = initialselecteddate;
    	 	Log.i("Get" , " SEt date eeeeeeeeeeelllllll%%%%% = " + setdate);
      }
   
     Log.i("C msg" ," Selected date from starting = " + initialselecteddate );
	 Log.i("C msg" ," Contact Num. = " + num );
	 Log.i("C msg" ," Entered Message = " + msg );
	  Log.i("C msg" ," Current Date = " + date );
	   Log.i("C msg" ," Current Time = " + time );
	   Log.i("C msg" ," selected Date @@@@@@@@= " + setdate );
	   Log.i("C msg" ," Selected Time = " + settime);
		adapter.open(CmsgAdapter.writeMode);
	   CmsgSavePro e = new CmsgSavePro(num , msg , time , date ,setdate , settime);
	   adapter.save(e);
     
		finish();
	}
	

}
