package com.example.tacademy.samplefragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }

    TextView message;
    EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

        message =(TextView)view.findViewById(R.id.textMessage);
        input = (EditText)view.findViewById(R.id.editText);
        Button btn = (Button)view.findViewById(R.id.btn_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message.setText(input.getText().toString());
            }
        });
        return  view;
    }

}
