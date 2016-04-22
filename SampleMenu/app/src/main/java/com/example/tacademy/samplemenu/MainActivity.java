package com.example.tacademy.samplemenu;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //옵션 메뉴 실행

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.menu_1);
        View view = MenuItemCompat.getActionView(item);
        EditText editText = (EditText)view.findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Toast.makeText(MainActivity.this, "Text : "+ s.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return true; //메뉴 생성 성공
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //옵션메뉴 아이템 선택시

        switch (item.getItemId()){
            case R.id.menu_1:

            case R.id.menu_2:

            case R.id.menu_3:

            Toast.makeText(MainActivity.this,"menu : "+item.getTitle(),Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
