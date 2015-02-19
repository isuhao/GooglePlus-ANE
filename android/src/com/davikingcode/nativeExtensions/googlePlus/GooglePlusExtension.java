package com.davikingcode.nativeExtensions.googlePlus;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

import java.lang.Override;

public class GooglePlusExtension implements FREExtension {

    static public GooglePlusExtensionContext context;

    @Override
    public FREContext createContext(String label) {

        return context = new GooglePlusExtensionContext();
    }

    @Override
    public void dispose() {

        context = null;
    }

    @Override
    public void initialize() {
    }
}