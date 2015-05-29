package com.reply.salesmen;

import java.util.ArrayList;
import java.util.List;

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
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	public static AssetManager assetManager;
	
	private AsyncTaskHandler asyncTask;
	private ProgressDialog pd;
	private AlertDialog.Builder adb;
	
	
	// Voice Recognition: List result in ListView
	private ListView wordsList;
	/* Declares a request code, 
	 * this is basically a checksum 
	 * that we use to confirm the 
	 * response when we call out to 
	 * the voice recognition engine, 
	 * this value could be anything you want.  */
	private static final int REQUESTCODE = 1234;
	
	
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
		
		// Test Voice Recognition
		wordsList = (ListView) findViewById(R.id.L_List);
		
		// Disable button if no recognition service is present
		Button b_voice = (Button) findViewById(R.id.B_Voice);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (activities.size() == 0)
        {
        	b_voice.setEnabled(false);
        	b_voice.setText("Voice unabled");
        }
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
			case (R.id.B_Voice):
				startVoiceRecognitionActivity();
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

	/**
     * Fire an intent to start the voice recognition activity.
     */
    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUESTCODE);
    }
    
    /**
     * Handle the results from the voice recognition activity.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK)
        {
            // Populate the wordsList with the String values the recognition engine thought it heard
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            
            /* Test Recognition: if possible navigate to Sales Order view*/
            if(matches.get(0).equals("sales order")) {
            	navigate2Intent(SalesOrderView.class);
            }
            
            wordsList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,matches));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
}
