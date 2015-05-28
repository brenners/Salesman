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

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.connect.ConnectionManager;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderSet;
import com.reply.salesmen.model.elements.SalesOrder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
		connectStatus = this.conMan.loadSets();
		
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
				mainActivity.showAlertDialog(title, msg);
			}
			
		} else {
			Log.i(logTag, "Not able to show Toast message on MainActivity");
		}
		
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		int size01 = colWrap.getSalesOrderSet().size();
		int size02 = colWrap.getSalesOrderItemSet().size();
		Log.i(logTag, "size salesorders: " + size01 + " / size salesorderitems: " + size02);
				
		Log.i(logTag, "Values of first Object in Collection Wrapper");
		SalesOrderSet so = colWrap.getSalesOrderSet();		
		SalesOrder o = so.getFirst();
		
		if(o != null) {
			Log.i(logTag, ""+o.getAddress());
			Log.i(logTag, ""+o.getContactName());
			Log.i(logTag, ""+o.getCustomerName());
			Log.i(logTag, ""+o.getDescription());
			Log.i(logTag, ""+o.getEmployeeResponsibleName());
			Log.i(logTag, ""+o.getPhone());
			Log.i(logTag, ""+o.getObjectId());
			Log.i(logTag, ""+o.getPostingDate());
		}		
	}
		
}
