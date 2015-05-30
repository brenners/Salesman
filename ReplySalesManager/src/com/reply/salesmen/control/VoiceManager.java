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
package com.reply.salesmen.control;

import java.util.ArrayList;

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.view.SalesOrderItemView;
import com.reply.salesmen.view.SalesOrderView;
import com.reply.salesmen.view.SettingsView;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.ArrayAdapter;

/**
 * @author s.brenner
 *
 */
public class VoiceManager extends Activity {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "VoiceManager";
	
	/* Declares a request code, 
	 * this is basically a checksum 
	 * that we use to confirm the 
	 * response when we call out to 
	 * the voice recognition engine, 
	 * this value could be anything you want.  */
	private static final int REQUESTCODE = 1234;
	
	private ArrayList<String> matches = null;
	
	// Reqgister necessary Acitivity Classes (Views)
	private MainActivity mainActivity = null;
	private SalesOrderView salesOrderView = null;
	private SalesOrderItemView salesOrderItemView = null;
	private SettingsView settingsView = null;	
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	public VoiceManager(Activity activity) {
		// initialize Activity Context		
		switch(activity.getClass().toString()) {
			case(ConstantManager.CLASSNAME_MAINACTIVITY):
				mainActivity = (MainActivity) activity;
				break;
		}	
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	/**
     * Fire an intent to start the voice recognition activity.
     */
    public void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition ...");
        startActivityForResult(intent, REQUESTCODE);
    }
	
    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);           
        }
        super.onActivityResult(requestCode, resultCode, data);
        
        // if possible set matches
        setMatches();
    }
    
    public ArrayList<String> getMatches() {
    	return matches;
    }
    
    private void setMatches() {
    	if(mainActivity != null) {
    		/* Test Recognition: if possible navigate to Sales Order view*/
            if(matches.get(0).equals("sales order")) {
            	mainActivity.navigate2Intent(SalesOrderView.class);
            }
    	}
    	mainActivity.wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,matches));
    }
    
}
