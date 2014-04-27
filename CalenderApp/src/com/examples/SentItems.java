package com.examples;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SentItems extends Activity {
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
		 Cursor cmsg = adapter.getAllSendCmsg();
		
			 SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(this,R.layout.sentitemslayout,cmsg,new String[]{
					 "number","message","time" , "date"},new int[]{R.id.sentitemslayouttextView1,R.id.sentitemslayouttextView2,R.id.sentitemslayouttextView3,R.id.sentitemslayouttextView4});
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
