package com.icetea09.droidmax.actions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class GpsAction {//implements IAction{
    protected boolean mIsGpsEnable;
    protected Context mContext;
    protected Activity mActivity;

    public GpsAction(Context context, boolean isGpsEnable) {
        this.mContext = context;
        this.mIsGpsEnable = isGpsEnable;
    }

    //@Override
    public String convertToString() {
        return null;
    }

    //@Override
    public void perform() {
        turnGPS(mIsGpsEnable);
    }

    private void turnGPS(boolean mIsGpsEnable) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", mIsGpsEnable);
        mContext.sendBroadcast(intent);
    }

    private void turnOnGPS() {
        String provider = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) {
            //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            mContext.sendBroadcast(poke);
        }
    }
}
