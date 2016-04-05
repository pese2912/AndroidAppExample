package com.example.sangsu.seletwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<String> mAdapter;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner)findViewById(R.id.spinner1);
        adapter = new MyAdapter(this);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(mAdapter); //기본 어댑터
        spinner.setAdapter(adapter); // 커스터 마이징 어댑터
        initData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { // 선택

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { //취소
            }
        });

    }
    public void initData()
    {
        for(int i =1 ; i<=20; i++)
            adapter.add("item " + i);
    }


}
