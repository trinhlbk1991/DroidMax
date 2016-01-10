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
import com.icetea09.droidmax.adapters.RuleSuggestRecyclerViewAdapter;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.rules.battery.BatteryRule;
import com.icetea09.droidmax.rules.battery.ChargerPluggedRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;
import com.icetea09.droidmax.rules.location.ArriveSpecificLocation;
import com.icetea09.droidmax.rules.location.LocationRule;
import com.icetea09.droidmax.rules.weatherforecast.TodayWeatherForecastRule;
import com.icetea09.droidmax.rules.weatherforecast.WeatherForecastRule;

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

        recyclerViewRules = (RecyclerView) rootView.findViewById(R.id.recyclerViewRulesSuggest);

        rules = new ArrayList<>();
        iActions = new ArrayList<>();
        iRules = new ArrayList<>();

        arriveSpecificLocation = new ArriveSpecificLocation("Home", "106.663345", "10.779646");
        iRules.add(arriveSpecificLocation);

        wifiAction = new WifiAction("true");
        iActions.add(wifiAction);

        arriveSpecificLocationRule = new Rule();
        arriveSpecificLocationRule.setId("dac4c7d7-7ac1-4adb-8cfb-01da3be88801");
        arriveSpecificLocationRule.setName("Got home - Wifi on!");
        arriveSpecificLocationRule.setCategories(LocationRule.TAG);
        arriveSpecificLocationRule.setActions(iActions);
        arriveSpecificLocationRule.setConditions(iRules);

        rules.add(arriveSpecificLocationRule);

        //Low Battery -> Phone Mode action -> Vibrate
        iActions = new ArrayList<>();
        iRules = new ArrayList<>();

        lowBatteryRule = new LowBatteryRule("15");
        phoneModeActions = new PhoneModeActions(PhoneModeActions.RINGER_MODE_VIBRATE + "");

        iActions.add(phoneModeActions);
        iRules.add(lowBatteryRule);

        lowBatteryActionRule = new Rule();
        lowBatteryActionRule.setId("6e749ad7-1174-4df7-8bc0-c88d40d44e60");
        lowBatteryActionRule.setName("Save battery in silent");
        lowBatteryActionRule.setActions(iActions);
        lowBatteryActionRule.setConditions(iRules);
        lowBatteryActionRule.setCategories(BatteryRule.TAG);
        rules.add(lowBatteryActionRule);

        //Charger Plugin -> Disable Wifi, Bluetooth
        iRules = new ArrayList<>();
        chargerPluggedRule = new ChargerPluggedRule();
        iRules.add(chargerPluggedRule);

        iActions = new ArrayList<>();
        wifiAction = new WifiAction("false");
        blueToothAction = new BlueToothAction("false");
        iActions.add(wifiAction);
        iActions.add(blueToothAction);

        chargerPluginActionRule = new Rule();
        chargerPluginActionRule.setId("cf3d7b23-f118-4e5d-b653-5c471684e81f");
        chargerPluginActionRule.setName("Fast Charging");
        chargerPluginActionRule.setActions(iActions);
        chargerPluginActionRule.setConditions(iRules);
        chargerPluginActionRule.setCategories(BatteryRule.TAG);

        rules.add(chargerPluginActionRule);

        //Today Rainy Rule
        iActions = new ArrayList<>();
        iRules = new ArrayList<>();
        todayWeatherForecastRule = new TodayWeatherForecastRule("Rainy", "12:00");
        iRules.add(todayWeatherForecastRule);
        notificationAction = new NotificationAction("Bring your umbrella!", "Tomorrow will rain.");
        iActions.add(notificationAction);

        todayRainyRule = new Rule();
        todayRainyRule.setId("71960d95-2f77-473f-90fd-f76b74691896");
        todayRainyRule.setName("Bring umbrella reminder");
        todayRainyRule.setActions(iActions);
        todayRainyRule.setConditions(iRules);
        todayRainyRule.setCategories(WeatherForecastRule.TAG);

        rules.add(todayRainyRule);


        ruleSuggestRecyclerViewAdapter = new RuleSuggestRecyclerViewAdapter(getActivity(), rules);
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRules.setAdapter(ruleSuggestRecyclerViewAdapter);

        return rootView;
    }

}
