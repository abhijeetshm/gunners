package com.examples;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyArrayAdapterAutoMessageHome extends ArrayAdapter<String> {
	private final Activity context;
	private final String[] names;

	public MyArrayAdapterAutoMessageHome(Activity context, String[] names) {
		super(context, R.layout.rowlayout, names);
		this.context = context;
		this.names = names;
	}

	// static to save the reference to the outer class and to avoid access to
	// any members of the containing class
	static class ViewHolder {
		public ImageView imageView;
		public TextView textView;
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
			rowView = inflater.inflate(R.layout.rowlayout, null, true);
			holder = new ViewHolder();
			holder.textView = (TextView) rowView.findViewById(R.id.label);
			holder.imageView = (ImageView) rowView.findViewById(R.id.icon);
			rowView.setTag(holder);
		} else {
			holder = (ViewHolder) rowView.getTag();
		}

		holder.textView.setText(names[position]);
		// Change the icon for Windows and iPhone
		String s = names[position];
		if (s.startsWith("Create message"))
		{

			holder.imageView.setImageResource(R.drawable.create_msg);
		} 
		
		else if(s.startsWith("Inbox"))
		{
			holder.imageView.setImageResource(R.drawable.inbox);
		}
		else if(s.startsWith("Sent items"))
		{
			holder.imageView.setImageResource(R.drawable.sentitems);
		}
		else if(s.startsWith("Drafts"))
		{
			holder.imageView.setImageResource(R.drawable.drafts);
		}
		else if(s.startsWith("Delete message"))
		{
			holder.imageView.setImageResource(R.drawable.deletemessage);
		}
		else	if (s.startsWith("Birth Day"))
		{

			holder.imageView.setImageResource(R.drawable.birthday);
		} 
		
		else if(s.startsWith("Meeting"))
		{
			holder.imageView.setImageResource(R.drawable.meeting);
		}
		else if(s.startsWith("Important Dates"))
		{
			holder.imageView.setImageResource(R.drawable.important);
		}
		else if(s.startsWith("Others"))
		{
			holder.imageView.setImageResource(R.drawable.other);
		}
		else if(s.startsWith("Delete Reminder"))
		{
			holder.imageView.setImageResource(R.drawable.deletereminder);
		}
		else if(s.startsWith("Set Alarm"))
		{
			holder.imageView.setImageResource(R.drawable.alarmtone);
		}
		else if(s.startsWith("Memo"))
		{
			holder.imageView.setImageResource(R.drawable.timesetting);
		}
		else if(s.startsWith("Reminder"))
		{
			holder.imageView.setImageResource(R.drawable.repeatalarm);
		}
		else
		{
			holder.imageView.setImageResource(R.drawable.repeatalarm);
		}

		return rowView;
	}
}

