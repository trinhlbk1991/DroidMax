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
import com.icetea09.droidmax.adapters.ConditionExpandableListAdapter;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;
import com.icetea09.droidmax.rules.battery.ChargerUnpluggedRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;
import com.icetea09.droidmax.rules.bluetooth.BluetoothDisableRule;
import com.icetea09.droidmax.rules.bluetooth.BluetoothEnableRule;
import com.icetea09.droidmax.rules.location.ArriveSpecificLocation;
import com.icetea09.droidmax.rules.location.ExitSpecificLocation;
import com.icetea09.droidmax.rules.network.WifiConnectToAnyNetwork;
import com.icetea09.droidmax.rules.network.WifiConnectToSpecificNetwork;
import com.icetea09.droidmax.rules.network.WifiDisableRule;
import com.icetea09.droidmax.rules.network.WifiEnableRule;
import com.icetea09.droidmax.rules.weatherforecast.TodayWeatherForecastRule;
import com.icetea09.droidmax.rules.weatherforecast.TomorrowWeatherForecastRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectConditionFragment extends Fragment {

    View mRootView;
    private Toolbar mToolbar;
    ExpandableListView expandableLvCondition;
    ConditionExpandableListAdapter conditionExpandableListAdapter;

    String[] categoryResource;
    List<String> mCategories;
    HashMap<String, List<IRule>> mRuleMap;
    List<IRule> iBatteryRules, iBluetoothRules, iLocationRules, iNetworkRules, iWeatherRules;

    public static Fragment newInstance() {
        return new SelectConditionFragment();
    }

    public SelectConditionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_select_condition, container, false);
        expandableLvCondition = (ExpandableListView) mRootView.findViewById(R.id.expandListviewCondition);
        initViewToolbar(mRootView);

        mCategories = new ArrayList<String>();
        categoryResource = getResources().getStringArray(R.array.array_category_condition);
        for (int i = 0; i < categoryResource.length; i++) {
            mCategories.add(categoryResource[i]);
        }

        mRuleMap = new HashMap<>();

        //Battery Rules
        iBatteryRules = createBatteryRules();
        mRuleMap.put(mCategories.get(0), iBatteryRules);

        //Bluetooth Rules
        iBluetoothRules = createBluetoothRules();
        mRuleMap.put(mCategories.get(1), iBluetoothRules);

        //Location
        iLocationRules = createLocationRules();
        mRuleMap.put(mCategories.get(2), iLocationRules);

        //Network
        iNetworkRules = createNetworkRules();
        mRuleMap.put(mCategories.get(3), iNetworkRules);

        //Weather
        iWeatherRules = createWeatherRules();
        mRuleMap.put(mCategories.get(4), iWeatherRules);

        conditionExpandableListAdapter = new ConditionExpandableListAdapter(getActivity(), mCategories, mRuleMap);
        expandableLvCondition.setAdapter(conditionExpandableListAdapter);

        return mRootView;
    }

    private List<IRule> createBatteryRules() {
        List<IRule> iBatteryRules = new ArrayList<IRule>();
        ChargerPluggedRule chargerPluggedRule = new ChargerPluggedRule();
        ChargerUnpluggedRule chargerUnpluggedRule = new ChargerUnpluggedRule();
        LowBatteryRule lowBatteryRule = new LowBatteryRule("15");

        iBatteryRules.add(chargerPluggedRule);
        iBatteryRules.add(chargerUnpluggedRule);
        iBatteryRules.add(lowBatteryRule);
        return iBatteryRules;
    }

    private List<IRule> createBluetoothRules() {
        List<IRule> iRules = new ArrayList<IRule>();
        BluetoothDisableRule bluetoothDisableRule = new BluetoothDisableRule();
        BluetoothEnableRule bluetoothEnableRule = new BluetoothEnableRule();
        iRules.add(bluetoothDisableRule);
        iRules.add(bluetoothEnableRule);
        return iRules;
    }

    private List<IRule> createLocationRules() {
        List<IRule> iRules = new ArrayList<IRule>();
        ArriveSpecificLocation arriveSpecificLocation = new ArriveSpecificLocation("Home", "106.663353", "10.779658");
        ExitSpecificLocation exitSpecificLocation = new ExitSpecificLocation("Home", "106.663353", "10.779658");
        iRules.add(arriveSpecificLocation);
        iRules.add(exitSpecificLocation);
        return iRules;
    }

    private List<IRule> createNetworkRules() {
        List<IRule> iRules = new ArrayList<IRule>();
        WifiEnableRule wifiEnableRule = new WifiEnableRule();
        WifiDisableRule wifiDisableRule = new WifiDisableRule();
        WifiConnectToAnyNetwork wifiConnectToAnyNetwork = new WifiConnectToAnyNetwork();
        WifiConnectToSpecificNetwork wifiConnectToSpecificNetwork = new WifiConnectToSpecificNetwork("East Agile F4");
        iRules.add(wifiEnableRule);
        iRules.add(wifiDisableRule);
        iRules.add(wifiConnectToAnyNetwork);
        iRules.add(wifiConnectToSpecificNetwork);
        return iRules;
    }

    private List<IRule> createWeatherRules() {
        List<IRule> iRules = new ArrayList<IRule>();
        TodayWeatherForecastRule todayWeatherForecastRule = new TodayWeatherForecastRule("Rainy", "20:00");
        TomorrowWeatherForecastRule tomorrowWeatherForecastRule = new TomorrowWeatherForecastRule("Rainy", "20:00");
        iRules.add(todayWeatherForecastRule);
        iRules.add(tomorrowWeatherForecastRule);
        return iRules;
    }


    private void initViewToolbar(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        if (activity != null) {
            activity.setSupportActionBar(mToolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
                actionBar.setTitle(R.string.title_add_new_condition);
            }
        }
    }

}
