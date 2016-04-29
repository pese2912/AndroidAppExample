package com.example.tacademy.samplemediastore;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;

/**
 * Created by dongja94 on 2016-04-29.
 */
public class ItemView extends FrameLayout implements Checkable {
    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_item, this);
    }

    boolean isChecked = false;

    private void drawCheck() {
        if (isChecked) {
            setBackgroundColor(Color.GREEN);
        } else {
            setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}