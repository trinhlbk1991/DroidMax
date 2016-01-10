package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.BlueToothAction;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.actions.NotificationAction;
import com.icetea09.droidmax.actions.PhoneModeActions;
import com.icetea09.droidmax.actions.WifiAction;
import com.icetea09.droidmax.adapters.RuleRecyclerViewAdapter;
import com.icetea09.droidmax.adapters.RuleSuggestRecyclerViewAdapter;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;
import com.icetea09.droidmax.rules.bluetooth.BluetoothDisableRule;
import com.icetea09.droidmax.rules.location.ArriveSpecificLocation;
import com.icetea09.droidmax.rules.network.WifiDisableRule;
import com.icetea09.droidmax.rules.weatherforecast.TodayWeatherForecastRule;

import java.util.ArrayList;
import java.util.List;


public class SuggestionRulesFragment extends Fragment {

    View rootView;
    ArrayList<Rule> rules;
    RecyclerView recyclerViewRules;
    RuleSuggestRecyclerViewAdapter ruleSuggestRecyclerViewAdapter;
    List<IAction> iActions;
    List<IRule> iRules;

    //IRules
    ArriveSpecificLocation arriveSpecificLocation;
    LowBatteryRule lowBatteryRule;
    ChargerPluggedRule chargerPluggedRule;
    TodayWeatherForecastRule todayWeatherForecastRule;
    //IActions
    WifiAction wifiAction;
    PhoneModeActions phoneModeActions;
    BlueToothAction blueToothAction;
    NotificationAction notificationAction;

    Rule arriveSpecificLocationRule, lowBatteryActionRule, chargerPluginActionRule, todayRainyRule;

    public SuggestionRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_suggestion_rules, container, false);

        recyclerViewRules = (RecyclerView)rootView.findViewById(R.id.recyclerViewRulesSuggest);

        rules = new ArrayList<Rule>();
        iActions = new ArrayList<IAction>();
        iRules = new ArrayList<IRule>();

        arriveSpecificLocation = new ArriveSpecificLocation("Home", "106.663345", "10.779646");
        iRules.add(arriveSpecificLocation);

        wifiAction = new WifiAction("true");
        iActions.add(wifiAction);

        arriveSpecificLocationRule = new Rule();
        arriveSpecificLocationRule.setId("dac4c7d7-7ac1-4adb-8cfb-01da3be88801");
        arriveSpecificLocationRule.setName("Go Home");
        arriveSpecificLocationRule.setActions(iActions);
        arriveSpecificLocationRule.setConditions(iRules);

        rules.add(arriveSpecificLocationRule);

        //Low Battery -> Phone Mode action -> Vibrate
        iActions = new ArrayList<IAction>();
        iRules = new ArrayList<IRule>();

        lowBatteryRule = new LowBatteryRule("15");
        phoneModeActions = new PhoneModeActions(PhoneModeActions.RINGER_MODE_VIBRATE + "");

        iActions.add(phoneModeActions);
        iRules.add(lowBatteryRule);

        lowBatteryActionRule = new Rule();
        lowBatteryActionRule.setId("6e749ad7-1174-4df7-8bc0-c88d40d44e60");
        lowBatteryActionRule.setName("Low Battery");
        lowBatteryActionRule.setActions(iActions);
        lowBatteryActionRule.setConditions(iRules);
        rules.add(lowBatteryActionRule);

        //Charger Plugin -> Disable Wifi, Bluetooth
        iRules = new ArrayList<IRule>();
        chargerPluggedRule = new ChargerPluggedRule();
        iRules.add(chargerPluggedRule);

        iActions = new ArrayList<IAction>();
        wifiAction = new WifiAction("false");
        blueToothAction = new BlueToothAction("false");
        iActions.add(wifiAction);
        iActions.add(blueToothAction);

        chargerPluginActionRule = new Rule();
        chargerPluginActionRule.setId("cf3d7b23-f118-4e5d-b653-5c471684e81f");
        chargerPluginActionRule.setName("Battery is Charging");
        chargerPluginActionRule.setActions(iActions);
        chargerPluginActionRule.setConditions(iRules);

        rules.add(chargerPluginActionRule);

        //Today Rainy Rule
        iActions = new ArrayList<IAction>();
        iRules = new ArrayList<IRule>();
        todayWeatherForecastRule = new TodayWeatherForecastRule("Rainy", "12:00");
        iRules.add(todayWeatherForecastRule);
        notificationAction = new NotificationAction("Bring umbrella", "Bring umbrella because it's raining");
        iActions.add(notificationAction);

        todayRainyRule = new Rule();
        todayRainyRule.setId("71960d95-2f77-473f-90fd-f76b74691896");
        todayRainyRule.setName("It will be rainy");
        todayRainyRule.setActions(iActions);
        todayRainyRule.setConditions(iRules);

        rules.add(todayRainyRule);


        ruleSuggestRecyclerViewAdapter = new RuleSuggestRecyclerViewAdapter(getActivity(), rules);
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRules.setAdapter(ruleSuggestRecyclerViewAdapter);


        return rootView;
    }

}
