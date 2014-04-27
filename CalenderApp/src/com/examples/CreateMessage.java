package com.examples;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;

public class CreateMessage extends Activity implements OnClickListener{


	Button btn1 , btn2 , btn3 ;
	EditText et1 , et2 ;
	String time , date , selecteddate , cdate ,str1 , str2;
	CmsgAdapter adapter;
	private Calendar dateTime = Calendar.getInstance();
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM dd, yyyy");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
	private static final int DIALOG_DATE = 3;
	private static final int DIALOG_TIME = 2;
	private static final int DIALOG_Alert = 1;
	int hourOfDay , minute , year , monthOfYear , dayOfMonth;
	TextView txt1 , txt2;
	boolean check=false;
	int a =1;
	Intent cmsg;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.createmessage);
      txt1 = (TextView)findViewById(R.id.createmessagetextView1);
      txt2 = (TextView)findViewById(R.id.createmessagetextView2);
        btn1 = (Button)findViewById(R.id.cmsgbutton1);
        btn2 = (Button)findViewById(R.id.cmsgbutton2);
        btn3 = (Button)findViewById(R.id.cmsgbutton3);
        et1 = (EditText)findViewById(R.id.cmsgeditText1);
        et2 = (EditText)findViewById(R.id.cmsgeditText2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        Intent createmessage = getIntent();
        cdate = createmessage.getStringExtra("date");
        Log.i("Create message" , "Selected Date from calendar"+cdate);
    	cmsg = new Intent(this ,GetSelectedDateandTime.class);
	}
	 protected void onStart() {
			super.onStart();
			adapter=MyApplication.getcAdapter();
	
	 }
	 protected void onStop() {
			super.onStop();
			adapter.close();
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
	                    str2 = txt2.getText().toString();
	                    Log.i("Create Message" ,"Selected value from Date picker"+str2);
	                    cmsg.putExtra("selecteddate", str2);
	            
	               
	                }
	            }, dateTime.get(Calendar.YEAR),
	               dateTime.get(Calendar.MONTH),
	               dateTime.get(Calendar.DAY_OF_MONTH));
	 
	        case DIALOG_TIME:
	        	check= true;
	            return new TimePickerDialog(this, new OnTimeSetListener() {
	 
	                @Override
	                public void onTimeSet(TimePicker view, int hourOfDay,
	                        int minute) {
	                	 Log.i("Alarm Time" ,"Start up of onTimeSet() method.");
	                    dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	                    dateTime.set(Calendar.MINUTE, minute);
	                    txt1.setText(timeFormatter
	                            .format(dateTime.getTime()));
	                    str1 = txt1.getText().toString();  
	                    Log.i("Create Message" ,"Selected value from Time picker"+str1);
	                    String number = (et1.getText().toString().trim());
	        			String message = et2.getText().toString();
	                    cmsg.putExtra("selectedtime", str1);
	                    cmsg.putExtra("contactnum", number);
	                    cmsg.putExtra("message", message);
	                    cmsg.putExtra("initialselecteddate" ,cdate);
	                        startActivity(cmsg);
	                   
	                   
	                }
	            }, dateTime.get(Calendar.HOUR_OF_DAY),
	               dateTime.get(Calendar.MINUTE), false);
			case DIALOG_Alert :
	        	
				 final CharSequence[] items = {"Yes" , "NO"};

			 AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Change Selected Date");
				//builder.setIcon(R.drawable.cooltext589940243);
				builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog , int arg1)
				{
					
						
					}


				});
				builder.setCancelable(true);
				builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int item) {
				        Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
				        if(items[item].equals("Yes"))
				        {
				        	showDialog(DIALOG_DATE);
				        
				        }
				        else
				        {
				        	
				        	
				        }
				  
				        
				    }
				});
				AlertDialog alert = builder.create();

				return alert;

	        }
	        
	        return null;
	       
	    }


	@Override
	public void onClick(View v) {
	
		switch(v.getId())
		{
		case  R.id.cmsgbutton1 :
			 hourOfDay =  dateTime.get(Calendar.HOUR_OF_DAY);
		        Log.i("Memo Home" ,"hour = " + hourOfDay );
	        minute = dateTime.get(Calendar.MINUTE);
	        Log.i("Memo Home" ,"Minute = " + minute );
		     dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
	     dateTime.set(Calendar.MINUTE, minute);
	    time = timeFormatter.format(dateTime.getTime()).toString(); 
	    year =  dateTime.get(Calendar.YEAR);
	    monthOfYear =  dateTime.get(Calendar.MONTH);
	     dayOfMonth = dateTime.get(Calendar.DAY_OF_MONTH);
	     dateTime.set(year, monthOfYear, dayOfMonth);
	     date = dateFormatter.format(dateTime.getTime()).toString();
	     String num = et1.getText().toString();
	     String msg = et2.getText().toString();
	     adapter.open(CmsgAdapter.writeMode);
		   CmsgSendPro e = new CmsgSendPro(num , msg , time , date);
		   adapter.save(e);
Intent intent = new Intent(this , MessageSender.class);
 intent.putExtra("message", msg);
 intent.putExtra("number", num);
         sendBroadcast(intent);
			break;
		case R.id.cmsgbutton2 :
			if(a==1)
			showDialog(DIALOG_TIME);
			showDialog(DIALOG_Alert);
			
			
			break;
		case R.id.cmsgbutton3 :
			finish();
		
		}
		
	}

}
