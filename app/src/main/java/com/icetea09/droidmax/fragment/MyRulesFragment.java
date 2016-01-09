package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.adapters.RuleRecyclerViewAdapter;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class MyRulesFragment extends Fragment {

    View rootView;
    ArrayList<Rule> rules;
    RecyclerView recyclerViewRules;
    RuleRecyclerViewAdapter ruleRecyclerViewAdapter;

    public static Fragment newInstance() {
        return new MyRulesFragment();
    }

    public MyRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_rules, container, false);
        recyclerViewRules = (RecyclerView)rootView.findViewById(R.id.recyclerViewRules);

        rules = new ArrayList<Rule>();


        for (int i = 0; i < 2; i++){
            Rule rule = new Rule();
            rule.setName("Rule Name " + i);
            rules.add(rule);
        }

        ruleRecyclerViewAdapter = new RuleRecyclerViewAdapter(getActivity(), rules);
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRules.setAdapter(ruleRecyclerViewAdapter);

        EventBus.getDefault().register(this);

        return rootView;
    }

    public void onEvent(AddRuleEvent event) {
        rules.add(event.getRule());
        ruleRecyclerViewAdapter.notifyDataSetChanged();
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
