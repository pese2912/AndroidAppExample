package com.example.tacademy.samplefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1= (Button)findViewById(R.id.btn_first);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirstFragment f = new FirstFragment(); //첫번째 프레그먼트
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction(); //트랜잭션(프레그먼트의 연산의 집합을 나타내는 클래스) 생성
                ft.replace(R.id.container,f); // 컨테이너안에 뷰를 제거하고 프레그먼트 배치
                ft.commit(); // 갱신
            }
        });
        Button btn2 = (Button)findViewById(R.id.btn_second);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SecondFragment f = new SecondFragment(); //두번째 프레그먼트
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();//트랜잭션(프레그먼트의 연산의 집합을 나타내는 클래스)  생성
                ft.replace(R.id.container,f);// 컨테이너안에 뷰를 제거하고 프레그먼트 배치
                ft.commit();// 갱신
            }
        });

        FirstFragment f = new FirstFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,f);
        ft.commit();

    }
}
