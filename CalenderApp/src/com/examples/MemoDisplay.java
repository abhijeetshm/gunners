package com.examples;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MemoDisplay extends Activity implements OnClickListener{
	ListView list ;
	Button btn ;
	Memoadapter adapter; 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.memodisplay1);
		list =(ListView)findViewById(R.id.memodisplay1listView1);
		btn = (Button)findViewById(R.id.memodisplay1button1);
		btn.setOnClickListener(this);
		 

	}
	protected void onStart() {
		super.onStart();
		adapter=MyApplication.getDbAdapter();
		adapter.open(Memoadapter.readMode);
		Log.i("memo","Obtaining cursor...");
		 Cursor memoCursor=adapter.getAllMemo();
		 Log.i("memo","cursor obtained, creting list adapter...");

		 SimpleCursorAdapter listAdapter=new SimpleCursorAdapter(this, R.layout.memodisplay,memoCursor,new String[]{
				 "message","date" , "time"  , "selecteddate"},new int[]{R.id.memodisplaytextView1,R.id.memodisplaytextView2 , R.id.memodisplaytextView3 ,R.id.memodisplaytextView4 });
		 Log.i("memo","list adapter created...");
		 list.setAdapter(listAdapter);
		 Log.i("memo","list adapter provided to the list view...");

	}
    
	@Override
	protected void onStop() {
		super.onStop();
		adapter.close();
	}
	@Override
	public void onClick(View v) {
		finish();
		
	}

}
