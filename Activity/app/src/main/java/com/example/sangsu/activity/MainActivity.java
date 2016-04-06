package com.example.sangsu.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_MY_ACTIVITY = 0;

    Button btn;
    EditText editText;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btn1);
        editText = (EditText)findViewById(R.id.edit1);
        txt = (TextView)findViewById(R.id.resultView);
        btn.setOnClickListener(new View.OnClickListener() { //버튼 클릭시
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MyActivity.class); // 인텐트 생성
                String message = editText.getText().toString();// 입력한 메시지 값
                i.putExtra(MyActivity.PARAM_INPUT_MESSAGE, message); //새로운 액티비티에 전달
                startActivityForResult(i, REQUEST_MY_ACTIVITY); //실행
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //MyActivity에서 보낸 결과를 받을 떄
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MY_ACTIVITY && resultCode == Activity.RESULT_OK) { //OK면
            String result = data.getStringExtra(MyActivity.RESULT_MESSAGE); //메시지 받아온다
            txt.setText("result : " + result);
        }
    }


}
