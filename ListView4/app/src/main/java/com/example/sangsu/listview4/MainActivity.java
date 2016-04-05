package com.example.sangsu.listview4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ExpandableListView listView;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ExpandableListView)findViewById(R.id.expandableListView);
        TextView headerView = new TextView(this);
        headerView.setText("Header View");
        listView.addHeaderView(headerView, null, false);
        mAdapter = new MyAdapter(this);
        listView.setAdapter(mAdapter);

        initData();
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                ChildItemData data = (ChildItemData) mAdapter.getChild(groupPosition,
                        childPosition);
                Toast.makeText(MainActivity.this, "GroupPosition : " + groupPosition + ",child : " +
                        data.title, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                GroupItemData group =
                        (GroupItemData) mAdapter.getGroup(groupPosition);
                return false;
            }
        });
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //listView.expandGroup(groupPosition); 한번 열면 다시 못 올라가게
            }
        });

    }
    public void initData()
    {
        for(int i=1; i<5; i++) {
            GroupItemData group = new GroupItemData();
            group.groupTitle = "group"+i;
            group.children = new ArrayList<ChildItemData>();
            ChildItemData c1 = new ChildItemData();
            c1.title = "child1";
            ChildItemData c2 = new ChildItemData();
            c2.title = "child2";
            ChildItemData c3 = new ChildItemData();
            c3.title = "child3";

            mAdapter.add(group.groupTitle, c1);
            mAdapter.add(group.groupTitle, c2);
            mAdapter.add(group.groupTitle, c3);
        }
    }
}
