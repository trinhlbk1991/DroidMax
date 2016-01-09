package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.R;
import com.icetea09.droidmax.adapters.RuleRecyclerViewAdapter;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import java.util.List;

import de.greenrobot.event.EventBus;


public class MyRulesFragment extends Fragment implements View.OnClickListener {

    View rootView;
    List<Rule> mRules;
    RecyclerView recyclerViewRules;
    RuleRecyclerViewAdapter ruleRecyclerViewAdapter;
    RulesDataSource mRulesDs;

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
        recyclerViewRules = (RecyclerView) rootView.findViewById(R.id.recyclerViewRules);
        rootView.findViewById(R.id.fab_add_rule).setOnClickListener(this);

        mRulesDs = new RulesDataSource(getContext());
        mRules = mRulesDs.getAllRules();

        ruleRecyclerViewAdapter = new RuleRecyclerViewAdapter(getActivity(), mRules);
        recyclerViewRules.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRules.setAdapter(ruleRecyclerViewAdapter);

        EventBus.getDefault().register(this);

        return rootView;
    }

    public void onEvent(AddRuleEvent event) {
        mRules.add(event.getRule());
        ruleRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_rule:
                ((MainActivity) getActivity()).setFragment(AddNewRuleFragment.newInstance(), true);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
