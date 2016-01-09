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
import com.icetea09.droidmax.adapters.RuleSuggestRecyclerViewAdapter;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import java.util.ArrayList;


public class SuggestionRulesFragment extends Fragment {

    View rootView;
    ArrayList<Rule> rules;
    RecyclerView recyclerViewRules;
    RuleSuggestRecyclerViewAdapter ruleSuggestRecyclerViewAdapter;

    public SuggestionRulesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_suggestion_rules, container, false);

        recyclerViewRules = (RecyclerView)rootView.findViewById(R.id.recyclerViewRulesSuggest);

        rules = new ArrayList<Rule>();

        for (int i = 0; i < 5; i++){
            Rule rule = new Rule();
            rule.setName("Rule Name " + i);
            rules.add(rule);
        }

        ruleSuggestRecyclerViewAdapter = new RuleSuggestRecyclerViewAdapter(getActivity(), rules);
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRules.setAdapter(ruleSuggestRecyclerViewAdapter);


        return rootView;
    }

}
