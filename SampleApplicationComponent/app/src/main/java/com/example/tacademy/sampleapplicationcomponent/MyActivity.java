package com.example.tacademy.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    public static final String ERTRA_NAME ="name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button btn = (Button)findViewById(R.id.button2);
        Intent intent = getIntent();
        String value  = intent.getStringExtra(ERTRA_NAME);//MainActivity로 값 받음
        Toast.makeText(MyActivity.this, value, Toast.LENGTH_SHORT).show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("result","aaa"); //데이터 만들어서
                setResult(Activity.RESULT_OK,data); //MainActivity로 데이터 보냄
               finish();
            }
        });
    }
}
