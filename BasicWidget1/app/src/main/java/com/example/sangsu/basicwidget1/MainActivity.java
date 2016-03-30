package com.example.sangsu.basicwidget1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.txt);

        text.setText("Android");

        Button btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "버튼이 눌렸어요", Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox ch1 = (CheckBox)findViewById(R.id.chb1);
        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // check상태로 변경되면 isChecked가 true, 그렇지 않으면 false
                Toast.makeText(MainActivity.this, "알림 : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        // checkbox의 check 상태를 가져온다.
        if (!ch1.isChecked()) {
            // checkbox의 check 상태를 설정한다.
            ch1.setChecked(true);
        }


        RadioGroup group = (RadioGroup)findViewById(R.id.sex);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int CheckedId) {
                //선택된 RadioButton의 id가 CheckedId로 넘어옴.
                if(CheckedId == R.id.men)
                {
                    Toast.makeText(MainActivity.this, "남 : ", Toast.LENGTH_SHORT).show();
                }
                else if(CheckedId == R.id.girl)
                {
                    Toast.makeText(MainActivity.this, "여 : ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void OnClick(View v)
    {
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setBackgroundColor(Color.BLUE);
        Toast.makeText(MainActivity.this, "버튼이 눌렸어요", Toast.LENGTH_SHORT).show();
    }






}
