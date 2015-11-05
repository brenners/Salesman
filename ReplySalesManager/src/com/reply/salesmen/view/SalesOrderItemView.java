package com.reply.salesmen.view;

import java.util.ArrayList;

import com.reply.salesmen.R;
import com.reply.salesmen.control.CameraManager;
import com.reply.salesmen.control.ConstantManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.control.Voice.VoiceManager;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderItemSet;
import com.reply.salesmen.model.elements.SalesOrder;
import com.reply.salesmen.model.elements.SalesOrderItem;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class SalesOrderItemView extends Activity implements SurfaceHolder.Callback {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sales_order_item_view);
		
		init();
		displayItem();
	}
	
	private void init() {
		// Init Camera Manager
        CameraManager cameraManager = CameraManager.getInstance(this);
        
        if(cameraManager.getStatus().equals("Locked")) {

        	SettingsManager sm = SettingsManager.getInstance();
			String deviceName = sm.getCurrentDevice();
			
			if(deviceName.equals("Vuzix M100")) {
				// Special handling for Vuzix
				cameraManager.stopCamera(this);
			}
        	
        	// Calculate Screen size
        	Display display = this.getWindowManager().getDefaultDisplay();
    		Point size = new Point();
    		display.getSize(size);
    		int width = size.x;
    		int height = size.y;
    		
    		// Get Video View and define size
			VideoView videoView = (VideoView) this.findViewById(R.id.videoViewItemView);
			videoView.setLayoutParams(new FrameLayout.LayoutParams(width,height));
		    SurfaceHolder holder = videoView.getHolder();		    
		    holder.addCallback(this);
		    holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
	}
	
	private void displayItem() {
		// Get current Object
		CollectionWrapper colWrap = CollectionWrapper.getInstance();
		
		// Catch exception when no object is available and afterwards, show empty item page
		try {
			SalesOrder so = colWrap.getSalesOrderSet().getCurrent();
			
			if(so != null) {				
				Object o = so.getDependentItems();
				
				if( o != null) {
				 SalesOrderItemSet si_set = new SalesOrderItemSet((ArrayList<SalesOrderItem>) o); 
				 
				 if(si_set != null) {			
					 setObject2View(si_set.getFirst(), false);
				 }
				}
			}
		} catch(RuntimeException e) {
			Log.e(ConstantManager.CONSOLE_TAG, e.toString());			
		} catch(Exception e) {
			Log.e(ConstantManager.CONSOLE_TAG, e.toString());
		}
		
		
	}
	
	private void setObject2View(SalesOrderItem si, boolean editable) {
		
		TextView t_objectId = (TextView) findViewById(R.id.TV_SI_ObjectID);
		t_objectId.setText(""+si.getObjectId());
	
		TextView t_NumberInt = (TextView) findViewById(R.id.TV_NumberInt);
		t_NumberInt.setText(""+si.getNumberInt());
		
		TextView t_desc = (TextView) findViewById(R.id.TV_Description);
		t_desc.setText(""+si.getDescription());
		
		TextView t_prodID = (TextView) findViewById(R.id.TV_ProductID);
		t_prodID.setText(""+si.getProductId());
		
		TextView t_quant = (TextView) findViewById(R.id.TV_Quantity);
		t_quant.setText(""+si.getQuantity());		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
				
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		CameraManager cameraManager = CameraManager.getInstance(this);
        
        if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.setPreviewDisplay(holder);
        	cameraManager.rotateCamera(this);
        }		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) { 
		
	}
	
	@Override
	public void onBackPressed() {
		CameraManager cameraManager = CameraManager.getInstance(this);
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onBackPressed();
	}
	
}
