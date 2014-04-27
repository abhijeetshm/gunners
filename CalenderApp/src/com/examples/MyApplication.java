package com.examples;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
static Memoadapter memoadapter;
static TimeSettingAdapter tadapter;
static DateSettingAdapter dadapter;
static AlarmTimeAdapter alarmadapter;
static CmsgAdapter cadapter;
@Override
public void onCreate() {
		super.onCreate();
		memoadapter=new Memoadapter(this);
		Log.i("Calender","application object created.");
           tadapter = new TimeSettingAdapter(this);
           dadapter = new DateSettingAdapter(this);
           alarmadapter = new AlarmTimeAdapter(this);
           cadapter = new CmsgAdapter(this);
}


public static Memoadapter getDbAdapter() {
	Log.i("Calender","adapter object requested from the application.");
	return memoadapter;
}

public static TimeSettingAdapter getAdapter() {
	Log.i("Calender","adapter object requested from the application.");
	return tadapter;
}

public static AlarmTimeAdapter getAlarmAdapter() {
	Log.i("Calender","adapter object requested from the application.");
	return alarmadapter;
}

public static DateSettingAdapter getdAdapter() {
	Log.i("Calender","adapter object requested from the application.");
	return dadapter;
}
public static CmsgAdapter getcAdapter() {
	Log.i("Calender","adapter object requested from the application.");
	return cadapter;
}
}
