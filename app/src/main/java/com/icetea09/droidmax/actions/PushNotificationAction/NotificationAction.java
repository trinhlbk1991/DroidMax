package com.icetea09.droidmax.actions.PushNotificationAction;

import android.content.Context;

import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.utils.NotificationUtil;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class NotificationAction implements IAction{

    private String mTitle;
    private String mMessage;
    private Context mContext;
    public NotificationAction(Context context, String title, String message){
        this.mTitle = title;
        this.mMessage = message;
        this.mContext = context;
    }

    @Override
    public void perform() {
        NotificationUtil.showNotification(mContext, mTitle, mMessage);
    }

    @Override
    public String convertToString() {
        return null;
    }


}
