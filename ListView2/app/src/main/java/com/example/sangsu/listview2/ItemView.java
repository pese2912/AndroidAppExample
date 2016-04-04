package com.example.sangsu.listview2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Duedapi on 2016-04-05.
 */
public class ItemView extends FrameLayout {
    public ItemView(Context context) {
        super(context);
        init();
    }
    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    ImageView iconView;
    TextView nameView, descView, likeView;
    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.activity_item_layout, this);
        iconView = (ImageView)findViewById(R.id.image_icon);
        nameView = (TextView)findViewById(R.id.text_name);
        descView = (TextView)findViewById(R.id.text_desc);
        likeView = (TextView)findViewById(R.id.text_like);
    }
    ItemData mData;
    public void setItemData(ItemData data) {
        mData = data;
        iconView.setImageResource(data.imageId);
        nameView.setText(data.name);
        descView.setText(data.desc);
        likeView.setText("like : " + data.likeCount);
    }
}


