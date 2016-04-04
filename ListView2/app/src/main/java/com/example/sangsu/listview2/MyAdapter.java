package com.example.sangsu.listview2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duedapi on 2016-04-05.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<ItemData> items = new ArrayList<ItemData>();
    Context mContext;
    public MyAdapter(Context context) {
        mContext = context;
    }
    public void add(ItemData item) {
        items.add(item);
        notifyDataSetChanged();
    }
    public void addAll(List<ItemData> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemView v = new ItemView(mContext);
        v.setItemData(items.get(position));
        return v;
    }

}
