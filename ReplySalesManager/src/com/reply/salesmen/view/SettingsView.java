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
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingsView extends Activity {

	private static final String logTag = "PropertyEntity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);	
		
		// Init URL 
		SettingsManager sm = SettingsManager.getInstance();
		sm.setURL("url_test");
		
		this.initSwitchButton();
	}
	
	private void initSwitchButton() {
		// Switch URL: Initialize Switch Button
		Switch mySwitch = (Switch) findViewById(R.id.switchURL);	 
		//set the switch to ON 
		mySwitch.setChecked(false);
		//attach a listener to check for changes in state
		OnCheckedChangeListener listener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				SettingsManager sm = SettingsManager.getInstance();
				
				// Set URL for Connection
				if(isChecked) {
					sm.setURL("url_develop");
			    } else {
			    	sm.setURL("url_test");
			    }
			}
		};
		mySwitch.setOnCheckedChangeListener(listener);		
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
