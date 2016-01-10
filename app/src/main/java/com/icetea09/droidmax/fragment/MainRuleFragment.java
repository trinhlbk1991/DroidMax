package com.icetea09.droidmax.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.adapters.MainRuleViewPagerAdapter;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class MainRuleFragment extends Fragment {

    public static Fragment newInstance() {
        return new MainRuleFragment();
    }

    View mRootView;
    ViewPager mViewPagerMainRule;
    MainRuleViewPagerAdapter mainRuleAdapter;
    private Toolbar mToolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main_rule, container, false);

        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        if (activity != null) {
            activity.setSupportActionBar(mToolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("DROIDMAX");
            }
        }

        mViewPagerMainRule = (ViewPager) mRootView.findViewById(R.id.viewPagerMainRule);
        mainRuleAdapter = new MainRuleViewPagerAdapter(getFragmentManager(), getActivity());
        mViewPagerMainRule.setAdapter(mainRuleAdapter);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void onEvent(AddRuleEvent event) {
        //Delay
        mViewPagerMainRule.setCurrentItem(0);
    }
}
