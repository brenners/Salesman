package com.reply.salesmen.control.Voice;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;

public interface VoiceManagerInterface {

	/* Declares a request code, 
	 * this is basically a checksum 
	 * that we use to confirm the 
	 * response when we call out to 
	 * the voice recognition engine, 
	 * this value could be anything you want.  */
	public static final int REQUESTCODE = 1234;	
	ArrayList<String> matches = null;	
	
	public void defineActivity(Activity act);
	public void start();
	public void stop();
}
