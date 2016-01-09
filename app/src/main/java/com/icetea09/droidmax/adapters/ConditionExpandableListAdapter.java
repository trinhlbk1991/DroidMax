package com.icetea09.droidmax.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.icetea09.droidmax.R;
import com.icetea09.droidmax.rules.IRule;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nam Nguyen on 09-Jan-16.
 */
public class ConditionExpandableListAdapter extends BaseExpandableListAdapter {
    Context _context;
    HashMap<String, List<IRule>> _listDataChild;
    List<String> _listDataHeader;
    public ConditionExpandableListAdapter(Context context, List<String> headerList, HashMap<String, List<IRule>> conditionMap){
        this._context = context;
        this._listDataHeader = headerList;
        this._listDataChild = conditionMap;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        IRule iRule = (IRule)getChild(groupPosition, childPosition);
        final String childText = iRule.getRuleDescription();

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.item_condition_child, null);
        }

        TextView txtListChild = (TextView)convertView.findViewById(R.id.tvConditionChild);
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.item_condition_group, null);
            }

            TextView tvConditionHeader = (TextView) convertView.findViewById(R.id.tvConditionHeader);
            tvConditionHeader.setText(headerTitle);

            return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
