package com.example.vibrateapp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class SetOnTime extends Activity {
	
		private int hour, minute;
		private static final int TIME_DIALOG_ID = 1111;
		private static final int REQUEST_CODE = 1;
		private TextView output;
		private static Context context;
		
		private VibrateOnBroadcastReceiver vAlarm;
		
		@Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_setontime);
            output = (TextView) findViewById(R.id.output2);
            
            vAlarm = new VibrateOnBroadcastReceiver();
            
            Button button = (Button) findViewById(R.id.btn2);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                	showDialog(TIME_DIALOG_ID);
                	} 
            });
    	}
    	
    	 @Override
    	    protected Dialog onCreateDialog(int id) {
    	        switch (id) {
    	        case TIME_DIALOG_ID:
    	             
    	            // set time picker as current time
    	            return new TimePickerDialog(this, timePickerListener, hour, minute,
    	                    false);
    	       
    	        }
    	        return null;
    	    }
    	 
    	    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
    	         
    	 
    	        @Override
    	        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
    	            hour   = hourOfDay;
    	           //this hour is in 24hr format
    	            
    	            minute = minutes;
    	 
    	            updateTime(hour,minute);
    	             
    	         }
    	 
    	    };

    	     
    	    // Used to convert 24hr format to 12hr format with AM/PM values
    	    private void updateTime(int hours, int mins) {
    	         
    	        String timeSet = "";
    	        if (hours > 12) {
    	            hours -= 12;
    	            timeSet = "PM";
    	        } else if (hours == 0) {
    	            hours += 12;
    	            timeSet = "AM";
    	        } else if (hours == 12)
    	            timeSet = "PM";
    	        else
    	            timeSet = "AM";
    	 
    	         
    	        String minutes = "";
    	        if (mins < 10)
    	            minutes = "0" + mins;
    	        else
    	            minutes = String.valueOf(mins);
    	 
    	        // Append in a StringBuilder
    	         String aTime = new StringBuilder().append(hours).append(':')
    	                .append(minutes).append(" ").append(timeSet).toString();
    	 
    	          output.setText(aTime);
    	          
    	          Calendar time = Calendar.getInstance();
    	          time.set(Calendar.DAY_OF_WEEK, time.get(Calendar.DAY_OF_WEEK) + 1);
    	          time.set(Calendar.HOUR_OF_DAY, hours);
    	          time.set(Calendar.MINUTE, mins);
    	          
    	          context = getApplicationContext();
//    	          AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//    	          Intent intent = new Intent(this, VibrateOnBroadcastReceiver.class);
//    	          PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);
//    	          alarmManager.set(AlarmManager.RTC, System.currentTimeMillis()+getAlarmTimeFromNow(),pendingIntent);
    	          
    	          vAlarm.setAlarm(context, getAlarmTimeFromNow());
    	    }
    	    
    	    private long getAlarmTimeFromNow(){
    	    	Calendar c = Calendar.getInstance();
    	    	long min2 = c.get(Calendar.MINUTE);
    	    	long hour2 = c.get(Calendar.HOUR_OF_DAY);
    	    	
    	    	long sec = c.get(Calendar.SECOND);
    	    	
    	    	hour2 = Math.abs(this.hour-hour2);
    	        min2 = Math.abs(this.minute-min2);
    	        long timeInSec = (min2*60)+(hour2*3600)-sec;
    	    	
    	    	
    			return timeInSec*1000;
    	    	
    	    }
    	    
	}
