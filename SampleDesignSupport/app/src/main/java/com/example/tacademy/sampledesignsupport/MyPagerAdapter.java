package com.example.tacademy.sampledesignsupport;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Tacademy on 2016-04-26.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ChildFragment.newInstance("tabpager : "+ position);
    }

    @Override
    public int getCount() {
        return 10;
    }

    public CharSequence getPageTitle(int position) {
        return "TAB"+position;
    }
}
