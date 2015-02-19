package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.davikingcode.nativeExtensions.googlePlus.LoginActivity;

public class IsAuthenticatedFunction extends BaseFunction implements FREFunction {
	
	 @Override
	    public FREObject call(FREContext context, FREObject[] args) {

	        super.call(context, args);
	        
	        try {
	        	
	        	return FREObject.newObject(LoginActivity.isConnected);
	        	
	        } catch (FREWrongThreadException e) {
	        	
	        	e.printStackTrace();
	        }

	        return null;
	    }

}
