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
import com.reply.salesmen.model.elements.SalesOrderItem;

/**
 * @author s.brenner
 *
 */
public class SalesOrderSet implements Model<SalesOrder> {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "SalesOrderSet";
	
	private static SalesOrderSet salesOrderSet = null;	
	private static ArrayList<SalesOrder> salesOrders = null;
	private static int index = 0;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private SalesOrderSet() {		 		
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
		if(so != null) {
			SalesOrderSet.salesOrders.add(so);
			index = this.size() - 1;
		}			
	}
	
	public int size() {
		return SalesOrderSet.salesOrders.size();
	}
	
	public SalesOrder getFirst() {			
		if(this.size() > 0)
			return SalesOrderSet.salesOrders.get(0);
		else
			return null;
	}
	
	public SalesOrder getLast() {			
		if(this.size() > 0)
			return SalesOrderSet.salesOrders.get(this.size() - 1);
		else
			return null;
	}
	
	public SalesOrder getCurrent() {				
		return SalesOrderSet.salesOrders.get(index);
	}
	
	public SalesOrder getNext() {
		int next = index;
		
		if((next+1) < this.size() && SalesOrderSet.salesOrders.get(next+1) != null) {
			index += 1;
			return SalesOrderSet.salesOrders.get(index);
		}else 
			return null;
	}
	
	public SalesOrder getPrev() {
		int i = index;
		
		if(i > 0 && SalesOrderSet.salesOrders.get(i-1) != null) {
			index -= 1;
			return SalesOrderSet.salesOrders.get(index);
		}else
			return null;
	}
	
	public int getIndex() {
		return index;
	}

	/* (non-Javadoc)
	 * @see com.reply.salesmen.model.Model#query()
	 */
	@Override
	public ArrayList<SalesOrder> query(int salesOrderObjectID) {
		
		ArrayList<SalesOrder> list = new ArrayList<SalesOrder>();
		
		// Try to find Sales Order Items 
		for (SalesOrder order: SalesOrderSet.salesOrders) {
			if(order.getObjectId() == salesOrderObjectID)
				list.add(order);
		}
		
		return list;
	}
}
