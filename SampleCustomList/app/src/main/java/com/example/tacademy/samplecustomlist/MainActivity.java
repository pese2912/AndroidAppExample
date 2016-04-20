package com.example.tacademy.samplecustomlist;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    PersonAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= (ListView)findViewById(R.id.listView);

        mAdapter = new PersonAdapter();
        listView.setAdapter(mAdapter);

        initData();
    }

    public void initData(){

        for(int i =0; i<40; i++){
            Person p = new Person();
            p.setName("item " + i);
            p.setAge(i);
            p.setPhoto(ContextCompat.getDrawable(this,R.mipmap.ic_launcher));
            mAdapter.add(p);
        }
    }
}
