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
package com.reply.salesmen.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.reply.salesmen.control.ConstantManager;

import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class Parser {
	
	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "Parser";
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/

	public void Parser() {
		
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public void parse(InputStream is, String setName) {
		try {			
			JSONObject json_obj = getJsonObject(is);		
		
			// Get JSON Object
			JSONObject json_obj2 = json_obj.getJSONObject("d");
			// Get Array of JSON Objects
			JSONArray result = json_obj2.getJSONArray("results");
			
			// Get Collection Wrapper
			CollectionWrapper colWrap = CollectionWrapper.getInstance();
			
			for(int i=0; i<result.length();i++)
			{
				JSONObject o = result.getJSONObject(i);
				
				// Define object and fill CollectionWrapper dynamically
				Object obj = null;
				if((obj = createEntitySet(setName, o)) != null) {
					colWrap.addObjectToSet(obj, setName);
				}
			}
			
		} catch(JSONException e) {
			Log.e("Intent", e.toString());
		}
	}
	
	private JSONObject getJsonObject(InputStream input) {
	    InputStream inputStreamObject = input;
	    
	   try {
	       BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStreamObject, "UTF-8"));
	       StringBuilder responseStrBuilder = new StringBuilder();

	       String inputStr;
	       while ((inputStr = streamReader.readLine()) != null)
	           responseStrBuilder.append(inputStr);

	       JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

	       //returns the json object
	       return jsonObject;

	   } catch (IOException e) {
	       Log.e(logTag, e.toString());
	   } catch (JSONException e) {
		   Log.e(logTag, e.toString());
	   }

	    //if something went wrong, return null
	    return null;
	}
	
	private Object createEntitySet(String setName, JSONObject o) throws JSONException {
		
		if(setName.equals("SalesOrderItemSet")) {			
			//return invokeMethod(ConstantManager.SETNAME_SALESORDERITEM, o);
			return invokeMethod(ConstantManager.CLASSNAME_SALESORDERITEM, o);
    	  
		} else if(setName.equals("SalesOrderSet")) {//cl_SalesOrderSet) {
			//return invokeMethod(ConstantManager.SETNAME_SALESORDER, o);
			return invokeMethod(ConstantManager.CLASSNAME_SALESORDER, o);
	    	  
		} else {
			  // Should not happen
			  return null;	      
		}
	}
	
	private Object invokeMethod(String classname, JSONObject o) throws JSONException {
				
		// First, create Object
		Object obj = null;
		Class<?> cls = null;
		Class[] paramString = new Class[1];	
		paramString[0] = String.class;
		
		try {
			cls = Class.forName(classname);
			obj = cls.newInstance();
			
		} catch (ClassNotFoundException e) {
			Log.e(logTag, e.toString());
		} catch (InstantiationException e) {
			Log.e(logTag, e.toString());
		} catch (IllegalAccessException e) {
			Log.e(logTag, e.toString());
		}
		
		// Fill values
		Iterator<String> it = o.keys();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = o.getString(key);
			
			// Define setter method dynamically
			String methodname = "set" + key;
			Class paramTypes[] = {};
		    Object paramsObj[] = {};
		    
		    try {	
		    	Method  method = cls.getDeclaredMethod (methodname, paramString);
		    	method.invoke (obj, value);  
		    	//Log.i(logTag, "Key: " + key + " | " + "Value: "+value);
		    	
		      } catch (IllegalAccessException e) {
		    	e.printStackTrace();
		      } catch (NoSuchMethodException e) {
					e.printStackTrace();
		      } catch (SecurityException e) {
					e.printStackTrace();
		      } catch (IllegalArgumentException e) {
					e.printStackTrace();
		      } catch (InvocationTargetException e) {
					e.printStackTrace();
		      }	 		    
		}
		
		return obj;
	}
		
}
