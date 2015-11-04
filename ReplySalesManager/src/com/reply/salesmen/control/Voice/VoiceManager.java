package com.reply.salesmen.control.Voice;

import java.util.ArrayList;

import android.app.Activity;

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.view.SalesOrderView;
import com.reply.salesmen.view.SettingsView;

public class VoiceManager{
	
	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	private final static String logTag = "VoiceManager";
	
	private static VoiceManager voiceManager;		
	private static VoiceManagerInterface vManager;
	public static Activity currentActivity = null;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private VoiceManager(Activity act) {
		init(act);
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	public static VoiceManager getInstance(Activity act) {
		if(voiceManager == null) {
			voiceManager = new VoiceManager(act);
		}		
		return voiceManager;
	}

	private void init(Activity act) {
		// Choose Voice Recognition based on current used Device		
		SettingsManager sm = SettingsManager.getInstance();
		String deviceName = sm.getCurrentDevice();
		
		if(deviceName.equals("Vuzix M100")) {
			vManager = VoiceManager4Vuzix.getInstance();
			
		} else {
			vManager = VoiceManagerSmartphone.getInstance();		
		}
		
		setActivity(act);
	}
	
	private static void setActivity(Activity activity) {
		// initialize Activity Context		
		switch(activity.getClass().toString()) {
			case(ConstantManager.CLASSNAME_MAINACTIVITY):
				currentActivity = (MainActivity) activity;
				break;
			case(ConstantManager.CLASSNAME_SALESORDER):
				currentActivity = (SalesOrderView) activity;
				break;
			case(ConstantManager.CLASSNAME_SETTINGS):
				currentActivity = (SettingsView) activity;
				break;
		}		
		vManager.defineActivity(currentActivity);
	}
	
	public void start() {		
		vManager.start();
	}
	
	public void stop() {		
		vManager.stop();
	}
}
