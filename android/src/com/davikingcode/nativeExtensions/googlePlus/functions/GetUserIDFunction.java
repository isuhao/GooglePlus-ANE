package com.davikingcode.nativeExtensions.googlePlus.functions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.davikingcode.nativeExtensions.googlePlus.LoginActivity;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class GetUserIDFunction extends BaseFunction {
	
	@Override
    public FREObject call(FREContext context, FREObject[] args) {

        super.call(context, args);
        
        Person person = Plus.PeopleApi.getCurrentPerson(LoginActivity.mGoogleApiClient);
        
        try {
        	
        	return FREObject.newObject(person.getId());
        	
        } catch (FREWrongThreadException e) {
        	
        	e.printStackTrace();
        }

        return null;
    }
}
