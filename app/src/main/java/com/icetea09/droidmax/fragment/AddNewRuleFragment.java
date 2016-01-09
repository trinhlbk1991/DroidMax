package com.icetea09.droidmax.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.icetea09.droidmax.MainActivity;
import com.icetea09.droidmax.R;
import com.icetea09.droidmax.actions.IAction;
import com.icetea09.droidmax.component.ImageButton;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;
import com.icetea09.droidmax.model.event.SelectActionEvent;
import com.icetea09.droidmax.model.event.SelectConditionEvent;
import com.icetea09.droidmax.rules.IRule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.greenrobot.event.EventBus;


public class AddNewRuleFragment extends Fragment implements View.OnClickListener {

    public static Fragment newInstance() {
        return new AddNewRuleFragment();
    }


    private List<IRule> mRules;
    private List<IAction> mActions;
    private RulesDataSource mRulesDs;

    private LinearLayout mContainerConditions;
    private LinearLayout mContainerActions;
    private ImageButton mBtnAddCondition;
    private ImageButton mBtnAddAction;
    private Toolbar mToolbar;
    private EditText mEtRuleName;

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
        mRulesDs = new RulesDataSource(getContext());
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
        rootView.findViewById(R.id.btn_add_rule).setOnClickListener(this);
        mEtRuleName = (EditText) rootView.findViewById(R.id.et_rule_name);
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

    public void onEvent(SelectConditionEvent event) {
        getActivity().onBackPressed();
        addConditionToView(event.getRule());
    }

    public void onEvent(SelectActionEvent event) {
        getActivity().onBackPressed();
        addActionToView(event.getAction());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddCondition.getRootView())) {
            ((MainActivity) getActivity()).setFragment(SelectConditionFragment.newInstance(), true);

        } else if (v.equals(mBtnAddAction.getRootView())) {
            ((MainActivity) getActivity()).setFragment(SelectActionFragment.newInstance(), true);
        } else if (v.getId() == R.id.btn_add_rule) {
            addNewRule();
        }
    }

    private void addNewRule() {
        String ruleName = mEtRuleName.getText().toString().trim();
        if (TextUtils.isEmpty(ruleName)) {
            Toast.makeText(getActivity(), "Rule name must be not empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mRules.size() == 0) {
            Toast.makeText(getActivity(), "Must select at least one condition", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mActions.size() == 0) {
            Toast.makeText(getActivity(), "Must select at least one action", Toast.LENGTH_SHORT).show();
            return;
        }

        Rule rule = new Rule();
        rule.setName(ruleName);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        rule.setId(randomUUIDString);
        rule.setNumOfPerformed(0);
        rule.setConditions(mRules);
        rule.setActions(mActions);

        String strCategories = "";
        for (IRule r : mRules) {
            strCategories += r.getCategory() + Rule.ITEMS_SEPARATOR;
        }
        strCategories = strCategories.substring(0, strCategories.length() - 1);
        rule.setCategories(strCategories);

        if (mRulesDs.addNewRule(rule)) {
            Toast.makeText(getActivity(), "Added new rule successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Added new rule failed", Toast.LENGTH_SHORT).show();
        }
        AddRuleEvent.fire(rule);
        getActivity().onBackPressed();
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
