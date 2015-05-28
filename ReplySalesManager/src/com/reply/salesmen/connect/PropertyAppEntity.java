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
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;

/**
 * @author s.brenner
 *
 */
public class PropertyAppEntity {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static final String logTag = "PropertyAppEntity";
	private String username;
	private String url;	
	private byte[] authorization;
	
	/******************************************
	 * 				Constructor 			  
	 * 
	 *****************************************/
		
	public PropertyAppEntity() {
		
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setAuthorization(byte[] auth) {
		this.authorization = auth;
	}
	
	public byte[] getAuthorization() {
		return this.authorization;
	}
	
}
