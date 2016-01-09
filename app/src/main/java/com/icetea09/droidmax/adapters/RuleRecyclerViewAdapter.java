package com.icetea09.droidmax.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.database.RulesDataSource;
import com.icetea09.droidmax.model.Rule;

import java.util.List;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class RuleRecyclerViewAdapter extends RecyclerView.Adapter<RuleRecyclerViewAdapter.RuleViewHolder> {

    String TAG = RuleRecyclerViewAdapter.class.getSimpleName();

    public static class RuleViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewRule;
        ImageView ivThumbRule;
        TextView tvRuleName, tvRuleCondictionAction;
        Button btnRuleEdit, btnRuleDelete;

        RuleViewHolder(View itemView) {
            super(itemView);
            cardViewRule = (CardView) itemView.findViewById(R.id.cardViewRule);
            ivThumbRule = (ImageView) itemView.findViewById(R.id.ivThumbRule);
            tvRuleName = (TextView) itemView.findViewById(R.id.tvRuleName);
            tvRuleCondictionAction = (TextView) itemView.findViewById(R.id.tvRuleCondictionAction);
            btnRuleEdit = (Button) itemView.findViewById(R.id.btnRuleEdit);
            btnRuleDelete = (Button) itemView.findViewById(R.id.btnRuleDel);
        }
    }

    Context mContext;
    List<Rule> mRules;
    RulesDataSource mRulesDs;

    public RuleRecyclerViewAdapter(Context context, List<Rule> rules) {
        this.mContext = context;
        this.mRules = rules;
        mRulesDs = new RulesDataSource(context);
    }

    @Override
    public RuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rule, parent, false);
        RuleViewHolder ruleViewHolder = new RuleViewHolder(view);
        return ruleViewHolder;
    }

    @Override
    public int getItemCount() {
        return mRules.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RuleViewHolder holder, final int position) {
        final Rule rule = mRules.get(position);
        holder.ivThumbRule.setImageResource(rule.getConditions().get(0).getIcon());
        holder.tvRuleName.setText(rule.getName());

        String description = "IF";
        for (int i = 0; i < rule.getConditions().size(); i++) {
            description += " " + rule.getConditions().get(i).getRuleDescription();
            if (i < rule.getConditions().size() - 1) {
                description += " and ";
            }
        }

        description += " THEN ";
        for (int i = 0; i < rule.getActions().size(); i++) {
            description += " " + rule.getActions().get(i).getActionDescription();
            if (i < rule.getActions().size() - 1) {
                description += " and ";
            }
        }

        holder.tvRuleCondictionAction.setText(description);
        holder.btnRuleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Show edit rule form
            }
        });

        holder.btnRuleDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Pos: " + position);
                Log.d(TAG, "Size: " + mRules.size());
                deleteItem(position);
                mRulesDs.deleteRule(rule);
            }
        });
    }


    private void deleteItem(int position) {
        mRules.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mRules.size());
    }

}
