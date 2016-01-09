package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.R;


public class AddNewRuleFragment extends Fragment {

    public static Fragment newInstance() {
        return new AddNewRuleFragment();
    }


    public AddNewRuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_rule, container, false);
    }

}
