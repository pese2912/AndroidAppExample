package com.example.sangsu.seletwidget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Duedapi on 2016-04-05.
 */
public class PersonAdapter extends ArrayAdapter<Person> {
    public PersonAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView view;
        if (convertView == null) {
            view = new TextView(getContext());
        } else {
            view = (TextView)convertView;
        }
        view.setText("dd");
        return view;
    }
}

class Person {
    public int imageId;
    public String name;
    @Override
    public String toString() {
        return name;
    }
}
