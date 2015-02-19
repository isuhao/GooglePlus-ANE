package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.davikingcode.nativeExtensions.googlePlus.GooglePlusExtension;

public class ShareFunction extends BaseFunction {
	
	@Override
    public FREObject call(FREContext context, FREObject[] args) {

        super.call(context, args);
        
        String text = getStringFromFREObject(args[0]);
        String url = getStringFromFREObject(args[1]);
        String imageURL = getStringFromFREObject(args[2]);

        GooglePlusExtension.context.launchShareActivity(text, url, imageURL);

        return null;
    }

}
