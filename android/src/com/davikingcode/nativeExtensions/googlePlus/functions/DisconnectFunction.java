package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.davikingcode.nativeExtensions.googlePlus.GooglePlusExtension;

public class DisconnectFunction extends BaseFunction implements FREFunction {

    @Override
    public FREObject call(FREContext context, FREObject[] args) {

        super.call(context, args);
        
        GooglePlusExtension.context.launchDisconnectActivity();

        return null;
    }
}