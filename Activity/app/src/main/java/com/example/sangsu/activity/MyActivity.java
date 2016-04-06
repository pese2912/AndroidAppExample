package com.example.sangsu.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends AppCompatActivity {
    public static final String PARAM_INPUT_MESSAGE = "input_message";
    public static final String RESULT_MESSAGE = "result_message";
    EditText editText;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

         btn = (Button)findViewById(R.id.btn2);
         txt = (TextView)findViewById(R.id.message);
        editText = (EditText)findViewById(R.id.edit2);
        Intent intent = getIntent(); //호출한 인탠트 얻어옴
        String message = intent.getStringExtra(PARAM_INPUT_MESSAGE); //보낸 메시지 얻어옴
        txt.setText(message); // 텍스트뷰에 설정

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(RESULT_MESSAGE, editText.getText().toString());// 새로운 인텐트 생성해서 결과 값을 보낸다.
                setResult(Activity.RESULT_OK, data); //값 설정
                finish(); //버튼클릭시 되돌아감
            }
        });
    }
}
