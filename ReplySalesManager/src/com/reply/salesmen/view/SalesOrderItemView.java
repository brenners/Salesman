package com.reply.salesmen.view;

import java.util.ArrayList;

import com.reply.salesmen.R;
import com.reply.salesmen.control.CameraManager;
import com.reply.salesmen.control.SettingsManager;
import com.reply.salesmen.control.Voice.VoiceManager;
import com.reply.salesmen.model.CollectionWrapper;
import com.reply.salesmen.model.SalesOrderItemSet;
import com.reply.salesmen.model.elements.SalesOrder;
import com.reply.salesmen.model.elements.SalesOrderItem;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
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
        CameraManager cameraManager = CameraManager.getInstance();
        
        if(cameraManager.getStatus().equals("Locked")) {
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

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		CameraManager cameraManager = CameraManager.getInstance();
        
        if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.setPreviewDisplay(holder);
        	cameraManager.rotateCamera(this);
        }		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) { 
		
	}
	
	/*@Override
	protected void onDestroy() {
		CameraManager cameraManager = CameraManager.getInstance();
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onDestroy();
	}*/
	
	@Override
	public void onBackPressed() {
		CameraManager cameraManager = CameraManager.getInstance();
		if(cameraManager.getStatus().equals("Locked")) {
        	cameraManager.stopCamera(this);
        }		
		
		VoiceManager voice = VoiceManager.getInstance(this);				
		voice.stop();
		super.onBackPressed();
	}
	
}
