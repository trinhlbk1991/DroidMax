package com.icetea09.droidmax.actions.ChangeWifiAction;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.icetea09.droidmax.actions.IAction;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiAction implements IAction{

    Context mContext;
    boolean mIsWifiEnable;
    public WifiAction(Context context, boolean isWifiEnable){
        this.mContext = context;
        this.mIsWifiEnable = isWifiEnable;
    }

    @Override
    public void perform() {
            turnWifi(mIsWifiEnable);
    }

    @Override
    public String convertToString() {
        return null;
    }


    private void turnWifi(boolean mIsWifiEnable){
        WifiManager wifiManager = (WifiManager)mContext.getSystemService(Context.WIFI_SERVICE);
        if (mIsWifiEnable){
            wifiManager.setWifiEnabled(true);
        }
        else{
            wifiManager.setWifiEnabled(false);
        }
    }
}
