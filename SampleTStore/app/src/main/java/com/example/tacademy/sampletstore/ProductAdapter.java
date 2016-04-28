package com.example.tacademy.sampletstore;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2016-04-28.
 */
public class ProductAdapter extends BaseAdapter {
    List<Product> items = new ArrayList<>();
    public void addAll(List<Product> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(Product item) {
        items.add(item);
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
        ProductView view;
        if (convertView != null) {
            view = (ProductView)convertView;
        } else {
            view = new ProductView(parent.getContext());
        }
        view.setProduct(items.get(position));
        return view;
    }
}