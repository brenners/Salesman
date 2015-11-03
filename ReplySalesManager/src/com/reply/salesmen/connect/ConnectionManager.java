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
package com.reply.salesmen.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.olingo.odata2.api.commons.HttpStatusCodes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.reply.salesmen.model.Parser;

import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class ConnectionManager {
	
	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "ConnectionManager";
	
	private static ConnectionManager conMan;
	public static final String HTTP_METHOD_PUT = "PUT";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
		
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
		  
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_XML = "application/xml";
		
	private static final String METADATA = "$metadata";
	private static final String SAP_CLIENT = "?sap-client=498";
	private static final String SEPARATOR = "/";
		
	private static final boolean PRINT_RAW_CONTENT = true;
	
	private static final String URL = "";
	private static final String SALESORDERSET = "SalesOrderSet";
	private static final String SALESORDERITEMSET = "SalesOrderItemSet";
	
	// give it 15 seconds to respond
	private static final int TIMEOUT2RESPOND = 15*1000;
	
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	private static final String PROXY_HOST = "proxyhost";
	private static final String PROXY_PORT = "proxyport";
	private static final String CLIENT_PROPERTIES = "client.properties";
	
	private int httpStatusCode = 0;
	
	public String errorMessage = "";
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private ConnectionManager() {
		
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	public static ConnectionManager getInstance() {	
		if(ConnectionManager.conMan == null) {
			ConnectionManager.conMan = new ConnectionManager();
		}
		
		return ConnectionManager.conMan;
	}
	
	public boolean loadSets() {				
		boolean loadFlag = false;
		
		// Get PropertyManager and load App Properties Entity
		PropertyManager propMan = PropertyManager.getInstance(); 
		PropertyEntity propEntity = propMan.getPropertyEntity("app");
		PropertyAppEntity propAppEntity = propEntity.getPropertyAppEntity();
		
		// connect to server and load each set
		loadFlag = loadSet(propAppEntity, ConnectionManager.SALESORDERSET);
		loadFlag = loadSet(propAppEntity, ConnectionManager.SALESORDERITEMSET);
		
		return loadFlag;
	}
	
	public boolean loadSet(PropertyAppEntity propAppEntity, String set) {
		
		Parser parser = new Parser();		

		try {
			InputStream content = execute(propAppEntity.getUrl() + set + SEPARATOR, APPLICATION_JSON, HTTP_METHOD_GET, propAppEntity);	
			parser.parse(content, set);
			
			return true;
			
		} catch (ConnectException e) {
			Log.e(logTag, e.toString());
			this.errorMessage = e.toString();
		} catch (IOException e) {
			Log.e(logTag, e.toString());		
			this.errorMessage = e.toString();
		} 
	
		return false;
	}
	
	private InputStream execute(String relativeUri, String contentType, String httpMethod, PropertyAppEntity propAppEntity) throws ConnectException, IOException {
		HttpURLConnection connection = initializeConnection(relativeUri, contentType, httpMethod, propAppEntity);
		// give it 15 seconds to respond
		connection.setReadTimeout(TIMEOUT2RESPOND);
		connection.connect();
		
		Log.i(logTag, "Connect");
		
		// Check Status of Response
		checkStatus(connection);   
		// Get content
		InputStream content = connection.getInputStream();

		return content;
	}
	
	private HttpURLConnection initializeConnection(String absoluteUri, String contentType, String httpMethod, PropertyAppEntity propAppEntity) throws IOException {
		URL url = new URL(absoluteUri);
		HttpURLConnection connection;

		connection = (HttpURLConnection) url.openConnection();
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");

		connection.setRequestProperty(HTTP_HEADER_ACCEPT, contentType);
		connection.setRequestMethod(httpMethod);
		
		if (HTTP_METHOD_POST.equals(httpMethod) || HTTP_METHOD_PUT.equals(httpMethod)) {
			connection.setDoOutput(true);
			connection.setRequestProperty(HTTP_HEADER_CONTENT_TYPE, contentType);
		}
		
		String authorization = "Basic ";
		//authorization += new String(Base64.encodeBase64((USERNAME + ":" + PASSWORD).getBytes()));
		
		if(propAppEntity.getAuthorization() != null) {
			// Set authorization
			authorization += new String(propAppEntity.getAuthorization());			
			connection.setRequestProperty("Authorization", authorization);
		}
		
		return connection;
	  }
	
	private HttpStatusCodes checkStatus(HttpURLConnection connection) throws IOException {
		HttpStatusCodes httpStatusCode = HttpStatusCodes.fromStatusCode(connection.getResponseCode());
  
		Log.i(logTag, "HttpStatusCode: " + httpStatusCode.getStatusCode());
  
		if (400 <= httpStatusCode.getStatusCode() && httpStatusCode.getStatusCode() <= 599) {
			String msg = "Http Connection failed with status " + httpStatusCode.getStatusCode() + " " + httpStatusCode.toString() +
	  		".\n\tRequest URL was: '" + connection.getURL().toString() + "'.";
			
			Log.e(logTag, msg);
			//throw new RuntimeException(msg);
			
		} else if(httpStatusCode.getStatusCode() == 200) {
			Log.i(logTag, "Connection successfully");
		}
		
		setStatus(httpStatusCode.getStatusCode());
		
		return httpStatusCode;
	}
	
	private void setStatus(int status) {
		httpStatusCode = status;
	}
	
	public int getStatus() {
		return httpStatusCode;
	}
	
}
