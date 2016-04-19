package com.example.tacademy.samplesimplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    ListView listView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText)findViewById(R.id.btn_edit);
        listView = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);


        listView.setAdapter(mAdapter);

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!inputView.getText().toString().isEmpty())
                mAdapter.add(inputView.getText().toString());
                listView.smoothScrollToPosition(mAdapter.getCount()-1);
            }
        });
        initData();
    }

    public void initData()
    {
        String[] array  = getResources().getStringArray(R.array.list_item);

        for(String s: array)
        {
            mAdapter.add(s);
        }
    }

}
