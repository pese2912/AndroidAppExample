package com.example.tacademy.samplemultiplelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText inputView;
    ListView listView;
    ArrayAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputView = (EditText)findViewById(R.id.btn_edit);
        listView = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice); //멀티모드


        listView.setAdapter(mAdapter);
        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE); //멀티모드

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener(){ // 입력한 아이템 추가

            @Override
            public void onClick(View v) {
                if(!inputView.getText().toString().isEmpty())
                    mAdapter.add(inputView.getText().toString());
                listView.smoothScrollToPosition(mAdapter.getCount()-1);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){ //리스트 뷰 아이템 클릭시

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String)listView.getItemAtPosition(position); // mAdapter.getItem(position)은 사용금지 위치가 안맞아
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });


        Button btn_del = (Button)findViewById(R.id.btn_del);

        btn_del.setOnClickListener(new View.OnClickListener(){ //선택한 리스트 삭제

            @Override
            public void onClick(View v) {
                if(listView.CHOICE_MODE_SINGLE == listView.getChoiceMode()) //싱글 모드
                {

                    int position = listView.getCheckedItemPosition();
                    if(position < mAdapter.getCount()) {

                        String item = (String) listView.getItemAtPosition(position);

                        mAdapter.remove(item);
                        listView.smoothScrollToPosition(0);
                    }
                }
                else if(listView.CHOICE_MODE_MULTIPLE == listView.getChoiceMode()) //멀티 모드
                {
                    SparseBooleanArray array = listView.getCheckedItemPositions();
                    ArrayList<String> select = new ArrayList<String>();

                    for(int i = 0; i<array.size(); i++)
                    {
                        int position = array.keyAt(i);
                        if(array.get(position)){
                            select.add( (String) listView.getItemAtPosition(position));
                        }
                    }
                    for(String s : select){
                        mAdapter.remove(s);

                    }

                    listView.clearChoices();
                    listView.smoothScrollToPosition(0);
                }


            }
        });

        initData();
    }

    public void initData() // 데이터 초기화
    {
        String[] array  = getResources().getStringArray(R.array.list_item);
        // mAdapter.addAll(array); api 11이상
        for(String s: array)
        {
            mAdapter.add(s);
        }
    }

}