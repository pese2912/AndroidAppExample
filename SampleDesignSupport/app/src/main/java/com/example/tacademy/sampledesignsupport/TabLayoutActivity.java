package com.example.tacademy.sampledesignsupport;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

public class TabLayoutActivity extends AppCompatActivity {

    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tabs = (TabLayout)findViewById(R.id.tablayout);
        for (int i = 0; i < 10; i++) {
            tabs.addTab(tabs.newTab().setTag("tab"+i).setText("TAB"+i));
        }

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, ChildFragment.newInstance(tab.getTag().toString()))
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}