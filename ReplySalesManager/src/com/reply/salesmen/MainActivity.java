package com.reply.salesmen;

import com.reply.salesmen.connect.ConnectionManager;
import com.reply.salesmen.control.AsyncTaskHandler;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
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
		
		// Initialize objects and connections
		init();		
		
		// start connecting to Server
		//this.startAsyncTask();
	}
	
	private void init() {		
		// define Asset Manager		
		if(assetManager == null)
			assetManager = this.getAssets();
	}
	
	private void startAsyncTask() {
		ConnectionManager conMan = ConnectionManager.getInstance();
		
		// If ConnectinManager is not correctly connected to Server, try it
		//if(conMan.getStatus() != 200)
		//{
			this.asyncTask = new AsyncTaskHandler(this);		
			this.asyncTask.execute();
		//}		
	}
	
	public void onClick_Listener(View view) { 	

		SettingsManager sm = SettingsManager.getInstance();
	
		switch(view.getId()) {
			case (R.id.B_SalesOrder):
				if(sm.isDataLoadCompleted())					
					navigate2Intent(SalesOrderView.class);		
				else {
					String title = "No Data is loaded:";
					String msg = "Do you want to load Mock Data first?: ";
					String posButtonTitle = ConstantManager.GO_ON_WITHOUT_LOADING;
					String negButtonTitle = ConstantManager.RETRY_CONNECTION;
									
					showAlertDialog(title, msg, posButtonTitle, negButtonTitle);					
				}				
				break;
			case (R.id.B_Settings):
				navigate2Intent(SettingsView.class);					
				break;
			case (R.id.B_LoadData):
				// start connecting to Server
				this.startAsyncTask();
				sm.setDataLoadCompleted(true);
			
				break;
		}
	}
	
	private void navigate2Intent(Class cl) {		
		Intent intent = new Intent(this, cl);
		startActivity(intent);	
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

	public void initAlertDialog(String title, String msg, final String posButtonTitle, final String negButtonTitle) {
		// initialize Alert Dialog Box
		adb = new AlertDialog.Builder(this)
							.setTitle(title)
							.setMessage(msg)
							.setPositiveButton(posButtonTitle, new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int which) { 
						            // continue with cancel
									dialog.cancel();									
						        }
							})
							.setNegativeButton(negButtonTitle,  new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) { 
						        	dialog.cancel();
						            // start connecting to Server again
						        	if(negButtonTitle.equals("Retry"))
						        		startAsyncTask();		        	
						        }
						     })
						     .setIcon(android.R.drawable.ic_dialog_alert);
		
	
	}
	
	public void showAlertDialog() {
		if(adb != null)
			adb.show();
	}
	
	public void showAlertDialog(String title, String msg, final String posButtonTitle, final String negButtonTitle) {
		new AlertDialog.Builder(this)
		.setTitle(title)
		.setMessage(msg)
		.setPositiveButton(posButtonTitle, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 	            
				dialog.cancel();	// continue with cancel
				
				if(posButtonTitle.equals(ConstantManager.GO_ON_WITHOUT_LOADING))
					navigate2Intent(SalesOrderView.class);				
	        }
		})
		.setNegativeButton(negButtonTitle,  new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	dialog.cancel();
	            // start connecting to Server again
	        	if(negButtonTitle.equals(ConstantManager.RETRY_CONNECTION)) {
	        		SettingsManager sm = SettingsManager.getInstance();
	        		sm.setDataLoadCompleted(true);
	        		startAsyncTask();	        		
	        	}	        		
	        }
	     })
	     .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}
}
