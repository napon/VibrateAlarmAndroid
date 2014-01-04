package com.example.vibrateapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;



public class SwitchBackOnBroadcastReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent arg1) {
			AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		}
		
		public void setAlarm(Context context, long time)
	    {
	        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	        Intent intent = new Intent(context, SwitchBackOnBroadcastReceiver.class);
	        intent.putExtra("ringtone", Boolean.FALSE);
	        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
	        am.set(AlarmManager.RTC, System.currentTimeMillis()+time,pendingIntent); 
	    }


}
