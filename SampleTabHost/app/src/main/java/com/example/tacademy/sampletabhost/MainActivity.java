package com.example.tacademy.sampletabhost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost)findViewById(R.id.tabHost);

        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator("TAB1");
        spec.setContent(R.id.tab1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator("TAB2");
        spec.setContent(R.id.tab2);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab3");
        spec.setIndicator("TAB3");
        spec.setContent(R.id.tab3);
        tabHost.addTab(spec);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(MainActivity.this, "tabId: " + tabId ,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
