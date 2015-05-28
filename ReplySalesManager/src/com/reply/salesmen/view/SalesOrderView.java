package com.reply.salesmen.view;

import com.reply.salesmen.R;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderSet;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SalesOrderView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_order_view);
		
		getObject2View();
	}
	
	private void getObject2View() {
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		SalesOrderSet orderset = colWrap.getSalesOrderSet();
		
		com.reply.salesmen.model.elements.SalesOrder so = orderset.getFirst();		

		if(so == null)
			return;
		
		TextView t = (TextView) findViewById(R.id.objectID);		
		String val = "Object ID: " + so.getObjectId();
		t.setText(val);
	}
}
