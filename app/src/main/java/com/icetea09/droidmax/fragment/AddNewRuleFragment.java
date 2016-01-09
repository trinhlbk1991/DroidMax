package com.icetea09.droidmax.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.BluetoothAction.BlueToothAction;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.component.ImageButton;
import com.icetea09.droidmax.rules.IRule;
import com.icetea09.droidmax.rules.battery.LowBatteryRule;

import java.util.ArrayList;
import java.util.List;


public class AddNewRuleFragment extends Fragment implements View.OnClickListener {

    public static Fragment newInstance() {
        return new AddNewRuleFragment();
    }


    private List<IRule> mRules;
    private List<IAction> mActions;

    private LinearLayout mContainerConditions;
    private LinearLayout mContainerActions;
    private ImageButton mBtnAddCondition;
    private ImageButton mBtnAddAction;
    private Toolbar mToolbar;

    public AddNewRuleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_new_rule, container, false);
        initView(rootView);
        mRules = new ArrayList<>();
        mActions = new ArrayList<>();
        return rootView;
    }

    private void initView(View rootView) {
        mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        AppCompatActivity activity = ((AppCompatActivity) getActivity());
        if (activity != null) {
            activity.setSupportActionBar(mToolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
                actionBar.setTitle(R.string.title_add_new_rule);
            }
        }

        mContainerConditions = (LinearLayout) rootView.findViewById(R.id.container_conditions);
        mContainerActions = (LinearLayout) rootView.findViewById(R.id.container_actions);
        mBtnAddCondition = (ImageButton) rootView.findViewById(R.id.btn_add_condition);
        mBtnAddCondition.setOnClickListener(this);
        mBtnAddAction = (ImageButton) rootView.findViewById(R.id.btn_add_action);
        mBtnAddAction.setOnClickListener(this);
    }

    int index = 0;

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddCondition.getRootView())) {
            //TODO: Goto pick condition
            addConditionToView(new LowBatteryRule(String.valueOf(index++)));
            Toast.makeText(getActivity(), "Add Condition", Toast.LENGTH_SHORT).show();
        } else if (v.equals(mBtnAddAction.getRootView())) {
            //TODO: Goto pick action
            addActionToView(new BlueToothAction("true"));
            Toast.makeText(getActivity(), "Add Action", Toast.LENGTH_SHORT).show();
        }
    }

    private void addConditionToView(final IRule rule) {
        final ImageButton imageButton = new ImageButton(getContext());
        imageButton.setText(rule.getRuleDescription());
        imageButton.setIcon(rule.getIcon());
        imageButton.setTextColor(getResources().getColor(R.color.primary_text));
        imageButton.setSecondaryButtonVisibility(View.VISIBLE);
        imageButton.setSecondaryButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainerConditions.removeView(imageButton);
                mRules.remove(rule);
            }
        });
        mContainerConditions.addView(imageButton, mRules.size());
        mRules.add(rule);
    }

    private void addActionToView(final IAction action) {
        final ImageButton imageButton = new ImageButton(getContext());
        imageButton.setText(action.getActionDescription());
        imageButton.setIcon(action.getIcon());
        imageButton.setTextColor(getResources().getColor(R.color.primary_text));
        imageButton.setSecondaryButtonVisibility(View.VISIBLE);
        imageButton.setSecondaryButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContainerActions.removeView(imageButton);
                mActions.remove(action);
            }
        });
        mContainerActions.addView(imageButton, mActions.size());
        mActions.add(action);
    }
}
