package com.example.tacademy.samplerecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-04-20.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView titleView;
    public MyViewHolder(View itemView) {
        super(itemView);
        titleView = (TextView)itemView.findViewById(R.id.text_title);
        itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setTitle(String title) {
        titleView.setText(title);
    }


    OnItemClickListener itemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){

    }
}