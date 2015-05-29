package com.reply.salesmen.view;

import com.reply.salesmen.R;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderSet;
import com.reply.salesmen.model.elements.*;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SalesOrderView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_order_view);
		
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		SalesOrderSet so_set = colWrap.getSalesOrderSet();
		SalesOrder so = so_set.getFirst();
		setObject2View(so, false);
	}
	
	private void setObject2View(SalesOrder so, boolean editable) {
		
		if(so == null)
			return;
		
		// Fill all values of visible fields		
		TextView t_objID = (TextView) findViewById(R.id.ET_ObjectID);		
		t_objID.setText("Object ID: " + so.getObjectId());
		t_objID.setEnabled(editable);
		
		TextView t_desc = (TextView) findViewById(R.id.ET_Description);		
		t_desc.setText("Description: " + so.getDescription());
		
		TextView t_custID = (TextView) findViewById(R.id.ET_CustomerId);		
		t_custID.setText("Customer ID: " + so.getCustomerId());
		
		TextView t_custName = (TextView) findViewById(R.id.ET_CustomerName);		
		t_custName.setText("Customer Name: " + so.getCustomerName());
		
		TextView t_adress = (TextView) findViewById(R.id.ET_Adress);		
		t_adress.setText("Adress: " + so.getAddress());
		
		TextView t_email = (TextView) findViewById(R.id.ET_EMail);		
		t_email.setText("EMail: " + so.getMail());
		
		TextView t_phone = (TextView) findViewById(R.id.ET_Phone);		
		t_phone.setText("Phone: " + so.getPhone());
		
		TextView t_contID = (TextView) findViewById(R.id.ET_ContactID);		
		t_contID.setText("Contact ID: " + so.getContactID());
		
		TextView t_contName = (TextView) findViewById(R.id.ET_ContactName);		
		t_contName.setText("Contact Name: " + so.getContactName());
		
	}
}
