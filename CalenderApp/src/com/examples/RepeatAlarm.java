package com.examples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class RepeatAlarm extends ListActivity {


/** Called when the activity is first created. */

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		RepeatAlarmAdapter adapter = new RepeatAlarmAdapter(this,getModel());
		setListAdapter(adapter);
	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(get("Monday"));
		list.add(get("Tuesday"));
		list.add(get("Wednesday"));
		list.add(get("Thursday"));
		list.add(get("Friday"));
		list.add(get("Saturday"));
		list.add(get("Sunday"));
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}

	private Model get(String s) {
		return new Model(s);
	}



}

