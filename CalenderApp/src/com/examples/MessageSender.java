package com.examples;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MessageSender extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {

		String num = intent.getStringExtra("number");
	String msg = intent.getStringExtra("message");
	SmsManager manager = SmsManager.getDefault();
	manager.sendTextMessage(num, null, msg, null, null);
	Toast.makeText(context.getApplicationContext(), " Message send Successfully..", Toast.LENGTH_LONG).show();
		
	}

}
