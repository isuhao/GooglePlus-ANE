package com.davikingcode.nativeExtensions.googlePlus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class DisconnectActivity extends Activity implements GoogleApiClient.ConnectionCallbacks {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LoginActivity.mGoogleApiClient.registerConnectionCallbacks(this);
		
		Plus.AccountApi.revokeAccessAndDisconnect(LoginActivity.mGoogleApiClient);
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		
		LoginActivity.isConnected = false;
		
		GooglePlusExtension.context.dispatchStatusEventAsync("DISCONNECTED", "");
		
		finish();
	}

	@Override
	public void onConnectionSuspended(int cause) {

		Log.d("GooglePlusANE", "onConnectionSuspended!!!!!");
	}
}
