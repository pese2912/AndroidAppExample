package com.example.tacademy.sampledesignsupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class AppBarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        recyclerView = (RecyclerView)findViewById(R.id.rv_list);
        mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();

    }
    public void initData(){
        for(int i =0; i<40; i++){
            mAdapter.add("item "+i);
        }
    }
}
