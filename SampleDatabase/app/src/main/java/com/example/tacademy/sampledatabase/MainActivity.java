package com.example.tacademy.sampledatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText keywordView;
    ArrayAdapter<Person> mAdapter;

    String mKeyword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        keywordView = (EditText)findViewById(R.id.editText);
        mAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyword = keywordView.getText().toString();
                updateList();
            }
        });

        btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataAddActivity.class));
            }
        });
        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
        List<Person> list = DataManager.getInstance().search(mKeyword);
        mAdapter.clear();
        for (Person p : list) {
            mAdapter.add(p);
        }
    }
}