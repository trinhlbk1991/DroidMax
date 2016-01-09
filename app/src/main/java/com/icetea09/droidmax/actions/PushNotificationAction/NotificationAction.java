package com.icetea09.droidmax.actions.PushNotificationAction;

import android.content.Context;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.utils.NotificationUtil;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class NotificationAction implements IAction {

    public static final String TAG = NotificationAction.class.getName();

    private String mTitle;
    private String mMessage;
    private Context mContext;

    public NotificationAction(String title, String message) {
        this.mTitle = title;
        this.mMessage = message;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void perform() {
        NotificationUtil.showNotification(mContext, mTitle, mMessage);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mTitle)
                .append(Rule.ARGS_SEPARATOR)
                .append(mMessage);
        return stringBuilder.toString();
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_notification;
    }

    @Override
    public String getActionDescription() {
        return "Push notification to user";
    }
}
