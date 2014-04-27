package com.examples;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;





public class RepeatAlarmAdapter extends ArrayAdapter<Model> {

	private final List<Model> list;
	private final Activity context;

	public RepeatAlarmAdapter(Activity context, List<Model> list) {
		super(context, R.layout.repaetalarmdisplay, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		protected TextView text;
		protected CheckBox checkbox;
		protected ImageView imageView;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.repaetalarmdisplay, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView)view.findViewById(R.id.repeatalarmdisplayicon);
			viewHolder.text = (TextView) view.findViewById(R.id.repeatalarmdisplaytextView1);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.repeatalarmdisplaycheckBox1);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Model element = (Model) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
				

						}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());
		holder.checkbox.setChecked(list.get(position).isSelected());
		String s = list.get(position).getName();
		if (s.startsWith("Monday"))
		{

			holder.imageView.setImageResource(R.drawable.alarmtime);
		} 
		
		else if(s.startsWith("Tuesday"))
		{
			holder.imageView.setImageResource(R.drawable.speakclock);
		}
		else if(s.startsWith("Wednesday"))
		{
			holder.imageView.setImageResource(R.drawable.alarmtone);
		}
		else if(s.startsWith("Thursday"))
		{
			holder.imageView.setImageResource(R.drawable.alarmtime);
		}
		else if(s.startsWith("Friday"))
		{
			holder.imageView.setImageResource(R.drawable.speakclock);
		}
		else if(s.startsWith("Saturday"))
		{
			holder.imageView.setImageResource(R.drawable.repeatalarm);
		}
		else
		{
			holder.imageView.setImageResource(R.drawable.alarmtime);
		}
		

		return view;
	}
}
