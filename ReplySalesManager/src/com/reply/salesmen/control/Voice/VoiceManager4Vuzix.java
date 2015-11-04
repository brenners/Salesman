package com.reply.salesmen.control.Voice;

import java.util.ArrayList;

import com.reply.salesmen.MainActivity;
import com.reply.salesmen.view.SettingsView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

public class VoiceManager4Vuzix implements VoiceManagerInterface, RecognitionListener {
	
	/******************************************
	 * 				Declaration 			  *
	 *****************************************/
	
	private final static String logTag = "VoiceManagerSmartphone";
	
	// Vuzix 
	private SpeechRecognizer mSpeechRecognizer;
	private static VoiceManager4Vuzix voiceManager;
	private static boolean firstTime = true;
	private static Activity currentActivity = null;
	
	/******************************************
	 * 				Constructor 			  *
	 *****************************************/
	
	private VoiceManager4Vuzix() {
		init();
	}
	
	/******************************************
	 * 				Methods 			  *
	 *****************************************/
	public static VoiceManager4Vuzix getInstance() {
		if(voiceManager == null) {
			voiceManager = new VoiceManager4Vuzix();
		}
		
		return voiceManager;
	}

	private void init() {
		
	}
	
	private void initSpeechRecognizer() {
		if(currentActivity != null)  {			
			if(mSpeechRecognizer == null) {
				mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(currentActivity);
				mSpeechRecognizer.setRecognitionListener(this);
			}		    
		}
	}
	
	@Override
	public void defineActivity(Activity act) {
		currentActivity = act;
		initSpeechRecognizer();
	}

	
	public Intent getIntent() {
		
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);		
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, currentActivity.getPackageName());
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition...");  
        return intent;
	}
	
	@Override
	public void start() {
		Intent intent = getIntent();
		mSpeechRecognizer.startListening(intent);
	}
	
	@Override
	public void stop() {
		if(mSpeechRecognizer != null) {
			/*mSpeechRecognizer.stopListening();
			mSpeechRecognizer.cancel();
			mSpeechRecognizer.destroy();
			mSpeechRecognizer = null;*/
		}
	}

	@Override
	public void onReadyForSpeech(Bundle params) {
		
		if(firstTime) {
			Toast.makeText(currentActivity, "Voice Recognition Ready.", Toast.LENGTH_SHORT).show();
			firstTime = false;
		}
		Log.v("AUDIO_SERVICE","ready for speech");
	}

	@Override
	public void onBeginningOfSpeech() {
		Toast.makeText(currentActivity, "Please speak command.", Toast.LENGTH_SHORT).show();		
	}

	@Override
	public void onRmsChanged(float rmsdB) {
		Log.v("AUDIO_SERVICE","recieve : " + rmsdB + "dB");
	}

	@Override
	public void onBufferReceived(byte[] buffer) {
		Log.v("AUDIO_SERVICE","recieved");
	}

	@Override
	public void onEndOfSpeech() {
		Log.v("AUDIO_SERVICE","finished.");
	}

	@Override
	public void onError(int error) {
		Log.v("AUDIO_SERVICE","Error: " + error);
		
		switch(error) {
		case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
			// Speech Recognizer is busy
			if(mSpeechRecognizer != null) {
				//mSpeechRecognizer.cancel();
				//mSpeechRecognizer.destroy();
				//mSpeechRecognizer = null;
				//initSpeechRecognizer();
			}
			//mSpeechRecognizer.destroy();
			break;
		}
	}

	@Override
	public void onResults(Bundle results) {
		// Vuzix M100 v1.0.8 only can support 'onPartialResuts'
		Log.v("AUDIO_SERVICE","onResults called");				
	}

	@Override
	public void onPartialResults(Bundle partialResults) {
		// Vuzix M100 v1.0.8 only can support 'onPartialResuts'
		// Supported keywords:
		//   move left/right/up/down
		//   go left/right/up/down
		//   left/right/up/down
		//   next, previous, forward, back
		//   select, cancel
		//   complete, stop, exit, go home
		//   menu, volume up/down
		//   mute, call, dial, hang up, answer
		//   ignore, end, redial, call back
		//   contacts, favorites, pair, unpair
		//   sleep, shut down
		//   set clock/time
		//   cut, copy, paste, delete
		//   0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	    String[] keywords = new String[]{"back", "next", "0", "1", "2", "3", "end"}; 
		ArrayList<String> recData = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
	    String getData = new String();
	    
	    for (String s : recData) {
	        getData += s + ",";
	    }
	    
	    for(String result: recData){
	    	if(result.contains("select")) {
	    		
	    		
	    	} else if(result.contains("next") || result.contains("forward")) {
	    		
	    		
	    	} else if(result.contains("previous") || result.contains("back")) {
	    		
	    		
	    	}
	    }
	    
	    // Show filtered keyword
	    /*String result = "";
	    for (String s: recData){
	    	for (String t: keywords){
	    		if(s.equals(t)){
	    			result = t;
	    			break;
	    		}
	    	}
	    	if(!result.isEmpty()){
	    	    Toast.makeText(currentActivity, result, Toast.LENGTH_SHORT).show();
	    	    
	    	    if(result.equals("select")) {
	    	    	((MainActivity) currentActivity).navigate();	
	    	    }	    	    
	    	    
	    		break;
	    	}
	    }*/
	    // Raw Result
		Log.v("AUDIO_SERVICE","Result: " + getData);
	}

	@Override
	public void onEvent(int eventType, Bundle params) {
		Log.v("AUDIO_SERVICE","onEvent called");
	}

}
