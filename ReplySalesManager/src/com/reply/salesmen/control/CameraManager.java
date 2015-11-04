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
	private static String status;
	
	private CameraManager() {
		init();
	}
	
	public static CameraManager getInstance() {
		if(cameraManager == null) {
			cameraManager = new CameraManager();
		} else if(camera == null) {
			init();
		}
		
		return cameraManager;	
	}
	
	private static void init() {		
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
	
	public Camera getCamera() {
		if(camera == null) {
			init();
		}		
		return camera;
	}
	
	public void rotateCamera(Activity activity) {
	    android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
	    
		// Choose Camera Orientation based on current used Device		
		SettingsManager sm = SettingsManager.getInstance();
		String deviceName = sm.getCurrentDevice();
	    
		int factor = 0;	    
		if(deviceName.equals("Vuzix M100")) {
			factor = 90;
		}
		
		int degrees = 0;	    
		int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
		
		switch (rotation) {
		case Surface.ROTATION_0: 
			// ROTATION_0 ==> Degrees = 270 and not 0, as Smartphone default orientation is 270°
			degrees = 270 + factor;
	        break;
	    case Surface.ROTATION_90: 
	    	degrees = 180 + factor; 
	    	break;
	    case Surface.ROTATION_180: 
	    	degrees = 90 + factor; 
	    	break;
	    case Surface.ROTATION_270: 
	    	degrees = 0 + factor; 
	    	break;
		}
        int result = (info.orientation - degrees + 360) % 360;
        camera.setDisplayOrientation(result);
	}
	
	public void stopCamera(Activity activity) {		
		//stop the preview  
        /*camera.stopPreview();  
        //release the camera  
        camera.release();  
        //unbind the camera from this object  
        camera = null;*/
        
        camera.stopPreview();
        camera.setPreviewCallback(null);
        camera.lock();
        camera.release();
        camera=null;
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
