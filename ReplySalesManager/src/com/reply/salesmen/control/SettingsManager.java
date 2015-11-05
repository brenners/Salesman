/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 28.05.2015
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

import android.graphics.YuvImage;
import android.webkit.WebView.FindListener;

/**
 * @author s.brenner
 *
 */
public class SettingsManager {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static SettingsManager settingsManager;
	
	private boolean isDataLoadCompleted;
	
	private boolean useMockData;	
	private boolean useMockDataGenerally;
	
	private String URL = "";
	
	private int currentObjectId;
	
	private String currentDevice;

	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private SettingsManager() {
		// Initialize Check box		
		setUseMockData(false);
		setUseMockDataGenerally(true);
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public static SettingsManager getInstance() {
		if(SettingsManager.settingsManager == null) {
			SettingsManager.settingsManager = new SettingsManager();
		}
		
		return SettingsManager.settingsManager;
	}

	/**
	 * @return the useMockData
	 */
	public boolean isUseMockData() {
		return useMockData;
	}

	/**
	 * @param useMockData the useMockData to set
	 */
	public void setUseMockData(boolean useMockData) {
		this.useMockData = useMockData;
	}

	/**
	 * @return the useMockDataGenerally
	 */
	public boolean isUseMockDataGenerally() {
		return useMockDataGenerally;
	}

	/**
	 * @param useMockDataGenerally the useMockDataGenerally to set
	 */
	public void setUseMockDataGenerally(boolean useMockDataGenerally) {
		this.useMockDataGenerally = useMockDataGenerally;
	}

	/**
	 * @return the isDataLoadCompleted
	 */
	public boolean isDataLoadCompleted() {
		return isDataLoadCompleted;
	}

	/**
	 * @param isDataLoadCompleted the isDataLoadCompleted to set
	 */
	public void setDataLoadCompleted(boolean isDataLoadCompleted) {
		this.isDataLoadCompleted = isDataLoadCompleted;
	}

	/**
	 * @return the currentObjectId
	 */
	public int getCurrentObjectId() {
		return currentObjectId;
	}

	/**
	 * @param currentObjectId the currentObjectId to set
	 */
	public void setCurrentObjectId(int newCurrentObjectId) {
		currentObjectId = newCurrentObjectId;
	}
	
	/**
	 * @return the URL
	 */
	public String getURL() {
		return URL;
	}

	/**
	 * @param URL the URL to set
	 */
	public void setURL(String newURL) {
		URL = newURL;
	}	

	public String getCurrentDevice() {
		/* Possible parameters
		 * 
		    android.os.Build.VERSION.SDK      // API Level
			android.os.Build.DEVICE           // Device
			android.os.Build.MODEL            // Model 
			android.os.Build.PRODUCT          // Product
		 *
		 *  */
		String myDeviceModel = android.os.Build.MODEL;	
		String myDeviceManufacturer = android.os.Build.MANUFACTURER;
		
		currentDevice = myDeviceManufacturer + " " + myDeviceModel; 
		
		return currentDevice;
	}
}
