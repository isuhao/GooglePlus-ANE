package com.davikingcode.nativeExtensions.googlePlus;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.plus.PlusShare;

public class ShareActivity extends Activity {
	
	static public String extraPrefix = "com.davikingcode.nativeExtensions.googlePlus.ShareActivity";
	
	static private final int REQUEST_CODE_INTERACTIVE_POST = 1;
	static private final int REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES = 2;
	
	private String _text;
	private String _url;
	private String _imageURL;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Bundle extras = this.getIntent().getExtras();
		
		_text = extras.getString(extraPrefix + ".text");
		 _url = extras.getString(extraPrefix + ".url");
		_imageURL = extras.getString(extraPrefix + ".imageURL");
		
		startActivityForResult(getInteractivePostIntent(), REQUEST_CODE_INTERACTIVE_POST);
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
        switch (requestCode) {
        
            case REQUEST_CODE_GET_GOOGLE_PLAY_SERVICES:
            	
                if (resultCode != RESULT_OK) {
                	
                	Log.d("GooglePlusANE", "Unable to sign the user in.");
                    finish();
                }
                break;

            case REQUEST_CODE_INTERACTIVE_POST:
            	
                if (resultCode != RESULT_OK)
                	GooglePlusExtension.context.dispatchStatusEventAsync("POST_NOT_SHARED", "");
                else
                	GooglePlusExtension.context.dispatchStatusEventAsync("POST_SHARED", "");
                
                finish();
                break;
        }
    }
	
	private Intent getInteractivePostIntent() {

        PlusShare.Builder builder = new PlusShare.Builder(this);
        
        builder.setText(_text + _url);
        builder.setType("text/plain");
        
        if (_imageURL != null && !_imageURL.isEmpty())
        	builder.setStream(Uri.parse(_imageURL));

        return builder.getIntent();
    }
}
