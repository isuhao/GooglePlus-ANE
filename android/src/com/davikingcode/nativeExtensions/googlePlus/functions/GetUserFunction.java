package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.davikingcode.nativeExtensions.googlePlus.GooglePlusExtension;
import com.davikingcode.nativeExtensions.googlePlus.LoginActivity;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class GetUserFunction extends BaseFunction implements FREFunction {
	
	@Override
    public FREObject call(FREContext context, FREObject[] args) {
        super.call(context, args);
        
        Person person = Plus.PeopleApi.getCurrentPerson(LoginActivity.mGoogleApiClient);
        
        GooglePlusExtension.context.dispatchStatusEventAsync("USER_INFO", person.toString().replace("\\", ""));

        return null;
    }
}
