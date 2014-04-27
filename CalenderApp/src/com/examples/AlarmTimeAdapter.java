package com.examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class AlarmTimeAdapter {
private static final String dbName="alarmtimetest.db";
private static final String tableName="alarmtime";
private static final int dbVersion=1;
private static final String createQuery="create table alarmtime (_id integer primary key autoincrement, time text );";
private SQLiteDatabase db;
public static final int readMode=1;
public static final int writeMode=2;
private MyDbHelper helper;
public AlarmTimeAdapter(Context ctx)
{
	try
	{
		 helper=new MyDbHelper(ctx,dbName,null,dbVersion);
				Log.i("Calender","helper object created.");
	}catch(Exception e){
		
		Log.i("Calender","Error in creating helper object");
	}
}
public void open(int mode)
{
	try{
	if(mode==readMode)
	{
		Log.i("Calender","Opening database in read mode...");
		db=helper.getReadableDatabase();
		Log.i("Calender","database  opened in read mode...");
	}
	else
	{
		Log.i("Calender","Opening database in write mode...");
		db=helper.getWritableDatabase();
		Log.i("Calender","database  opened in write mode...");
		
	}
	}catch(Exception e)
	{
		Log.i("Calender","Error in opening database.");
		
	}
}
public void close()
{
db.close();
}

public void save(AlarmTimeProp e)
{
	ContentValues row=new ContentValues();
	row.put("time",e.getTime());
	db.insert(tableName, null, row);
	Log.i("Alarm time","Record inserted.");
}
public Cursor getAllTime()
{
	Log.i("Alarm time","Fetching records from the database...");
	return db.query(tableName, new String[]{"_id","time"}, null, null, null, null, null);
}
// static dbhelper inner class starts
public static class MyDbHelper extends SQLiteOpenHelper
{

	public MyDbHelper(Context context, String dbname, CursorFactory factory,
			int dbversion) {
		super(context, dbname, factory, dbversion);
		Log.i("Calender","DbHelper  created.");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createQuery);
		Log.i("Calender","Alarm Time table created.");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	

}

// static dbhelper inner class ends.
}
