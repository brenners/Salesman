package com.reply.salesmen.view;

import com.reply.salesmen.R;
import com.reply.salesmen.control.SettingsManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

public class SettingsView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}
	
	public void onClick_Listener(View view) {
		
		SettingsManager sm = SettingsManager.getInstance();
		
		switch(view.getId()) {
			case (R.id.CB_MockData):
				CheckBox cb_mockdata = (CheckBox)findViewById(R.id.CB_MockData);
				sm.setUseMockData(cb_mockdata.isChecked());
				
				break;
			case (R.id.CB_MockData_Al):
				CheckBox cb_mockdata_al = (CheckBox)findViewById(R.id.CB_MockData_Al);
				sm.setUseMockDataGenerally(cb_mockdata_al.isChecked());
				
				break;
		}	
	}
}
