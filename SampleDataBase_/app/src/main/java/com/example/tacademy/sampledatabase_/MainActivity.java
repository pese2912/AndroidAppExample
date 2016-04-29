package com.example.tacademy.sampledatabase_;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText keywordView;
//    ArrayAdapter<Person> mAdapter;

    SimpleCursorAdapter mAdapter;

    String mKeyword = null;

    int columnIndexAddress = -1;
    int columnIndexPhone = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listView);
        keywordView = (EditText)findViewById(R.id.editText);
       // mAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_list_item_1);
        String[] from = {DataConstant.PersonTable.COLUMN_NAME,
                DataConstant.PersonTable.COLUMN_PHONE,
                DataConstant.PersonTable.COLUMN_ADDRESS};
        int[] to = {R.id.text_name,
                R.id.text_phone,
                R.id.text_address};

        mAdapter = new SimpleCursorAdapter(this, R.layout.view_item, null, from, to, 0);
        mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == columnIndexAddress) {
                    TextView tv = (TextView)view;
                    String address = cursor.getString(columnIndex);
                    String office = cursor.getString(cursor.getColumnIndex(DataConstant.PersonTable.COLUMN_OFFICE));
                    tv.setText(address+"("+office+")");
                    return true;
                } else if (columnIndex == columnIndexPhone) {
                    TextView tv = (TextView)view;
                    String phone = cursor.getString(columnIndex);
                    StringBuilder sb = new StringBuilder();
                    if (phone.length() > 0) {
                        sb.append(phone.charAt(0));
                    }
                    for (int i = 1; i < phone.length();i++) {
                        sb.append("*");
                    }
                    tv.setText(sb.toString());
                    return true;
                }

                return false;
            }
        });
        listView.setAdapter(mAdapter);

        Button btn = (Button)findViewById(R.id.btn_search);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyword = keywordView.getText().toString();
                updateList();
            }
        });

        btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataAddActivity.class));
            }
        });
        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList() {
//        List<Person> list = DataManager.getInstance().search(mKeyword);
//        mAdapter.clear();
//        for (Person p : list) {
//            mAdapter.add(p);
//        }
        Cursor c = DataManager.getInstance().selectCursor(mKeyword);
        columnIndexAddress = c.getColumnIndex(DataConstant.PersonTable.COLUMN_ADDRESS);
        columnIndexPhone = c.getColumnIndex(DataConstant.PersonTable.COLUMN_PHONE);
        mAdapter.changeCursor(c);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.changeCursor(null);
    }
}