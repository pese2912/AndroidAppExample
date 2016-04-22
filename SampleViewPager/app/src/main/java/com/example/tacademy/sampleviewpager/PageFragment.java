package com.example.tacademy.sampleviewpager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {


    public PageFragment() {
        // Required empty public constructor
    }

    public static PageFragment newInstance(String message) {
        Bundle b = new Bundle();
        b.putString("message", message);
        PageFragment f = new PageFragment();
        f.setArguments(b);
        return f;
    }

    String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString("message");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView tv = (TextView)view.findViewById(R.id.text_message);
        tv.setText(message);
        return view;
    }

}