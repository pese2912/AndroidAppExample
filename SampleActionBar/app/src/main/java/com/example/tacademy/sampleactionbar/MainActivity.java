package com.example.tacademy.sampleactionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar)); //툴바를 액션바로 정의
       getSupportActionBar().setDisplayHomeAsUpEnabled(true); //액션바
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher); //홈as업 아이콘
       // getSupportActionBar().setDisplayShowCustomEnabled(true); //커스텀 가능
      //  getSupportActionBar().setCustomView(view, new ActionBar.LayoutParams(Gravity.CENTER)); //뷰를 가운데 정렬 시키는법

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//홈as업 아이콘 클릭시
        switch (item.getItemId()){
            case android.R.id.home:
                Toast.makeText(MainActivity.this, "HomeAsUp Click",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
