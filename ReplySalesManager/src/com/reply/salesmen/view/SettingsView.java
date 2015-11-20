package com.reply.salesmen.view;

import java.lang.reflect.Array;

import com.reply.salesmen.R;
import com.reply.salesmen.control.CameraManager;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.control.Voice.VoiceManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
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
		String msg = getResources().getString(R.string.ChoosenUrl);
		msg = msg + ": " + sm.getURL();
		this.setLabelText(R.id.Btn_CheckURL_Label, msg);
	}
	
	private void setLabelText(int id, String msg) {		
		// display url		
		TextView textView = (TextView) findViewById(id);		
		textView.setText(msg);
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
			case(R.id.Btn_CheckURL):											
				AlertDialog.Builder builder = new AlertDialog.Builder(SettingsView.this);
			    builder.setTitle(R.string.ChooseUrlInPicker)
			    		.setItems(this.getItems(), new DialogInterface.OnClickListener() {
			               public void onClick(DialogInterface dialog, int which) {
			            	   SettingsManager sm = SettingsManager.getInstance();
			            	   // The 'which' argument contains the index position
			            	   // of the selected item. The selected item represents the choosen url
			            	   switch(which) {
			            	   case 0:
			            		   sm.setURL("url_test");
			            		   break;
			            	   case 1:
			            		   sm.setURL("url_develop1");
			            		   break;
			            	   case 2:
			            		   sm.setURL("url_develop2");
			            		   break;
		            		   default:
		            			   sm.setURL("url_test");
		            			   break;
			            	   }
			            	   // set label
			            	   String msg = getResources().getString(R.string.ChoosenUrl);
			            	   msg = msg + ": " + sm.getURL();
			            	   setLabelText(R.id.Btn_CheckURL_Label, msg);
			           }
	    		});
			    builder.create();
			    builder.show();
				break;
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
	protected void onDestroy() {
		CameraManager cameraManager = CameraManager.getInstance(this);
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onDestroy();
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
	
	private String[] getItems() {		
		String url_test = getResources().getString(R.string.url_test);
		String url_dev1 = getResources().getString(R.string.url_dep1);
		String url_dev2 = getResources().getString(R.string.url_dep2);
		String[] arr = {url_test, url_dev1, url_dev2};
		return arr;
	}
}
