package com.example.tacademy.sampleviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2016-04-22.
 */
public class MyPagerAdapter extends PagerAdapter {
    List<String> items = new ArrayList<String>();

    List<View> scrapped = new ArrayList<View>();


    public void add(String item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.5f;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView view;
        if (scrapped.size() == 0) {
            view = (TextView) LayoutInflater.from(container.getContext()).inflate(android.R.layout.simple_list_item_1, container, false);
        } else {
            view = (TextView)scrapped.remove(0);
        }
        view.setText(items.get(position));
        view.setGravity(Gravity.CENTER);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View)object;
        container.removeView(view);
        scrapped.add(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}