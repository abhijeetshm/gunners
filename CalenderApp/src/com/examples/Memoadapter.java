package com.examples;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class Memoadapter {
private static final String dbName="memotest.db";
private static final String tableName="memo";
private static final int dbVersion=1;
private static final String createQuery="create table memo (_id integer primary key autoincrement, message text, time text , date text , selecteddate text);";
private SQLiteDatabase db;
public static final int readMode=1;
public static final int writeMode=2;
private MyDbHelper helper;
public Memoadapter(Context ctx)
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

public void save(MemoProp e)
{
	ContentValues row=new ContentValues();
	row.put("message",e.getMessage());
	row.put("time",e.getTime());
	row.put("date",e.getDate());
	row.put("selecteddate",e.getSelectedDate());
	
	db.insert(tableName, null, row);
	Log.i("Memo Home","Record inserted.");
}
public Cursor getAllMemo()
{
	Log.i("memo","Fetching records from the database...");
	return db.query(tableName, new String[]{"_id","message","time", "date" , "selecteddate"}, null, null, null, null, null);
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
		Log.i("Calender","Memo table created.");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		
	}
	

}

// static dbhelper inner class ends.
}
