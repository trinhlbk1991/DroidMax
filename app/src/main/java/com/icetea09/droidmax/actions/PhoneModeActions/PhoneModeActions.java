package com.icetea09.droidmax.actions.PhoneModeActions;

import android.content.Context;
import android.media.AudioManager;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.model.Rule;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class PhoneModeActions implements IAction {

    public static final String TAG = PhoneModeActions.class.getName();

    protected int mPhoneModeAction;
    protected Context mContext;

    public static final int RINGER_MODE_NORMAL = AudioManager.RINGER_MODE_NORMAL;
    public static final int RINGER_MODE_SILENT = AudioManager.RINGER_MODE_SILENT;
    public static final int RINGER_MODE_VIBRATE = AudioManager.RINGER_MODE_VIBRATE;

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG)
                .append(Rule.ARGS_SEPARATOR)
                .append(mPhoneModeAction);
        return stringBuilder.toString();
    }

    @Override
    public void perform() {
        setPhoneMode(mPhoneModeAction);
    }

    public PhoneModeActions(String phoneAction) {
        this.mPhoneModeAction = Integer.valueOf(phoneAction);
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    private void setPhoneMode(int phoneMode) {
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(phoneMode);
    }

    @Override
    public int getIcon() {
        return R.drawable.ic_phone;
    }

    @Override
    public String getActionDescription() {
        String phoneMode = "";
        switch (mPhoneModeAction) {
            case RINGER_MODE_NORMAL:
                phoneMode = "Normal";
                break;
            case RINGER_MODE_SILENT:
                phoneMode = "Silent";
                break;
            case RINGER_MODE_VIBRATE:
                phoneMode = "Vibrate";
                break;
        }
        return "Change phone mode to " + phoneMode;
    }
}
