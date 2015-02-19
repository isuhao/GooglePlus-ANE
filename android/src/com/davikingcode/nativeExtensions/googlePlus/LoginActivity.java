package com.davikingcode.nativeExtensions.googlePlus;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class LoginActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
	
	static public boolean isConnected = false;
	
    static public GoogleApiClient mGoogleApiClient;
    
    static public String extraPrefix = "com.davikingcode.nativeExtensions.googlePlus.LoginActivity";
    
    private static final int REQUEST_CODE_SIGN_IN = 1;
    
    private Boolean _extended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle extras = this.getIntent().getExtras();
		
        _extended = extras.getBoolean(extraPrefix + ".extendedPermissions");
        
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Plus.API).addScope(_extended ? Plus.SCOPE_PLUS_LOGIN : Plus.SCOPE_PLUS_PROFILE).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
    }
    
    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }
    
   @Override
    public void onConnected(Bundle connectionHint) {
    	
	   GooglePlusExtension.context.dispatchStatusEventAsync("LOGIN_SUCCESSED", "");
	   
	   isConnected = true;
	   
	   finish();
    }
   
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
	   
       if (requestCode == REQUEST_CODE_SIGN_IN) {

           if (resultCode == RESULT_OK && !mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting())
               mGoogleApiClient.connect();
               
           else {

               if (resultCode == RESULT_CANCELED)
            	   finish();
               else
            	   Log.d("GooglePlusANE", "sign_in_error_status!!");
           }
           
       }
   }
    
    @Override
    public void onConnectionSuspended(int cause) {
    	
    	Log.d("GooglePlusANE", "onConnectionSuspended!!!!!");
    }
    
    @Override
    public void onConnectionFailed(ConnectionResult result) {
    	
    	Log.d("GooglePlusANE", "onConnectionFailed! code: " + Integer.toString(result.getErrorCode()) + " object: " + result.toString());
    	
    	if (result.hasResolution()) {
    		
    		try {
    			
    			result.startResolutionForResult(this, REQUEST_CODE_SIGN_IN);
    			Log.d("GooglePlusANE", "onConnectionFailed! We're in the try");
    			
    		} catch (SendIntentException e) {
    			
    			mGoogleApiClient.connect();
    			
    			Log.d("GooglePlusANE", "onConnectionFailed! Error sending the resolution Intent, connect() again.");
    		}
    	}
    }
}