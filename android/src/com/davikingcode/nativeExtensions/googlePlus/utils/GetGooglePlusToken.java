package com.davikingcode.nativeExtensions.googlePlus.utils;

import java.io.IOException;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.davikingcode.nativeExtensions.googlePlus.GooglePlusExtension;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

public class GetGooglePlusToken extends AsyncTask<Void, Void, String> {
	
	//http://stackoverflow.com/questions/18096934/get-access-token-from-google-plus-android

	Context context;
    private GoogleApiClient mGoogleApiClient;
    private String TAG  = this.getClass().getSimpleName();

    public GetGooglePlusToken(Context context, GoogleApiClient mGoogleApiClient) {
        this.context = context;
        this.mGoogleApiClient = mGoogleApiClient;
    }

    @Override
    protected String doInBackground(Void... params) {
        String accessToken1 = null;
        try {

            String  accountname  =   Plus.AccountApi.getAccountName(mGoogleApiClient);
            String scope = "oauth2:" + Scopes.PLUS_LOGIN;// + " " + "https://www.googleapis.com/auth/userinfo.email" + " https://www.googleapis.com/auth/plus.profile.agerange.read";
            accessToken1 = GoogleAuthUtil.getToken(context, accountname, scope);
            
            return accessToken1;

        } catch (IOException transientEx) {
            // network or server error, the call is expected to succeed if you try again later.
            // Don't attempt to call again immediately - the request is likely to
            // fail, you'll hit quotas or back-off.
            //TODO: HANDLE
            Log.e(TAG, "transientEx");
            transientEx.printStackTrace();
            accessToken1 = null;

        } catch (UserRecoverableAuthException e) {
            // Recover
            Log.e(TAG, "UserRecoverableAuthException");
            e.printStackTrace();
            accessToken1 = null;

        } catch (GoogleAuthException authEx) {
            // Failure. The call is not expected to ever succeed so it should not be
            // retried.
            Log.e(TAG, "GoogleAuthException");
            authEx.printStackTrace();
            accessToken1 = null;
        } catch (Exception e) {
            Log.e(TAG, "RuntimeException");
            e.printStackTrace();
            accessToken1 = null;
            throw new RuntimeException(e);
        }
        Log.wtf(TAG, "Code should not go here");
        accessToken1 = null;
        return accessToken1;
    }

    @Override
    protected void onPostExecute(String response) {
    	
    	GooglePlusExtension.context.dispatchStatusEventAsync("TOKEN", response);
    }
}
