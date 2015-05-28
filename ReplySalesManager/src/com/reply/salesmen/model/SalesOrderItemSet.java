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

import com.reply.salesmen.model.elements.SalesOrderItem;

/**
 * @author s.brenner
 *
 */
public class SalesOrderItemSet {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static SalesOrderItemSet salesOrderItemSet = null;	
	//private static JSONArray salesOrderItems = null;
	private static ArrayList<SalesOrderItem> salesOrderItems = null;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private SalesOrderItemSet() {	
		//SalesOrderItemSet.salesOrderItems = new JSONArray();
		SalesOrderItemSet.salesOrderItems = new ArrayList<SalesOrderItem>();
	}
	
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public static SalesOrderItemSet getInstance() {
		
		if(SalesOrderItemSet.salesOrderItemSet == null) {
			SalesOrderItemSet.salesOrderItemSet = new SalesOrderItemSet();
			
		}
		
		return SalesOrderItemSet.salesOrderItemSet;
	}

	/**
	 * @param si
	 */
	public void add(SalesOrderItem si) {
		if(si != null)
			//SalesOrderItemSet.salesOrderItems.put(si);
			SalesOrderItemSet.salesOrderItems.add(si);
	}
	
	public int size() {
		//return SalesOrderItemSet.salesOrderItems.length();
		return SalesOrderItemSet.salesOrderItems.size();
	}
	
}
