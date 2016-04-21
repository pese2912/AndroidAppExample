package com.example.tacademy.samplefragmentbackstack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MESSAGE = "param1";


    // TODO: Rename and change types of parameters
    private String message;



    public CountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment CountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CountFragment newInstance(String param1) {
        CountFragment fragment = new CountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            message = getArguments().getString(ARG_MESSAGE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_count, container, false);
        // Inflate the layout for this fragment
        TextView textView = (TextView)view.findViewById(R.id.text_message);
        textView.setText(message);
        return view;
    }

}
