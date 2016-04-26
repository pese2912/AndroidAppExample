package com.example.tacademy.sampledesignsupport;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-04-26.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    List<String> items = new ArrayList<String>();

    public void add(String item)
    {
        items.add(item);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.setTitle(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
