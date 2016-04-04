package com.example.sangsu.listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView = (ListView)findViewById(R.id.listView1);
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_layout);
        mAdapter = new MyAdapter(this);
       listView.setAdapter(mAdapter);
        initData();
    }
    private void initData() {
        for (int i = 0; i < 20 ; i++) {
            ItemData d = new ItemData();
            d.imageId = R.mipmap.ic_launcher;
            d.name = "item " + i;
            d.desc = "desc " + i;
            d.likeCount = i;
            mAdapter.add(d);
        }
    }
}
