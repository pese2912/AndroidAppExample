package com.example.sangsu.listview4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Duedapi on 2016-04-05.
 */
public class MyAdapter extends BaseExpandableListAdapter {

    ArrayList<GroupItemData> items = new ArrayList<GroupItemData>();
    Context mContext;
    public MyAdapter(Context c)
    {
        mContext = c;
    }
    @Override
    public int getGroupCount() {
        return items.size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return items.get(groupPosition);
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {

            tv =
                    (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,
                            null);
            tv.setPadding(50, 0, 0, 0);
        } else {
            tv = (TextView)convertView;
        }
        tv.setText(items.get(groupPosition).groupTitle);
        return tv;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return items.get(groupPosition).children.size();
    }
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return items.get(groupPosition).children.get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv =
                    (TextView)LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1,
                            null);
        } else {
            tv = (TextView)convertView;
        }
        tv.setText(items.get(groupPosition).children.get(childPosition).title);
        return tv;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    public void add(String groupKey, ChildItemData data) {
        GroupItemData group = null;
        for (GroupItemData item : items) {
            if (item.groupTitle.equals(groupKey)) {
                group = item;
                break;
            }
        }
        if (group == null) {
            group = new GroupItemData();
            group.groupTitle = groupKey;
            items.add(group);
        }
        group.children.add(data);
        notifyDataSetChanged();
    }
    
}

class GroupItemData {
    public String groupTitle;
    public String groupDesc;
    public ArrayList<ChildItemData> children = new ArrayList<ChildItemData>();
}
class ChildItemData {
    public String title;
}
