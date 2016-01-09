package com.icetea09.droidmax.actions.PhoneModeActions;

import android.content.Context;
import android.media.AudioManager;

import com.icetea09.droidmax.actions.IAction;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class PhoneModeActions implements IAction{
    protected int mPhoneModeAction;
    protected Context mContext;

    public static final int RINGER_MODE_NORMAL = AudioManager.RINGER_MODE_NORMAL;
    public static final int RINGER_MODE_SILENT = AudioManager.RINGER_MODE_SILENT;
    public static final int RINGER_MODE_VIBRATE = AudioManager.RINGER_MODE_VIBRATE;
    @Override
    public String convertToString() {
        return null;
    }

    @Override
    public void perform() {
        setPhoneMode(mPhoneModeAction);
    }

    public PhoneModeActions(Context context, int phoneAction){
        this.mContext = context;
        this.mPhoneModeAction = phoneAction;
    }

    private void setPhoneMode(int phoneMode){
        AudioManager am = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        am.setRingerMode(phoneMode);
    }
}
