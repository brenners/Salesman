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

import java.security.Timestamp;

import com.reply.salesmen.model.CollectionWrapper;

/**
 * @author s.brenner
 *
 */
public class SalesOrder implements Elements {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private String description;
	private String address;
	private int objectId;
	private int employeeResponsibleId;
	private String employeeResponsibleName;
	private Timestamp reqDeliveryDate; // Example: ReqDeliveryDate: 23.01.15 01:00
	private int customerId;	
	private String customerName;
	private int contactId;
	private String contactName;	
	private String mail;
	private Timestamp postingDate;
	private int numItems;
	private String phone;
	
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	public SalesOrder() {
		
	}
	
	public SalesOrder(int objectId) {
		this.setObjectId(objectId);
	}
	
	public SalesOrder(int objectId, String description) {
		this.setObjectId(objectId);
		this.setDescription(description);
	}
	
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the objectID
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
	 * @return the employeeResponsibleId
	 */
	public int getEmployeeResponsibleId() {
		return employeeResponsibleId;
	}
	
	/**
	 * @param employeeResponsibleId the employeeResponsibleId to set
	 */
	public void setEmployeeResponsibleId(int employeeResponsibleId) {
		this.employeeResponsibleId = employeeResponsibleId;
	}
	
	/**
	 * @param employeeResponsibleId the employeeResponsibleId to set
	 */
	public void setEmployeeResponsibleId(String employeeResponsibleId) {
		try {
			int val = Integer.parseInt(employeeResponsibleId);
			this.employeeResponsibleId = val;
			
		} catch(NumberFormatException e) {
			this.employeeResponsibleId = 0;
			
		}

	}
	
	/**
	 * @return the employeeResponsibleName
	 */
	public String getEmployeeResponsibleName() {
		return employeeResponsibleName;
	}
	
	/**
	 * @param employeeResponsibleName the employeeResponsibleName to set
	 */
	public void setEmployeeResponsibleName(String employeeResponsibleName) {
		this.employeeResponsibleName = employeeResponsibleName;
	}
	
	/**
	 * @return the reqDeliveryDate
	 */
	public Timestamp getReqDeliveryDate() {
		return reqDeliveryDate;
	}
	
	/**
	 * @param reqDeliveryDate the reqDeliveryDate to set
	 */
	public void setReqDeliveryDate(Timestamp reqDeliveryDate) {
		this.reqDeliveryDate = reqDeliveryDate;
	}
	
	/**
	 * @param reqDeliveryDate the reqDeliveryDate to set
	 */
	public void setReqDeliveryDate(String reqDeliveryDate) {
		// To Do: korrektes konvertieren
		
		/*try {
			long val = Timestamp.parse(reqDeliveryDate);
			this.employeeResponsibleId = val;
			
		} catch(NumberFormatException e) {
			this.employeeResponsibleId = 0;
			
		}
		
		this.reqDeliveryDate = reqDeliveryDate;*/
	}
	
	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerID(int customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerId(String customerId) {
		try {
			int val = Integer.parseInt(customerId);
			this.customerId = val;
			
		} catch(NumberFormatException e) {
			this.customerId = 0;
			
		}
	}
	
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return the contactId
	 */
	public int getContactID() {
		return contactId;
	}
	
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		try {
			this.contactId = Integer.parseInt(contactId);
		} catch(NumberFormatException e) {
			this.contactId = 0;
		}
		
	}
	
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * @return the postingDate
	 */
	public Timestamp getPostingDate() {
		return postingDate;
	}
	
	/**
	 * @param postingDate the postingDate to set
	 */
	public void setPostingDate(Timestamp postingDate) {
		this.postingDate = postingDate;
	}
	
	/**
	 * @param postingDate the postingDate to set
	 */
	public void setPostingDate(String postingDate) {
		// To Do: korrektes konvertieren
		
		//this.postingDate = postingDate;
	}
	
	/**
	 * @return the numItems
	 */
	public int getNumItems() {
		return numItems;
	}
	
	/**
	 * @param numItems the numItems to set
	 */
	public void setNumItems(int numItems) {
		this.numItems = numItems;
	}
	
	/**
	 * @param numItems the numItems to set
	 */
	public void setNumItems(String numItems) {
		try {
			this.numItems = Integer.parseInt(numItems);
		}catch(NumberFormatException e) {
			this.numItems = 0;
		}

	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/* (non-Javadoc)
	 * @see com.reply.salesmen.model.elements.Elements#getDependentItem()
	 */
	@Override
	public Object getDependentItems() {
		// To Do: lade alle abhängigen Item Objects von SalesOrderItemSet
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		return colWrap.getSalesOrderItemSet().query(this.objectId);		
	}

}
