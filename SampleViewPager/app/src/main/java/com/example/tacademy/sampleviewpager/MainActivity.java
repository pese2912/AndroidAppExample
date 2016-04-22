package com.example.tacademy.sampleviewpager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    //    MyPagerAdapter mAdapter;
    MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager)findViewById(R.id.pager);
//        mAdapter = new MyPagerAdapter();
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        pager.setAdapter(mAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainActivity.this, "positin : " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mAdapter.add("item " + i);
        }
    }
}