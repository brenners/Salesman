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
package com.reply.salesmen.control;

/**
 * @author s.brenner
 *
 */
public class ConstantManager {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	public static final String HTTP_METHOD_PUT = "PUT";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
	  
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_XML = "application/xml";
	
	public static final String METADATA = "$metadata/?sap-client=498";
	public static final String SEPARATOR = "/";
	
	public static final String CLIENT_PROPERTIES = "client.properties";	
	
	public static final String CLASSNAME_SALESORDERITEM = "com.reply.salesmen.model.elements.SalesOrderItem";
	public static final String CLASSNAME_SALESORDER = "com.reply.salesmen.model.elements.SalesOrder";
	
	public static final String SETNAME_SALESORDERITEM = "SalesOrderItemSet";
	public static final String SETNAME_SALESORDER = "SalesOrderSet";
	
	public static final String CLASSNAME_MAINACTIVITY = "class com.reply.salesmen.MainActivity";
	
}
