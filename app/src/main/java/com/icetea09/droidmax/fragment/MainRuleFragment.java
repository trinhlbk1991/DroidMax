package com.icetea09.droidmax.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.adapters.MainRuleViewPagerAdapter;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import java.util.logging.Handler;

import de.greenrobot.event.EventBus;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class MainRuleFragment extends Fragment{

    public static Fragment  newInstance() {
        return new MainRuleFragment();
    }

    View mRootView;
    ViewPager mViewPagerMainRule;
    MainRuleViewPagerAdapter mainRuleAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main_rule, container, false);
        mViewPagerMainRule = (ViewPager) mRootView.findViewById(R.id.viewPagerMainRule);

        mainRuleAdapter = new MainRuleViewPagerAdapter(getFragmentManager());
        mViewPagerMainRule.setAdapter(mainRuleAdapter);

        EventBus.getDefault().register(this);
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(AddRuleEvent event) {
        //Delay
        mViewPagerMainRule.setCurrentItem(0);
    };
}
