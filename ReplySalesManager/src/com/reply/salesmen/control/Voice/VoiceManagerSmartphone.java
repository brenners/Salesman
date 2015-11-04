/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 29.05.2015
 * Author     : author
 *
 *-----------------------------------------------------------------------------
 * Revision History (Release 1.0.0.0)
 *-----------------------------------------------------------------------------
 * VERSION     AUTHOR/      DESCRIPTION OF CHANGE
 * OLD/NEW     DATE                RFC NO
 *-----------------------------------------------------------------------------
 * --/1.0  | author        | Initial Create.
 *         | dd-mm-yy      |
 *---------|---------------|---------------------------------------------------
 *         | author        | Defect ID 1/Description
 *         | dd-mm-yy      | 
 *---------|---------------|---------------------------------------------------
 */
package com.reply.salesmen.control.Voice;

import java.util.ArrayList;

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.view.SalesOrderItemView;
import com.reply.salesmen.view.SalesOrderView;
import com.reply.salesmen.view.SettingsView;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.ArrayAdapter;

/**
 * @author s.brenner
 *
 */
public class VoiceManagerSmartphone implements VoiceManagerInterface {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "VoiceManagerSmartphone";
	
	private static Activity currentActivity = null;
	
	private static VoiceManagerSmartphone voiceManager; 
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private VoiceManagerSmartphone() {
		init();
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	private void init() {
		
	}
	
	public static VoiceManagerSmartphone getInstance() {
		if(voiceManager == null) {
			voiceManager = new VoiceManagerSmartphone();
		}
		return voiceManager;
	}
	
	/**
     * Fire an intent to start the voice recognition activity.
     */
    public Intent getIntent()
    {
    	// Google Android Voice Recognition ==> Used for Smartphones
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition...");        
        return intent;
    }

	@Override
	public void defineActivity(Activity act) {
		currentActivity = act;
	}

	@Override
	public void start() {
		if(this != null) {
			// push result to current activity
			currentActivity.startActivityForResult(this.getIntent(), VoiceManagerInterface.REQUESTCODE+1);
		}
	}
	
	@Override
	public void stop() {
		// Nothing to Do, intent will destroy automatically 
	}  
}
