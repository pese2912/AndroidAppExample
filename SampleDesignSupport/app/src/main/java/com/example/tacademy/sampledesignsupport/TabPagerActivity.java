package com.example.tacademy.sampledesignsupport;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

public class TabPagerActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);
        tabs = (TabLayout)findViewById(R.id.tabs);
        pager =(ViewPager)findViewById(R.id.pager);

        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
        tabs.removeAllViews();

        for (int i = 0; i < 10; i++) {
            tabs.addTab(tabs.newTab().setText("ttt"+i));
        }
    }
}
