package com.icetea09.droidmax.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.fragment.MainRuleFragment;
import com.icetea09.droidmax.fragment.MyRulesFragment;
import com.icetea09.droidmax.fragment.SuggestionRulesFragment;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class MainRuleViewPagerAdapter extends FragmentPagerAdapter{
    String[] mTabsTitle;
    public MainRuleViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTabsTitle = context.getResources().getStringArray(R.array.array_rule_tab);
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

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsTitle[position];
    }
}
