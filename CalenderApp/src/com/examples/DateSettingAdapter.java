package com.examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DateSettingAdapter {
private static final String dbName="datesettingtest.db";
private static final String tableName1="datesetting";
private static final int dbVersion=1;
private static final String createQuery1="create table datesetting (_id integer primary key autoincrement, date text);";
private SQLiteDatabase db;
public static final int readMode=1;
public static final int writeMode=2;
private MyDbHelper helper1;
public DateSettingAdapter(Context ctx)
{
	try
	{
		 helper1=new MyDbHelper(ctx,dbName,null,dbVersion);
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
		db=helper1.getReadableDatabase();
		Log.i("Calender","database  opened in read mode...");
	}
	else
	{
		Log.i("Calender","Opening database in write mode...");
		db=helper1.getWritableDatabase();
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

public void save(DateSettingFields e)
{
	ContentValues row=new ContentValues();
	row.put("date",e.getDate());
	db.insert(tableName1, null, row);
	Log.i("Date Setting","Record inserted.");
}
public Cursor getAllDate()
{
	Log.i("memo","Fetching records from the database...");
	return db.query(tableName1, new String[]{"_id","date"}, null, null, null, null, null);
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
		db.execSQL(createQuery1);
		Log.i("Calender","Date Setting  table created.");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	

}

// static dbhelper inner class ends.
}
