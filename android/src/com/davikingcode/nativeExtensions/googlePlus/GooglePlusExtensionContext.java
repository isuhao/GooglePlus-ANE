package com.davikingcode.nativeExtensions.googlePlus;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.DebuggerHelperFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.DisconnectFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.GetAuthFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.GetUserFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.GetUserIDFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.GetUserMailFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.IsAuthenticatedFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.LoginFunction;
import com.davikingcode.nativeExtensions.googlePlus.functions.ShareFunction;

public class GooglePlusExtensionContext extends FREContext {

    @Override
    public void dispose() {
    }

    @Override
    public Map<String, FREFunction> getFunctions() {

        Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

        functionMap.put("login", new LoginFunction());
        functionMap.put("isAuthenticated", new IsAuthenticatedFunction());
        functionMap.put("debuggerHelper", new DebuggerHelperFunction());
        functionMap.put("share", new ShareFunction());
        functionMap.put("disconnect", new DisconnectFunction());
        functionMap.put("getUserMail", new GetUserMailFunction());
        functionMap.put("getUserID", new GetUserIDFunction());
        functionMap.put("getAuth", new GetAuthFunction());
        functionMap.put("getUserInfo", new GetUserFunction());

        return functionMap;
    }

    public void launchLoginActivity(Boolean extendedPermissions) {

        Intent i = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        
        i.putExtra(LoginActivity.extraPrefix + ".extendedPermissions", extendedPermissions);

        getActivity().startActivity(i);
    }
    
    public void launchShareActivity(String text, String url, String imageURL) {

        Intent i = new Intent(getActivity().getApplicationContext(), ShareActivity.class);
        
        i.putExtra(ShareActivity.extraPrefix + ".text", text);
        i.putExtra(ShareActivity.extraPrefix + ".url", url);
        i.putExtra(ShareActivity.extraPrefix + ".imageURL", imageURL);

        getActivity().startActivity(i);
    }
    
    public void launchDisconnectActivity() {

        Intent i = new Intent(getActivity().getApplicationContext(), DisconnectActivity.class);

        getActivity().startActivity(i);
    }
}