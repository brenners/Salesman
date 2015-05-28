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

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.reply.salesmen.model.elements.SalesOrder;

/**
 * @author s.brenner
 *
 */
public class SalesOrderSet {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "SalesOrderSet";
	
	private static SalesOrderSet salesOrderSet = null;	
	//private static JSONArray salesOrders = null;
	private static ArrayList<SalesOrder> salesOrders = null;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private SalesOrderSet() {		 		
		//SalesOrderSet.salesOrders = new JSONArray();
		SalesOrderSet.salesOrders = new ArrayList<SalesOrder>();
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public static SalesOrderSet getInstance() {
		
		if(SalesOrderSet.salesOrderSet == null) {
			SalesOrderSet.salesOrderSet = new SalesOrderSet();
			
		}
		
		return SalesOrderSet.salesOrderSet;
	}
	
	/**
	 * @param so
	 */
	public void add(SalesOrder so) {
		if(so != null)
			SalesOrderSet.salesOrders.add(so);
	}
	
	public int size() {
		//return SalesOrderSet.salesOrders.length();
		return SalesOrderSet.salesOrders.size();
	}
	
	//public JSONObject getFirst() throws JSONException {
	public SalesOrder getFirst() {			
		if(this.size() > 0)
			return SalesOrderSet.salesOrders.get(0);//JSONObject(0);
		else
			return null;
	}	
}
