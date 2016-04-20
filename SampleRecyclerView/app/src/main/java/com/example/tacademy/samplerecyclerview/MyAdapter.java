package com.example.tacademy.samplerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-04-20.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<String> items = new ArrayList<String>();

    public void add(String title) {
        items.add(title);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_my_item, parent, false);
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