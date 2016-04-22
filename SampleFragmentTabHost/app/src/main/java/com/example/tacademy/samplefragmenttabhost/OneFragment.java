package com.example.tacademy.samplefragmenttabhost;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    public OneFragment() {
        // Required empty public constructor
    }

    FragmentTabHost tabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        tabHost = (FragmentTabHost)view.findViewById(R.id.tabHost);
        tabHost.setup(getContext(), getChildFragmentManager(), android.R.id.tabcontent);
       tabHost.addTab(tabHost.newTabSpec("child1").setIndicator("CT1"), ChildTab1Fragment.class, null);
        tabHost.addTab(tabHost.newTabSpec("child2").setIndicator("CT2"), ChildTab2Fragment.class, null);
        return view;
    }

}