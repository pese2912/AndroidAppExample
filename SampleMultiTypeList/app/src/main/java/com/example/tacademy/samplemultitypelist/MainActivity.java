package com.example.tacademy.samplemultitypelist;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    RadioGroup typeView;
    ListView listView;
    ChatAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText)findViewById(R.id.edit_input);
        typeView = (RadioGroup)findViewById(R.id.group_type);
        listView = (ListView)findViewById(R.id.listView);
        mAdapter = new ChatAdapter();
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputView.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    switch (typeView.getCheckedRadioButtonId()) {
                        case R.id.radio_s :
                            Send send = new Send();
                            send.message = message;
                            send.icon = ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_launcher);
                            mAdapter.add(send);
                            break;

                        case R.id.radio_r :

                            Receive receive = new Receive();
                            receive.message = message;
                            receive.icon = ContextCompat.getDrawable(MainActivity.this, R.mipmap.ic_launcher);
                            mAdapter.add(receive);

                            break;
                        case R.id.radio_d :
                            Date date = new Date();
                            date.message = message;
                            mAdapter.add(date);
                            break;
                    }
                    inputView.setText("");
                }
            }
        });
    }
}
