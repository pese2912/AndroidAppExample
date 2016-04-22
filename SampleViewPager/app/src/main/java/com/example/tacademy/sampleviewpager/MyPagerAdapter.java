package com.example.tacademy.sampleviewpager;

import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-04-22.
 */
public class MyPagerAdapter extends PagerAdapter {


    List<String> items = new ArrayList<String>();
    List<View> scrapped = new ArrayList<View>();
    @Override
    public int getCount() {

        return items.size();
    }

    public  void add(String item)
    {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public float getPageWidth(int position) {//한 화면에 보여지는 페이지 갯수

        return 0.5f; // 한 화면에 두개 페이지
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) { //뷰그룹 = 뷰페이지 뷰를 만들어서 추가
        //object = 뷰를 찾을 수 있는 키에 해당하는 데이터

        TextView view;
        if(scrapped.size()==0) {
            view = (TextView) LayoutInflater.from(container.getContext()).inflate(android.R.layout.simple_list_item_1, container, false);
        }
        else{
            view = (TextView)scrapped.remove(0);
        }
        //안쓰는 뷰가 있으면 재활용
        view.setGravity(Gravity.CENTER);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//positon에 대한 오브젝트가 들어갖고 제거
        View view= (View)object;
        container.removeView(view);
        scrapped.add(view);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }
}
