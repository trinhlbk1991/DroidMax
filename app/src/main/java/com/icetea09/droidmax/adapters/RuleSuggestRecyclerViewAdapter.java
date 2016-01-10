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
import com.icetea09.droidmax.model.Rule;
import com.icetea09.droidmax.model.event.AddRuleEvent;

import java.util.List;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class RuleSuggestRecyclerViewAdapter extends RecyclerView.Adapter<RuleSuggestRecyclerViewAdapter.RuleViewHolder>{

    String TAG = RuleSuggestRecyclerViewAdapter.class.getSimpleName();
    public static class RuleViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewRule;
        ImageView ivThumbRule;
        TextView tvRuleName, tvRuleCondictionAction;
        Button btnRuleEdit, btnRuleDelete;
        RuleViewHolder(View itemView) {
            super(itemView);

            cardViewRule = (CardView)itemView.findViewById(R.id.cardViewRule);
            ivThumbRule = (ImageView)itemView.findViewById(R.id.ivThumbRule);
            tvRuleName = (TextView)itemView.findViewById(R.id.tvRuleName);
            tvRuleCondictionAction = (TextView)itemView.findViewById(R.id.tvRuleCondictionAction);
            btnRuleEdit = (Button)itemView.findViewById(R.id.btnRuleEdit);
            btnRuleDelete = (Button)itemView.findViewById(R.id.btnRuleDel);
        }
    }
    Context mContext;
    List<Rule> mRules;

    public RuleSuggestRecyclerViewAdapter(Context context, List<Rule> rules){
        this.mContext = context;
        this.mRules = rules;
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
        holder.ivThumbRule.setImageResource(mRules.get(position).getConditions().get(0).getIcon());
        holder.tvRuleName.setText(mRules.get(position).getName());
        holder.tvRuleCondictionAction.setText(mRules.get(position).getDescription());

        holder.btnRuleEdit.setText("Add");
        holder.btnRuleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRuleEvent.fire(mRules.get(position));
                deleteItem(position);
            }
        });

        holder.btnRuleDelete.setVisibility(View.GONE);
    }

    private void deleteItem(int position){
        mRules.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mRules.size());
    }
}
