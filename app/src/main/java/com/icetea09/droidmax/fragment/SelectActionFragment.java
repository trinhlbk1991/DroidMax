package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.BluetoothAction.BlueToothAction;
import com.icetea09.droidmax.actions.ChangeWifiAction.WifiAction;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.actions.PhoneModeActions.PhoneModeActions;
import com.icetea09.droidmax.actions.PushNotificationAction.NotificationAction;
import com.icetea09.droidmax.adapters.ActionExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectActionFragment extends Fragment {

    public static Fragment newInstance(){
        return new SelectActionFragment();
    }

    public SelectActionFragment() {
        // Required empty public constructor
    }

    View mRootView;
    private Toolbar mToolbar;
    ExpandableListView expandableLvAction;
    ActionExpandableListAdapter actionExpandableListAdapter;

    String [] categoryResource;
    List<String> mCategories;
    HashMap<String, List<IAction>> mActionMap;
    List<IAction> iWifiActions, iBluetoothActions, iLocationActions, iPhoneModeActions, iNotiActions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_select_action, container, false);
        expandableLvAction = (ExpandableListView)mRootView.findViewById(R.id.expandListviewAction);
        initViewToolbar(mRootView);

        mCategories = new ArrayList<String>();
        categoryResource = getResources().getStringArray(R.array.array_category_action);
        for (int  i = 0; i < categoryResource.length; i++){
            mCategories.add(categoryResource[i]);
        }

        mActionMap = new HashMap<String, List<IAction>>();

        //Bluetooth Actions
        iBluetoothActions = getiBluetoothActions();
        mActionMap.put(mCategories.get(0), iBluetoothActions);

        //Wifi Actions
        iWifiActions = getiWifiActions();
        mActionMap.put(mCategories.get(1), iWifiActions);

        //GPS Action TEMP
        mActionMap.put(mCategories.get(2), iWifiActions);

        //Phone Mode
        iPhoneModeActions = getiPhoneModeActions();
        mActionMap.put(mCategories.get(3), iPhoneModeActions);

        //Noti Action
        iNotiActions = getiPushNotiActions();
        mActionMap.put(mCategories.get(4), iNotiActions);

        actionExpandableListAdapter = new ActionExpandableListAdapter(getActivity(), mCategories, mActionMap);
        expandableLvAction.setAdapter(actionExpandableListAdapter);

        return mRootView;
    }

    private List<IAction> getiPushNotiActions(){
        List<IAction> iActions = new ArrayList<IAction>();
        NotificationAction notificationAction = new NotificationAction("Noti Title", "Noti Message");
        iActions.add(notificationAction);
        return iActions;
    }

    private List<IAction> getiPhoneModeActions(){
        List<IAction> iActions = new ArrayList<IAction>();
        PhoneModeActions phoneModeActionsNormal = new PhoneModeActions(PhoneModeActions.RINGER_MODE_NORMAL + "");
        PhoneModeActions phoneModeActionsVibrate = new PhoneModeActions(PhoneModeActions.RINGER_MODE_VIBRATE + "");
        PhoneModeActions phoneModeActionsSilent = new PhoneModeActions(PhoneModeActions.RINGER_MODE_SILENT + "");
        iActions.add(phoneModeActionsNormal);
        iActions.add(phoneModeActionsVibrate);
        iActions.add(phoneModeActionsSilent);
        return iActions;
    }

    private List<IAction> getiWifiActions(){
        List<IAction> iActions = new ArrayList<IAction>();
        WifiAction wifiActionOn = new WifiAction("true");
        WifiAction wifiActionOff = new WifiAction("false");
        iActions.add(wifiActionOn);
        iActions.add(wifiActionOff);
        return iActions;
    }

    private List<IAction> getiBluetoothActions(){
        List<IAction> iActions = new ArrayList<IAction>();
        BlueToothAction blueToothActionOn = new BlueToothAction("true");
        BlueToothAction blueToothActionOff = new BlueToothAction("false");
        iActions.add(blueToothActionOn);
        iActions.add(blueToothActionOff);
        return iActions;
    }

    private void initViewToolbar(View rootView){
        mToolbar = (Toolbar)rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        if (activity != null) {
            activity.setSupportActionBar(mToolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
                actionBar.setTitle(R.string.title_add_new_action);
            }
        }
    }

}
