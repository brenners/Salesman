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

import android.provider.SyncStateContract.Constants;
import android.util.Log;

import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.model.elements.*;

/**
 * @author s.brenner
 *
 */
public class CollectionWrapper {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/

	private final static String logTag = "ConnectionManager";
	
	// Singleton
	private static CollectionWrapper collectionWrapper;
	
	private SalesOrderSet salesOrderSet = null;
	private SalesOrderItemSet salesOrderItemSet = null;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private CollectionWrapper() {
		init();
	}
	
	public static CollectionWrapper getInstance() {
		
		if(CollectionWrapper.collectionWrapper == null) {
			CollectionWrapper.collectionWrapper = new CollectionWrapper();
		}
		
		return CollectionWrapper.collectionWrapper;
	}
	
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	private void init() {		
		if(this.salesOrderSet == null) {
			this.salesOrderSet = SalesOrderSet.getInstance();
		}
		
		if(this.salesOrderItemSet == null) {
			this.salesOrderItemSet = SalesOrderItemSet.getInstance();
		}		
	}
	
	public void addObjectToSet(Object obj, String classname) {
		
		if(obj == null)
			return;
		
		if(classname.equals(ConstantManager.SETNAME_SALESORDER)) {			
			SalesOrder so = (SalesOrder) obj;
			this.salesOrderSet.add(so);
			
		} else if(classname.equals(ConstantManager.SETNAME_SALESORDERITEM)) {
			SalesOrderItem si = (SalesOrderItem) obj;
			this.salesOrderItemSet.add(si);			
		}
		
		/*String classname = obj.getClass().toString();
		Log.i(logTag, classname);
		
		if(classname.equals("class " + ConstantManager.CLASSNAME_SALESORDER)) {
			SalesOrder so = (SalesOrder) obj;
			CollectionWrapper.salesOrderSet.add(so);
			
		} else if(classname.equals("class " + ConstantManager.CLASSNAME_SALESORDERITEM)) {
			SalesOrderItem si = (SalesOrderItem) obj;
			CollectionWrapper.salesOrderItemSet.add(si);			
		}*/
		
	}
	
	public SalesOrderSet getSalesOrderSet() {
		return this.salesOrderSet;
	}
	
	public SalesOrderItemSet getSalesOrderItemSet() {
		return this.salesOrderItemSet;
	}
	
}
