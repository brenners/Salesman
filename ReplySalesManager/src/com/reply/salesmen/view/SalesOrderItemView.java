package com.reply.salesmen.view;

import java.util.ArrayList;

import com.reply.salesmen.R;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderItemSet;
import com.reply.salesmen.model.elements.SalesOrder;
import com.reply.salesmen.model.elements.SalesOrderItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SalesOrderItemView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_order_item_view);
		
		init();
		displayItem();
	}
	
	private void init() {
		// Currently nothing to initialize
	}
	
	private void displayItem() {
		// Get current Object
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		SalesOrder so = colWrap.getSalesOrderSet().getCurrent();
		
		if(so == null)
			return;
		
		Object o = so.getDependentItems();
		if( o == null)
			return;
			
		SalesOrderItemSet si_set = new SalesOrderItemSet((ArrayList<SalesOrderItem>) o); 
		
		if(si_set == null)
			return;
		
		setObject2View(si_set.getFirst(), false);
	}
	
	private void setObject2View(SalesOrderItem si, boolean editable) {
		
		TextView t_objectId = (TextView) findViewById(R.id.TV_SI_ObjectID);
		t_objectId.setText(""+si.getObjectId());
	
		TextView t_NumberInt = (TextView) findViewById(R.id.TV_NumberInt);
		t_NumberInt.setText(""+si.getNumberInt());
	}
}
