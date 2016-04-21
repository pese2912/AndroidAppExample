package com.example.tacademy.samplefragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public static final String KEY = "message";
    public static final int KEY_AGE = 50;
    public static FirstFragment newInstance(String message){ //액티비티에서 프레그먼트로 값전달
        FirstFragment f  = new FirstFragment();

        Bundle b = new Bundle(); //번들생성
        b.putString(KEY, message); //보낼 메시지

        f.setArguments(b); //인자설정
        return f;

    }

    String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments(); //액티비티로부터 값 받음
        if(b!= null){
            message = b.getString(KEY);
        }
    }

    public FirstFragment() {
        // Required empty public constructor
    }

    TextView messageView;
    EditText input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_first, container, false);

        messageView =(TextView)view.findViewById(R.id.textMessage);
        messageView.setText(message);
        input = (EditText)view.findViewById(R.id.editText);
        Button btn = (Button)view.findViewById(R.id.btn_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageView.setText(input.getText().toString());
            }
        });
        return  view;
    }

}
