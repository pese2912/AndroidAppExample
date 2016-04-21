package com.example.tacademy.sampleapplicationcomponent;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int RC_MY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                intent.putExtra(MyActivity.ERTRA_NAME, "value"); //MyActivity로 값 전달
                startActivityForResult(intent, RC_MY);
            }
        });

        btn = (Button) findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class); //서비스 시작
                startService(intent);

            }
        });

        btn = (Button) findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent); // 서비스 중단

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_MY) { // 내가 보낸 놈인지 확인하기 위해
            if (resultCode == Activity.RESULT_OK) { // 보낸곳에서 성공적으로 보냈는지
                String result = data.getStringExtra("result"); //MyActivity로에서 데이터 받기
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
