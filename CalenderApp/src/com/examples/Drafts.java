package com.examples;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Drafts extends Activity {
	ListView lv;
	CmsgAdapter adapter;
	String num , message , time , date ;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sentitems);
        lv = (ListView)findViewById(R.id.sentitemslistView1);
	}
	public void  onStart()
	 {
		 super.onStart();
		 adapter=MyApplication.getcAdapter();
		 adapter.open(CmsgAdapter.readMode);
		 Cursor cmsg = adapter.getAllSaveCmsg();
		
			 SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(this,R.layout.drafts,cmsg,new String[]{
					 "number","message","date" , "time" , "selecteddate" , "selectedtime"},new int[]{R.id.sentitemslayouttextView1,R.id.sentitemslayouttextView2,
					 R.id.sentitemslayouttextView3,R.id.sentitemslayouttextView4 ,R.id.sentitemslayouttextView5,R.id.sentitemslayouttextView6});
			 Log.i("dbtest","list adapter created...");
			 lv.setAdapter(listAdapter);
			 Log.i("dbtest","list adapter provided to the list view...");
	    
	 }
	 public void onStop()
	 {
		 super.onStop();
		 adapter.close();
	 }



}
