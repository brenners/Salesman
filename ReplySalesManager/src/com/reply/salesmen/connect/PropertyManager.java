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
package com.reply.salesmen.connect;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import com.reply.salesmen.MainActivity;

import android.content.res.AssetManager;
import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class PropertyManager {

	/* To Do: Implementieren einer Query Methode zum Zugriff spezieller Entities */
	
	
	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static final String logTag = "PropertyManager";
	
	private static PropertyManager propMan;
	private static AssetManager assetManager;
	private static ArrayList<PropertyEntity> properties = null;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private PropertyManager() {		
		PropertyManager.properties = new ArrayList<PropertyEntity>();
		PropertyManager.assetManager = MainActivity.assetManager;
	}
	
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public static PropertyManager getInstance() {
		if(PropertyManager.propMan == null) {
			PropertyManager.propMan = new PropertyManager(); 
		}
		
		return PropertyManager.propMan;
	}
	
	private PropertyEntity readPropertyFileFromAssets(String file) {
			
		String filename = "";
		
		if(!file.equals("")) {
			// add file identification
			String prop = ".properties";
			filename = file + prop;
			
		} else {
			return null;
		}
		
		InputStream inputStream = null;
		
		// Read from the './assets' directory
		try {
		    inputStream = PropertyManager.assetManager.open(filename);		 
		    
		    // Define Property Entity Object
		    PropertyEntity propEntity = new PropertyEntity(filename);
		    propEntity.readProperties(inputStream);
		    
		    // Store Property Entity Object into ArrayList
		    PropertyManager.properties.add(propEntity);
		    
		    Log.i(logTag, "The properties are now loaded");
		    
		    return propEntity;
		    
		} catch (IOException e) {
			Log.e(logTag, "Failed to load the properties file");		    
			Log.e(logTag, e.toString());
		}		
		
		return null;
	}
	
	public PropertyEntity getPropertyEntity(String name) {
		// Read Entity from Assets
		return this.readPropertyFileFromAssets(name);		
	}	
}
