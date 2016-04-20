package com.example.tacademy.samplecustomlist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-04-20.
 */
public class PersonAdapter extends BaseAdapter {
    List<Person> items = new ArrayList<Person>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //ScrppedView 가 넘어옴

        PersonView view; // 부모뷰로부터 context 얻어옴
        if(convertView == null) //convertView가 없으면 생성
        {
             view = new PersonView(parent.getContext());
        }else{ // 있으면 뷰 재사용
            view = (PersonView) convertView;
        }

        view.setPerson(items.get(position));
        return view;
    }

    public void add(Person p){
        items.add(p);
        notifyDataSetChanged();//등록된 리스트엑ㅔ 변경됬다고 알림
    }
    public void remove(Person p){
        items.remove(p);
        notifyDataSetChanged();
    }
    public void addAll(List<Person> p){
        this.addAll(p);
        notifyDataSetChanged();
    }
}
