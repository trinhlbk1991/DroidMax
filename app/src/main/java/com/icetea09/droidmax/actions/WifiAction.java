package com.icetea09.droidmax.actions;

import android.content.Context;
import android.net.wifi.WifiManager;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class WifiAction implements IAction {

    public static final String TAG = WifiAction.class.getName();

    protected Context mContext;
    protected boolean mIsWifiEnable;

    public WifiAction(String isWifiEnable) {
        this.mIsWifiEnable = Boolean.valueOf(isWifiEnable);
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void perform() {
        turnWifi(mIsWifiEnable);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mIsWifiEnable);
        return stringBuilder.toString();
    }


    private void turnWifi(boolean mIsWifiEnable) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        if (mIsWifiEnable) {
            wifiManager.setWifiEnabled(true);
        } else {
            wifiManager.setWifiEnabled(false);
        }
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_wifi;
    }

    @Override
    public String getActionDescription() {
        return "Turn " + (mIsWifiEnable ? "on" : "off") + " the Wifi";
    }
}
