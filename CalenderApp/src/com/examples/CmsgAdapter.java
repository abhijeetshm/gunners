package com.examples;

import com.examples.Memoadapter.MyDbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class CmsgAdapter {
	
	private static final String dbName="cmsgtest.db";
	private static final String tableName="cmsgsave";
	private static final String tableName1="cmsgsend";
	private static final int dbVersion=1;
	private static final String createQuery="create table cmsgsave (_id integer primary key autoincrement, number text , message text, time text , date text , selecteddate text , selectedtime text);";
	private static final String createQuery1="create table cmsgsend (_id integer primary key autoincrement, number text , message text, time text , date text);";
	private SQLiteDatabase db;
	public static final int readMode=1;
	public static final int writeMode=2;
	private MyDbHelper helper;

	
	public CmsgAdapter(Context ctx)
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
	public void save(CmsgSavePro e)
	{
		ContentValues row=new ContentValues();
		row.put("number", e.getNumber());
		row.put("message",e.getMessage());
		row.put("time",e.getTime());
		row.put("date",e.getDate());
		row.put("selecteddate",e.getSelectedDate());
		row.put("selectedtime",e.getSelectedTime());
		db.insert(tableName, null, row);
		Log.i("Create Message ","Record inserted.");
	}
	public Cursor getAllSaveCmsg()
	{
		Log.i("cmsg","Fetching records from the database...");
		return db.query(tableName, new String[]{"_id","number","message","time", "date" , "selecteddate" , "selectedtime"}, null, null, null, null, null);
	}
	
	
	public void save(CmsgSendPro e)
	{
		ContentValues row=new ContentValues();
		row.put("number", e.getNumber());
		row.put("message",e.getMessage());
		row.put("time",e.getTime());
		row.put("date",e.getDate());
	
		db.insert(tableName1, null, row);
		Log.i("Create Message ","Record inserted.");
	}
	public Cursor getAllSendCmsg()
	{
		Log.i("cmsg","Fetching records from the database...");
		return db.query(tableName1, new String[]{"_id","number","message","time", "date"}, null, null, null, null, null);
	}

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
			Log.i("Calender","cmsgsave table created.");
			db.execSQL(createQuery1);
			Log.i("Calender","cmsgsend table created.");
		
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
			
		}
		

	}


}
