package com.reply.salesmen.control;

import java.io.IOException;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

public class CameraManager {
	
	private static CameraManager cameraManager;
	private static Camera camera = null;
	private static String status = "";
	private Activity currActivity = null;
	
	private CameraManager(Activity act) {
		init(act);
	}
	
	public static CameraManager getInstance(Activity act) {
		if(cameraManager == null) {
			cameraManager = new CameraManager(act);
		} else if(camera == null) {
			init(act);
		}
		
		return cameraManager;	
	}
	
	private static void init(Activity act) {		
		try {
	        camera = Camera.open();
	        camera.lock();
	        status = "Locked";
	        
	    } catch(RuntimeException re) {
	        Log.e(ConstantManager.CONSOLE_TAG, "Could not initialize the Camera");
	        re.printStackTrace();
	        status = "Fail";
	    }
	}
	
	public Camera getCamera(Activity act) {
		if(camera == null) {
			init(act);
		}		
		return camera;
	}
	
	public void rotateCamera(Activity activity) {

		int factor = 90;
		
		SettingsManager sm = SettingsManager.getInstance();
		String deviceName = sm.getCurrentDevice();
		
		if(deviceName.equals("Vuzix M100")) {
			// Special handling for Vuzix
			factor = 0;
		}			   
		
		int degrees = 0;	    
		int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

		// ROTATION ==> Factor = 90, as Smartphone default orientation is 90°		
		switch (rotation) {
		case Surface.ROTATION_0: 
			degrees = 0;
	        break;
	    case Surface.ROTATION_90: 
	    	degrees = 90;
	    	break;
	    case Surface.ROTATION_180: 
	    	degrees = 180; 
	    	break;
	    case Surface.ROTATION_270: 
	    	degrees = 270;
	    	break;
		}
        int result = (factor - degrees + 360) % 360;
        camera.setDisplayOrientation(result);
	}
	
	public void stopCamera(Activity activity) {		
		if(camera != null) { 
			//stop the preview  
	        camera.stopPreview();  
	        camera.setPreviewCallback(null);
	        // unlock camera, so other applications can use camera
	        camera.unlock();
	        //release the camera  
	        camera.release();	        
	        //unbind the camera from this object  
	        camera = null;
		}
	}
	
	public void setPreviewDisplay(SurfaceHolder holder) {
		try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            
		} catch(IOException e)  {
			Log.e(ConstantManager.CONSOLE_TAG, "Could not place the Camera");
		}
	}
	
	public String getStatus() {
		return status;
	}
}
