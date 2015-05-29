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
package com.reply.salesmen.model.elements;

/**
 * @author s.brenner
 *
 */
public class SalesOrderItem {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private int objectId; 
	private double quantity;
	private int productId;
	private String description; 
	private int numberInt;

	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	public SalesOrderItem() {
		
	}
	
	public SalesOrderItem(int objectID) {
		this.setObjectId(objectID);
	}
	
	public SalesOrderItem(int objectID, double quantity, int productID, String prodDescription, int numberInt) {
		this.setObjectId(objectID);
		this.setQuantity(quantity);
		this.setProductId(productID);
		this.setDescription(prodDescription);
		this.setNumberInt(numberInt);
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	/**
	 * @return the objectId
	 */
	public int getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		try {
			int val = Integer.parseInt(objectId);
			this.objectId = val;
			
		} catch(NumberFormatException e) {
			this.objectId = 0;
			
		}		
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		try {
			double val = Double.parseDouble(quantity);
			this.quantity = val;
			
		} catch(NumberFormatException e) {
			this.quantity = 0.0;
		}
				
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return this.productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		try {
			int val = Integer.parseInt(productId);
			this.productId = val;
			
		} catch(NumberFormatException e) {
			this.productId = 0;
		}
		
	}

	/**
	 * @return the prodDescription
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param prodDescription the prodDescription to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the numberInt
	 */
	public int getNumberInt() {
		return numberInt;
	}

	/**
	 * @param numberInt the numberInt to set
	 */
	public void setNumberInt(int numberInt) {
		this.numberInt = numberInt;
	}

	/**
	 * @param numberInt the numberInt to set
	 */
	public void setNumberInt(String numberInt) {
		try {
			int val = Integer.parseInt(numberInt);
			this.numberInt = val;
			
		} catch(NumberFormatException e) {
			this.numberInt = 0;
		}
		
	}
}
