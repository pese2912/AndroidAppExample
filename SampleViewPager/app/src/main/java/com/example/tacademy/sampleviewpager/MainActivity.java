package com.example.tacademy.sampleviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    MyPagerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new MyPagerAdapter();
        pager.setAdapter(mAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { //페이지 변경됬을 시
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) { //선택한 페이지
                Toast.makeText(MainActivity.this, "position id:" +position, Toast.LENGTH_SHORT).show();
                //맨 처음 페이지는 별도로 처리해야함

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        initData();
    }
    private void initData()
    {
        for(int i=0; i<10; i++)
        {
            mAdapter.add("item " +i);
        }
    }
}
