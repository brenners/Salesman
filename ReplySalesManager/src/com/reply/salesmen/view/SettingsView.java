package com.reply.salesmen.view;

import com.reply.salesmen.R;
import com.reply.salesmen.control.CameraManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.control.Voice.VoiceManager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.VideoView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingsView extends Activity implements SurfaceHolder.Callback  {

	private static final String logTag = "PropertyEntity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);	
		
		// Init URL 
		SettingsManager sm = SettingsManager.getInstance();
		sm.setURL("url_test");
		
		init();
	}
	
	private void init() {
		// Init Camera Manager
        CameraManager cameraManager = CameraManager.getInstance();
        
        if(cameraManager.getStatus().equals("Locked")) {
        	// Calculate Screen size
        	Display display = this.getWindowManager().getDefaultDisplay();
    		Point size = new Point();
    		display.getSize(size);
    		int width = size.x;
    		int height = size.y;
    		
    		// Get Video View and define size
			VideoView videoView = (VideoView) this.findViewById(R.id.videoViewSettings);
			videoView.setLayoutParams(new FrameLayout.LayoutParams(width,height));
		    SurfaceHolder holder = videoView.getHolder();		    
		    holder.addCallback(this);
		    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        
        this.initSwitchButton();
	}
	
	private void initSwitchButton() {
		// Switch URL: Initialize Switch Button
		Switch mySwitch = (Switch) findViewById(R.id.switchURL);	 
		//set the switch to ON 
		mySwitch.setChecked(false);
		//attach a listener to check for changes in state
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsManager sm = SettingsManager.getInstance();
				
				// Set URL for Connection
				if(isChecked) {
					sm.setURL("url_develop");
			    } else {
			    	sm.setURL("url_test");
			    }
			}
		};
		mySwitch.setOnCheckedChangeListener(listener);		
	}
		
	public void onClick_Listener(View view) {
		
		SettingsManager sm = SettingsManager.getInstance();
		
		switch(view.getId()) {
			case (R.id.CB_MockData):
				CheckBox cb_mockdata = (CheckBox)findViewById(R.id.CB_MockData);
				sm.setUseMockData(cb_mockdata.isChecked());
				
				break;
			case (R.id.CB_MockData_Al):
				CheckBox cb_mockdata_al = (CheckBox)findViewById(R.id.CB_MockData_Al);
				sm.setUseMockDataGenerally(cb_mockdata_al.isChecked());
				
				break;
		}	
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		CameraManager cameraManager = CameraManager.getInstance();
        
        if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.setPreviewDisplay(holder);
        	cameraManager.rotateCamera(this);
        }		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}	
	
	/*@Override
	protected void onDestroy() {
		CameraManager cameraManager = CameraManager.getInstance();
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onDestroy();
	}*/
	
	@Override
	public void onBackPressed() {
		CameraManager cameraManager = CameraManager.getInstance();
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onBackPressed();
	}
}
