package com.example.sangsu.seletwidget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Duedapi on 2016-04-05.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<String> items = new ArrayList<String>();
    Context mContext;
    public MyAdapter(Context c)
    {
        mContext =c;
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
        TextView tv;
        if (convertView == null) {
            tv = new TextView(mContext);
            tv.setTextColor(Color.BLACK);
        } else {
            tv = (TextView) convertView;
        }
        tv.setText(items.get(position));
        return tv;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tv;
        if (convertView == null) {
            tv = new TextView(mContext);
            tv.setTextColor(Color.RED);
        } else {
            tv = (TextView) convertView;
        }
        tv.setText(items.get(position));
        return tv;
    }
    public void add(String data) {

        items.add(data);

        notifyDataSetChanged();
    }
}