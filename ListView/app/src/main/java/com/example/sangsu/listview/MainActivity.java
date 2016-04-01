package com.example.sangsu.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> mAdapter;
    TextView messageView;
    EditText inputView;
    Button btn;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView1);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,
                new ArrayList<String>());
        //어댑터 생성

        messageView = (TextView)findViewById(R.id.text_message);
        inputView = (EditText)findViewById(R.id.edit_input);
        btn = (Button)findViewById(R.id.btn_add);
        btn2  = (Button)findViewById(R.id.btn_del);

        listView.setAdapter(mAdapter); //리스트뷰가 어댑터설정
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE); //싱글초이스 모드
        initData(); //값 초기화

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//리스트뷰 아이템 클릭시
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (String) listView.getItemAtPosition(position); //선택된 포지션넘어와서 값 얻어옴
                messageView.setText(text);

            }
        });

        btn.setOnClickListener(new View.OnClickListener() { //add 버튼 클릭시
            public void onClick(View v) {
                String text = inputView.getText().toString();
                if (text != null && !text.equals("")) {
                    mAdapter.add(text);//선택된 아이템 추가
                    inputView.setText("");
                    listView.smoothScrollToPosition(mAdapter.getCount() - 1);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){ //del 버튼 클릭시
            public void onClick(View v) {

                if (listView.getChoiceMode() == ListView.CHOICE_MODE_SINGLE) {
                    int position = listView.getCheckedItemPosition();
                    String item = (String)listView.getItemAtPosition(position);
                    mAdapter.remove(item); // 선택된 아이템 삭제
                }
            }
        });
    }
    public void initData()
    {
        for(int i=0; i<=10; i++)
            mAdapter.add("Item "+i);
    }
}
