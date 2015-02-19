package com.davikingcode.nativeExtensions.googlePlus.functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class DebuggerHelperFunction extends BaseFunction implements FREFunction {

	@Override
	public FREObject call(FREContext context, FREObject[] args) {

		super.call(context, args);

		Context ctx = context.getActivity().getApplicationContext();

		Log.w("GooglePlusANE", "****");
		Log.w("GooglePlusANE", "****");
		Log.w("GooglePlusANE", "**** APP NOT CORRECTLY CONFIGURED TO USE GOOGLE PLAY GAME SERVICES");
		Log.w("GooglePlusANE", "**** This is usually caused by one of these reasons:");
		Log.w("GooglePlusANE", "**** (1) Your package name and certificate fingerprint do not match");
		Log.w("GooglePlusANE", "**** the client ID you registered in Developer Console.");
		Log.w("GooglePlusANE", "**** (2) Your App ID was incorrectly entered.");
		Log.w("GooglePlusANE", "****");
		if (ctx == null) {
			Log.w("GooglePlusANE", "*** (no Context, so can't print more debug info)");
			return null;
		}
		Log.w("GooglePlusANE", "**** To help you debug, here is the information about this app");
		Log.w("GooglePlusANE", "**** Package name : " + ctx.getPackageName());
		Log.w("GooglePlusANE", "**** Cert SHA1 fingerprint: " + getSHA1CertFingerprint(ctx));
		Log.w("GooglePlusANE", "****");
		Log.w("GooglePlusANE", "**** Check that the above information matches your setup in Developer Console");
		Log.w("GooglePlusANE", "****");
		
		try {
        	
        	return FREObject.newObject(getSHA1CertFingerprint(ctx));
        	
        } catch (FREWrongThreadException e) {
        	
        	e.printStackTrace();
        }
		
		return null;
	}

	static String getSHA1CertFingerprint(Context ctx) {

		try {
			Signature[] sigs = ctx.getPackageManager().getPackageInfo(
					ctx.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
			if (sigs.length == 0) {
				return "ERROR: NO SIGNATURE.";
			} else if (sigs.length > 1) {
				return "ERROR: MULTIPLE SIGNATURES";
			}
			byte[] digest = MessageDigest.getInstance("SHA1").digest(
					sigs[0].toByteArray());
			StringBuilder hexString = new StringBuilder();
			for (int i = 0; i < digest.length; ++i) {
				if (i > 0) {
					hexString.append(":");
				}
				byteToString(hexString, digest[i]);
			}
			return hexString.toString();
		} catch (PackageManager.NameNotFoundException ex) {
			ex.printStackTrace();
			return "(ERROR: package not found)";
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
			return "(ERROR: SHA1 algorithm not found)";
		}
	}

	static void byteToString(StringBuilder sb, byte b) {

		int unsigned_byte = b < 0 ? b + 256 : b;
		int hi = unsigned_byte / 16;
		int lo = unsigned_byte % 16;

		sb.append("0123456789ABCDEF".substring(hi, hi + 1));
		sb.append("0123456789ABCDEF".substring(lo, lo + 1));
	}
}
