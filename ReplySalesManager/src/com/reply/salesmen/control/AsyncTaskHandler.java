/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 26.05.2015
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

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.connect.ConnectionManager;
import com.reply.salesmen.model.CollectionWrapper;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class AsyncTaskHandler extends AsyncTask<String, Void, Void> {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "AsyncTaskHandler";
	
	private ConnectionManager conMan;
	
	private boolean connectStatus;
	
	// Register Activities
	private MainActivity mainActivity;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	public AsyncTaskHandler() {
		
	}
	
	public AsyncTaskHandler(Activity act) {		
		// initialize Activity Context
		if(act.getClass().toString().equals(ConstantManager.CLASSNAME_MAINACTIVITY))
			mainActivity = (MainActivity) act;
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	@Override
	protected void onPreExecute() {
		Log.i(logTag, "onPreExecute");
		Log.i(logTag, "Connecting...");
		
		// Create Connection Manager
		this.conMan = ConnectionManager.getInstance();
		
		if( mainActivity != null) {
			mainActivity.showProgressDialog("Connecting");
		}		
	}
	
	/* 
	 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
	 */
	@Override
	protected Void doInBackground(String... params) {		
		Log.i(logTag, "doInBackground");
		
		// Respect settings
		SettingsManager sm = SettingsManager.getInstance();
		
		// Do we use Mock Data or real-time Data
		if(sm.isUseMockDataGenerally()) {
			// Generating Mock Data and exit
			Log.i(logTag, "Generating Mock Data");
			CollectionWrapper colWrap = CollectionWrapper.getInstance();
			colWrap.useMockData();
			
			if(mainActivity != null) 
				mainActivity.showToast("Mock Data is generated");
			
			return null;
		}
		
		// Try to connect to the mentioned server
		connectStatus = this.conMan.loadSets();
		
		// If the app is not able to connect to the server,
		// ask for generating Mock Data
		if(connectStatus == false) {
			if(sm.isUseMockData()) {
				// Generating Mock Data
				Log.i(logTag, "Generating Mock Data");
				
				CollectionWrapper colWrap = CollectionWrapper.getInstance();
				colWrap.useMockData();
				
				if(mainActivity != null) 
					mainActivity.showToast("Mock Data is generated");				
			}
		}

		if(mainActivity != null) 
			mainActivity.showToast("Data load was successful");
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		Log.i(logTag, "onProgressUpdate");
	}
	
	@Override
	protected void onPostExecute(Void result) {
		Log.i(logTag, "onPostExecute");
		
		if(mainActivity != null) {
			mainActivity.closeProgessDialog();
			
			// If system is connected show short message,
			// if not show dialog alert box
			if(connectStatus) {
				mainActivity.showToast("Connection Status: " + connectStatus + " - Http Status Code: " + conMan.getStatus());				
			} else {
				String title = "Connection Status:";
				String msg = "Current Connection Status: " + connectStatus + " / Code: " + conMan.getStatus();
				String posButtonTitle = ConstantManager.OK;
				String negButtonTitle = ConstantManager.RETRY_CONNECTION;
					
				// Just show Alert Dialog if data load is not completed
				SettingsManager sm = SettingsManager.getInstance();
				if(!sm.isDataLoadCompleted())
					mainActivity.showAlertDialog(title, msg, posButtonTitle, negButtonTitle);
			}
			
		} else {
			Log.e(logTag, "Not able to show Toast message on MainActivity");
			return;
		}
		
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		int size01 = colWrap.getSalesOrderSet().size();
		int size02 = colWrap.getSalesOrderItemSet().size();
		Log.i(logTag, "Size salesorders: " + size01 + " / size salesorderitems: " + size02);		
	}
		
}
