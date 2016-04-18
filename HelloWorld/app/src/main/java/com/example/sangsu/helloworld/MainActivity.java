
package com.example.sangsu.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView messageView;
    EditText inputView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageView = (TextView) findViewById(R.id.textView);
        inputView = (EditText) findViewById(R.id.edit_input);
        Button btn = (Button) findViewById(R.id.btn_send);


        btn.setOnClickListener(new View.OnClickListener() { //이벤트 처리 리스너
            @Override
            public void onClick(View v) {
                messageView.setText(inputView.getText().toString());
            }
        });

        Button btn1 = (Button)findViewById(R.id.btn_toast);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "시작 버튼이 눌렸어요.", Toast.LENGTH_SHORT).show();
            }
        });
        // 토스트 출력
        Button btn2 = (Button)findViewById(R.id.btn_google);

        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(myIntent);
            }
        });
        // 구글접속

        Button btn3 = (Button)findViewById(R.id.btn_tel);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-8908-3614"));
                startActivity(myIntent);
            }
        });
        // 전화걸기

        Button btn4 = (Button)findViewById(R.id.btn_start);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "다른화면으로 이동.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(myIntent);
            }
        });
        // 화면이동

    }
}