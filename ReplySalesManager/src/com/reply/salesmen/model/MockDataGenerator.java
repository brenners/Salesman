/*
 * Copyright 2015 (C) Reply GmbH & Co. KG
 * 
 * Created on : 28.05.2015
 * Author     : Sven Brenner
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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.model.elements.SalesOrder;

import android.util.Log;

/**
 * @author s.brenner
 *
 */
public class MockDataGenerator {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private static final String logTag = "Mock Data Generator";
	
	private int gv_entries = 0;
	
	private CollectionWrapper gv_colWrap = null;
	
	/* Sales Orders */
	private ArrayList<Integer> ga_sales_orders_id;
	private ArrayList<String> ga_sales_orders_desc;
	private ArrayList<String> ga_sales_orders_names;
	private ArrayList<String> ga_sales_orders_delivery_date;
	private ArrayList<String> ga_sales_orders_adress;
	private ArrayList<Integer> ga_sales_orders_customer_id;
	private ArrayList<Integer> ga_sales_orders_contact_id;
	private ArrayList<Integer> ga_sales_orders_employee_resp_id;
	private ArrayList<String> ga_sales_orders_mail;
	private ArrayList<String> ga_sales_orders_posting_date;
	private ArrayList<Integer> ga_sales_orders_num_items;
	private ArrayList<Integer> ga_sales_orders_phone;
	private ArrayList<String> ga_sales_orders_customer_name;
	private ArrayList<String> ga_sales_orders_contact_name;
	
	private final char[] ga_zeichen = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u' };
	private final String[] ga_names = { "Horst", "Meyer", "Bechthold", "Ossmeier", "Wieber", 
										"Lutz", "Heyes", "Gerber", "Mercer", "Meiser", 
										"Blanco", "Peters", "Deller", "Krysovic", "Petarsch",
										"Nordpol", "Voller", "Reichelt", "Wiesenhof", "Quanto",
										"Osman", "Fretocino", "Rentzo" ,"Sallmer", "Hubertus"};
	
	/* Sales Orders Items */			
	private ArrayList<Integer> ga_sales_order_items_pos_id;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	public MockDataGenerator() {
		Log.i(logTag, "Mock Data wird generiert");
		
		this.initialize();
		this.createMockData();
		this.createSalesOrdersData();
		this.createSalesOrderItemsData();
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/

	private void initialize() {
		this.gv_entries = 25;
		
		this.gv_colWrap = CollectionWrapper.getInstance();
		
		this.ga_sales_orders_id = new ArrayList<Integer>();
		this.ga_sales_order_items_pos_id = new ArrayList<Integer>();	
		this.ga_sales_orders_desc = new ArrayList<String>();
		this.ga_sales_orders_names = new ArrayList<String>();
		this.ga_sales_orders_delivery_date = new ArrayList<String>();
		this.ga_sales_orders_adress = new ArrayList<String>();
		this.ga_sales_orders_customer_id = new ArrayList<Integer>();
		this.ga_sales_orders_contact_id = new ArrayList<Integer>();
		this.ga_sales_orders_employee_resp_id = new ArrayList<Integer>();
		this.ga_sales_orders_mail = new ArrayList<String>();
		this.ga_sales_orders_posting_date = new ArrayList<String>();
		this.ga_sales_orders_num_items = new ArrayList<Integer>();
		this.ga_sales_orders_phone = new ArrayList<Integer>();
		this.ga_sales_orders_customer_name = new ArrayList<String>();
		this.ga_sales_orders_contact_name = new ArrayList<String>();
	}

	private void createMockData() {
		
		Random lv_random = new Random();
		
		/* Create Mock Data for Sales Orders */
		// Create Object ID	
		int lv_order_index = 5000000;
		
		for(int i = 0; i < this.gv_entries; i++) {
			int lv_random_index =  lv_random.nextInt((999 - 100) + 1) + 100;
			int lv_objectid =  lv_order_index + lv_random_index;
			this.ga_sales_orders_id.add(lv_objectid);			
		}
		
		// Create Description
		for (int i=0; i<this.gv_entries; i++)
		{
			String lv_desc = "";
			for (int y=0; y<this.gv_entries; y++) {
				int lv_iIndex = lv_random.nextInt((this.ga_zeichen.length - 1) + 1);
				lv_desc += this.ga_zeichen[ lv_iIndex ];
			}
			this.ga_sales_orders_desc.add(lv_desc);
		}
				
		// Create ResponsibleName ID
		lv_order_index = 10000000;	
		for(int i = 0; i < this.gv_entries; i++) {
			int lv_random_index =  lv_random.nextInt((999 - 100) + 1) + 100;
			int lv_resp_id =  lv_order_index + lv_random_index;			
			this.ga_sales_orders_employee_resp_id.add(lv_resp_id);
		}
		
		// Create ResponsibleName
		String lv_name = "";
		for(int i = 0; i < this.gv_entries; i++) {
			int lv_index = lv_random.nextInt((this.ga_names.length - 1) + 1);
			lv_name = this.ga_names[ lv_index ];
			this.ga_sales_orders_names.add(lv_name);
		}	
		
		// Create ReqDeliveryDate [Format: dd.MM.yy hh:mm]
		for(int i = 0; i < this.gv_entries; i++) {
			SimpleDateFormat lv_sdf = new SimpleDateFormat("dd.MM.yy hh:mm");
			Timestamp lv_time = new Timestamp(System.currentTimeMillis());
			String lv_time_str = lv_sdf.format(lv_time);
			this.ga_sales_orders_delivery_date.add(lv_time_str);
		}
				
		// Create Customer ID
		lv_order_index = 10000000;		
		for(int i = 0; i < this.gv_entries; i++) {
			int lv_random_index =  lv_random.nextInt((999 - 100) + 1) + 100;
			int lv_resp_id =  lv_order_index + lv_random_index;			
			this.ga_sales_orders_employee_resp_id.add(lv_resp_id);
		}
	}
	
	private void createSalesOrdersData() {
		
		for(int i = 0; i < this.gv_entries; i++) {			
			SalesOrder lv_sales_order = new SalesOrder();
			lv_sales_order.setDescription(this.ga_sales_orders_desc.get(i));
			lv_sales_order.setObjectId(this.ga_sales_orders_id.get(i));
			lv_sales_order.setEmployeeResponsibleName(this.ga_sales_orders_names.get(i));
			lv_sales_order.setReqDeliveryDate(this.ga_sales_orders_delivery_date.get(i));
			
			this.gv_colWrap.addObjectToSet(lv_sales_order, ConstantManager.SETNAME_SALESORDER);
		}		
	}
	
	private void createSalesOrderItemsData() {
		
	}
	
	public SalesOrderSet getSalesOrderSet() {
		return this.gv_colWrap.getSalesOrderSet();
	}
	
	public SalesOrderItemSet getSalesOrderItemsData() {
		return this.gv_colWrap.getSalesOrderItemSet();
	}
	
}
