package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.davikingcode.nativeExtensions.googlePlus.GooglePlusExtension;
import com.davikingcode.nativeExtensions.googlePlus.LoginActivity;
import com.davikingcode.nativeExtensions.googlePlus.utils.GetGooglePlusToken;

public class GetAuthFunction extends BaseFunction implements FREFunction {
	
	@Override
    public FREObject call(FREContext context, FREObject[] args) {

        super.call(context, args);
        
        GetGooglePlusToken token = new GetGooglePlusToken(GooglePlusExtension.context.getActivity(), LoginActivity.mGoogleApiClient);
        token.execute();
        
        return null;
    }
}
