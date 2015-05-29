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
import com.reply.salesmen.model.elements.SalesOrderItem;

/**
 * @author s.brenner
 *
 */
public class SalesOrderItemSet implements Model<SalesOrderItem> {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static SalesOrderItemSet salesOrderItemSet = null;	
	private static ArrayList<SalesOrderItem> salesOrderItems = null;
	private static int index = 0;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private SalesOrderItemSet() {	
		SalesOrderItemSet.salesOrderItems = new ArrayList<SalesOrderItem>();
	}
	
	public SalesOrderItemSet(ArrayList<SalesOrderItem> list) {
		salesOrderItems = list;
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
			SalesOrderItemSet.salesOrderItems.add(si);
	}
	
	public int size() {
		return SalesOrderItemSet.salesOrderItems.size();
	}

	public SalesOrderItem getFirst() {			
		if(this.size() > 0)
			return SalesOrderItemSet.salesOrderItems.get(0);
		else
			return null;
	}
	
	public SalesOrderItem getLast() {			
		if(this.size() > 0)
			return SalesOrderItemSet.salesOrderItems.get(this.size() - 1);
		else
			return null;
	}
	
	public SalesOrderItem getCurrent() {				
		return SalesOrderItemSet.salesOrderItems.get(index);
	}
	
	public SalesOrderItem getNext() {
		int next = index;
		
		if((next+1) < this.size() && SalesOrderItemSet.salesOrderItems.get(next+1) != null) {
			index += 1;
			return SalesOrderItemSet.salesOrderItems.get(index);
		}else 
			return null;
	}
	
	public SalesOrderItem getPrev() {
		int i = index;
		
		if(i > 0 && SalesOrderItemSet.salesOrderItems.get(i-1) != null) {
			index -= 1;
			return SalesOrderItemSet.salesOrderItems.get(index);
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
	public ArrayList<SalesOrderItem> query(int salesOrderObjectID) {
		
		ArrayList<SalesOrderItem> list = new ArrayList<SalesOrderItem>();
		
		// Try to find Sales Order Items 
		for (SalesOrderItem item: SalesOrderItemSet.salesOrderItems) {
			if(item.getObjectId() == salesOrderObjectID)
				list.add(item);
		}
		
		return list;
	}
	
}
