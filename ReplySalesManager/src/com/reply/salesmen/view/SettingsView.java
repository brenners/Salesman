package com.reply.salesmen.view;

import com.reply.salesmen.R;
import com.reply.salesmen.control.CameraManager;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.control.Voice.VoiceManager;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class SettingsView extends Activity implements SurfaceHolder.Callback  {

	private static final String logTag = "PropertyEntity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);	
		
		// Init URL 
		SettingsManager sm = SettingsManager.getInstance();
		//sm.setURL("url_test");
		
		init();
	}
	
	private void init() {
		this.initCheckBoxes();
        this.initCamera();        
	}
	
	private void initCheckBoxes() {
		initCBSwitchURLButton();		
		initUseMockUpButtons();
	}
	
	private void initCBSwitchURLButton() {
		SettingsManager sm = SettingsManager.getInstance();
		// Switch URL: Initialize Switch Button		
		CheckBox cb_switchUrl = (CheckBox)findViewById(R.id.CB_SwitchURL);		
		// If settings are empty, define url per default
		if(sm.getURL().equals(""))  {			
			// Set URL for Connection
			if(cb_switchUrl.isChecked()) {
				sm.setURL(ConstantManager.URL_DEV);
		    } else {
		    	sm.setURL(ConstantManager.URL_TEST);
		    }
		} else {
			// set url as stored in settings object 
			String currURL = sm.getURL();
			
			switch (currURL) {
				case ConstantManager.URL_DEV:
					cb_switchUrl.setChecked(true);
					break;
				case ConstantManager.URL_TEST:
					cb_switchUrl.setChecked(false);
					break;
			}
		}
	}
	
	private void initUseMockUpButtons() {
		SettingsManager sm = SettingsManager.getInstance();
		
		// Use Mock up data: Initialize check boxes for usage of mock up data 		
		CheckBox cb = (CheckBox)findViewById(R.id.CB_MockData);
		
		if(sm.isUseMockData()) {
			cb.setChecked(true);
		} else {
			cb.setChecked(false);
		}
		
		cb = null;
		
		// Use Mock up data always: Initialize check boxes for usage of mock up data 		
		cb = (CheckBox)findViewById(R.id.CB_MockData_Al);
				
		if(sm.isUseMockDataGenerally()) {
			cb.setChecked(true);
		} else {
			cb.setChecked(false);
		}
		
		
	}
	
	private void initCamera() {
		// Init Camera Manager
        CameraManager cameraManager = CameraManager.getInstance(this);
        
        if(cameraManager.getStatus().equals("Locked")) {
        	
        	SettingsManager sm = SettingsManager.getInstance();
			String deviceName = sm.getCurrentDevice();
			
			if(deviceName.equals("Vuzix M100")) {
				// Special handling for Vuzix
				cameraManager.stopCamera(this);
			}
        	
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
			case (R.id.CB_SwitchURL):
				CheckBox cb_switchUrl= (CheckBox)findViewById(R.id.CB_SwitchURL);
				// Set URL for Connection
				if(cb_switchUrl.isChecked()) {
					sm.setURL("url_develop");
			    } else {
			    	sm.setURL("url_test");
			    }
		}	
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		CameraManager cameraManager = CameraManager.getInstance(this);
        
        if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.setPreviewDisplay(holder);
        	cameraManager.rotateCamera(this);
        }		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}	
	
	@Override
	public void onBackPressed() {
		CameraManager cameraManager = CameraManager.getInstance(this);
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onBackPressed();
	}
}
