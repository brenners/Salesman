/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 28.05.2015
 * Author     : Sven Brenner
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
package com.reply.salesmen.connect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

import com.reply.salesmen.control.SettingsManager;

import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class PropertyEntity {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	private static final String logTag = "PropertyEntity";
	
	private String propertyID = "";
	private Properties properties = null;
	
	// Specific Entities
	private PropertyAppEntity propAppEntity;
	
	/******************************************
	 * 				Constructor 			  
	 * 
	 *****************************************/
		
	public PropertyEntity(String propertyID) {
		
		// Initialize necessary Objects
		this.properties = new Properties();
		
		// Set Property ID
		this.propertyID = propertyID;
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public String getPropertyID() {
		return this.propertyID;
	}
	
	public void readProperties(InputStream inputStream) {
		if(inputStream == null)
			return;
		
		try {
			this.properties.load(inputStream);
			Log.i(logTag, "Properties are loaded");
			inputStream.close();
			this.storeEntityInSpecificObject();
			
		} catch (IOException e) {
			Log.e(logTag, e.toString());
		}
	}
	
	private void storeEntityInSpecificObject() {
		
		switch(this.propertyID) {
			case "app.properties":
				this.propAppEntity = new PropertyAppEntity();				
				
				// get choosen URL
				SettingsManager sm = SettingsManager.getInstance();
				String choosenURL = sm.getURL();
				
				// set values of App Property Object				
				this.propAppEntity.setUrl(properties.getProperty(choosenURL));
				
				this.propAppEntity.setAuthorization(Base64.encodeBase64((properties.getProperty("username") + ":" + properties.getProperty("password")).getBytes()));
				
			break;
		}
	}
	
	public PropertyAppEntity getPropertyAppEntity() {
		return this.propAppEntity;
	}
	
}
