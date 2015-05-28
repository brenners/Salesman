package com.reply.salesmen;

import com.reply.salesmen.control.AsyncTaskHandler;
import com.reply.salesmen.view.*;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	public static AssetManager assetManager;
	
	private AsyncTaskHandler asyncTask;
	private ProgressDialog pd;
	private AlertDialog.Builder adb;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		// define Asset Manager
		assetManager = this.getAssets();
		
		// start connecting to Server
		this.startAsyncTask();
	}
	
	public void onClick_Listener(View view) { 	
		Intent intent = null;
		
		switch(view.getId()) {
			case (R.id.B_SalesOrder):
				intent = new Intent(this, SalesOrderView.class);
		        startActivity(intent);
				break;
			case (R.id.B_Settings):
				intent = new Intent(this, SettingsView.class);
	        	startActivity(intent);
				break;
		}
	}
	
	private void startAsyncTask() {
		this.asyncTask = new AsyncTaskHandler(this);
		this.asyncTask.execute();
	}
	
	public void showToast(final String toast)
	{
	    runOnUiThread(new Runnable() {
	        public void run()
	        {
	            Toast.makeText(MainActivity.this, toast, Toast.LENGTH_LONG).show();
	        }
	    });
	}

	public void initProgressDialog(String msg) {
		// initialize Progress Dialog
		pd = new ProgressDialog(MainActivity.this);		
		// Show Progress Dialog Bar				
		pd.setMessage(msg);		       
	}
	
	public void showProgressDialog() {
		if(pd == null) {
			pd.show();	
		}
	}
	
	public void showProgressDialog(String msg) {
		// initialize Progress Dialog
		pd = new ProgressDialog(MainActivity.this);		
		// Show Progress Dialog Bar				
		pd.setMessage(msg);		       
		// Show Message
		pd.show();
	}
	
	public void closeProgessDialog() {
		if(pd != null) {
			pd.dismiss();
	        pd.cancel();	
		}		
	}

	public void initAlertDialog(String title, String msg) {
		// initialize Alert Dialog Box
		adb = new AlertDialog.Builder(this)
							.setTitle(title)
							.setMessage(msg)
							.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) { 
						            // continue with cancel
									dialog.cancel();
						        }
							})
							.setNegativeButton("Retry",  new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) { 
						        	dialog.cancel();
						            // start connecting to Server again
						        	startAsyncTask();						        	
						        }
						     })
						     .setIcon(android.R.drawable.ic_dialog_alert);
		
	
	}
	
	public void showAlertDialog() {
		if(adb != null)
			adb.show();
	}
	
	public void showAlertDialog(String title, String msg) {
		new AlertDialog.Builder(this)
		.setTitle(title)
		.setMessage(msg)
		.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
	            // continue with cancel
				dialog.cancel();
	        }
		})
		.setNegativeButton("Retry",  new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 	        	
	        	dialog.cancel();
	        	// start connecting to Server again
	    		startAsyncTask();
	        }
	     })
	     .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}
}
