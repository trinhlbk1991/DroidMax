package com.icetea09.droidmax.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.icetea09.droidmax.fragment.MainRuleFragment;
import com.icetea09.droidmax.fragment.MyRulesFragment;
import com.icetea09.droidmax.fragment.SuggestionRulesFragment;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class MainRuleViewPagerAdapter extends FragmentPagerAdapter{
    public MainRuleViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MyRulesFragment();
            case 1:
                return new SuggestionRulesFragment();
        }
        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
