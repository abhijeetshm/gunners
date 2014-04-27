package com.examples;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyTimeSettingAdapter extends ArrayAdapter<String>  {
	private final Activity context;
	private final String[] names;
	static String settime  , setdate;

	public MyTimeSettingAdapter(Activity context, String[] names) {
		super(context, R.layout.rowlayout2, names);
		this.context = context;
		this.names = names;
		
	}

	// static to save the reference to the outer class and to avoid access to
	// any members of the containing class
	static class ViewHolder {
		public TextView textView1;
		public TextView textView2;
	}
	public static  void setTimeAndDate(String time , String date)
{
	settime = time;
	setdate = date;
	}
	public static void setTime(String time)
	{
		settime = time;
	}
	public static void setDate(String date)
	{
		setdate = date;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ViewHolder will buffer the assess to the individual fields of the row
		// layout

		ViewHolder holder;
		// Recycle existing view if passed as parameter
		// This will save memory and time on Android
		// This only works if the base layout for all classes are the same
		View rowView = convertView;
		if (rowView == null) {
			LayoutInflater inflater = context.getLayoutInflater();
			rowView = inflater.inflate(R.layout.rowlayout2, null, true);
			holder = new ViewHolder();
			holder.textView1 = (TextView) rowView.findViewById(R.id.rowlayout2labe1);
			holder.textView2 = (TextView) rowView.findViewById(R.id.rowlayout2labe2);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}
		holder.textView1.setText(names[position]);
		String s = names[position];
		if (s.startsWith("Clock"))
		{
		
			holder.textView2.setText(settime);
		} 
		
		else if(s.startsWith("Date Setting"))
		{
	
			holder.textView2.setText(setdate);
		}
	
		
		

		return rowView;
	}

}

