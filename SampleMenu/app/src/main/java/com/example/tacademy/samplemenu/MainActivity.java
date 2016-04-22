package com.example.tacademy.samplemenu;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.text_view));

        Button btn = (Button)findViewById(R.id.btn_action_mode); //액션 모드 메뉴
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSupportActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) { //메뉴 생성하는 곳
                        getMenuInflater().inflate(R.menu.mode_menu,menu);

                        return true;//true 리턴해야함
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) { //메뉴 선택
                        switch (item.getItemId()){
                            case R.id.mode_menu_1:
                            case R.id.mode_menu_2:
                            case R.id.mode_menu_3:
                                Toast.makeText(MainActivity.this, "Mode menu click"+item.getTitle(), Toast.LENGTH_SHORT).show();

                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {

                    }
                });
            }
        });

        btn = (Button)findViewById(R.id.btn_popup);
        btn.setOnClickListener(new View.OnClickListener() { //팝업 메뉴
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v); //버튼 밑에 생성
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { //메뉴 선태
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(MainActivity.this, "Menu click: "+ item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show(); //팝업창 띄워라
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { //컨텍스트 메뉴
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.text_view:
                getMenuInflater().inflate(R.menu.context_text_menu,menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) { //컨텍스트 메뉴 선택
        switch (item.getItemId()){
            case R.id.context_menu :
                return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //옵션 메뉴

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
