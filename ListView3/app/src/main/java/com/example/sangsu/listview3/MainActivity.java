package com.example.sangsu.listview3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ImageView headerIconView;
    TextView headerTitleView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);
        View v = getLayoutInflater().inflate(R.layout.activity_header_layout, null);
        headerIconView = (ImageView)v.findViewById(R.id.image_icon);
        headerTitleView = (TextView)v.findViewById(R.id.text_title);
        listView.addHeaderView(v, null, false);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) listView.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "position : " + position + ", text : " + text, Toast.LENGTH_SHORT).show();
            }
        });
        initData();

    }
    public  void initData()
    {

        for (int i=0; i<15; i++) {
            mAdapter.add("item " + i);
        }
    }
}
