package com.example.tacademy.sampledesignsupport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChildFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAME = "param1";

    // TODO: Rename and change types of parameters
    private String mName;


    public ChildFragment() {
        // Required empty public constructor
    }

    public static ChildFragment newInstance(String name) {
        ChildFragment fragment = new ChildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_child, container, false);
        ((TextView)view.findViewById(R.id.textView)).setText(mName);
        return view;
    }

}
