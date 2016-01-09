package com.icetea09.droidmax.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
	
	
//	public static final String DROIMAX_NAMESPACE 												= "droidmax";
//	public static final String DROIMAX_KEY_VIEWED_HOTNEWS_ID 				= "droidmax_viewed_hotnews";
//	public static final String DROIMAX_KEY_SERVER_HOTNEWS_ID 				= "droidmax_server_boot_hotnews";
//	public static final String DROIMAX_VALUE_VIEWED_HOTNEWS_INIT 		= "empty";
//	public static final String DROIMAX_VALUE_VIEWED_SEPARATION 			= ",";
//	public static final String DROIMAX_UNLOGIN_READED_HOTNEW_ID 		= "droidmax_unlogin_readed_hotnew_id";
	
	// End use for Happy Voucher
	
	public    SharedPreferences mShare;
	private  Context 					  mContext;
	private  String 					  	  mNamespace;
	private  int 					  	  		  mMode;
	
	public SharedPreferencesUtil(Context context){
		mContext = context;
	}
	
	public SharedPreferencesUtil(Context context, String prefsNameSpace, int mode){
		mContext = context;
		mNamespace = prefsNameSpace;
		mMode     		= mode;
	}
	
	public void saveItem(String itemKey, String itemValues){
		SharedPreferences.Editor editor = mContext.getSharedPreferences(mNamespace, mMode).edit();
		 editor.putString(itemKey, itemValues);
		 editor.commit();
	}
	
	public void saveItem(String itemKey, int itemValues){
		SharedPreferences.Editor editor = mContext.getSharedPreferences(mNamespace, mMode).edit();
		editor.putInt(itemKey, itemValues);
		editor.commit();
	}
	
	public void saveItem(String itemKey, boolean itemValues){
		SharedPreferences.Editor editor = mContext.getSharedPreferences(mNamespace, mMode).edit();
		editor.putBoolean(itemKey, itemValues);
		editor.commit();
	}
	
	public String getItem(String itemKey, String itemValueIfNull){
		String res = itemValueIfNull;
		SharedPreferences prefs = mContext.getSharedPreferences(mNamespace, mMode); 
		res = prefs.getString(itemKey, itemValueIfNull);
		if(res.equals(itemValueIfNull)) saveItem(itemKey, itemValueIfNull);
		return res;
	}
	
	public int getItem(String itemKey, int itemValueIfNull){
		int res = itemValueIfNull;
		SharedPreferences prefs = mContext.getSharedPreferences(mNamespace, mMode);
		res = prefs.getInt(itemKey, itemValueIfNull);
		if(res==itemValueIfNull) saveItem(itemKey, itemValueIfNull);
		return res;
	}
	
	public boolean getItem(String itemKey, boolean itemValueIfNull){
		SharedPreferences prefs = mContext.getSharedPreferences(mNamespace, mMode);
		return prefs.getBoolean(itemKey, false);
	}
	
}