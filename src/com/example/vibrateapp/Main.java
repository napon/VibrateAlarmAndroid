package com.example.vibrateapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;



public class Main extends Activity {
	
	private static Context context;


	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();
        
    
        
        Button button1 = (Button) findViewById(R.id.button_ringtone);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            	audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            } 
        });
        
        Button button2 = (Button) findViewById(R.id.button_vibrate);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
				AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            	audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            } 
        });
        
        Button button3 = (Button) findViewById(R.id.button_ringtone_time);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startOffTime();
            	} 
        });
        
        Button button4 = (Button) findViewById(R.id.button_vibrate_time);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	startOnTime();
            } 
        });
    }
    
    private void startOffTime(){
    	Intent intent = new Intent(this, SetOffTime.class);
    	startActivity(intent);
    }
    private void startOnTime(){
    	Intent intent = new Intent(this, SetOnTime.class);
    	startActivity(intent);
    }
    
    
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
}
