package com.example.tacademy.samplefragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SecondFragment.OnMessageCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1= (Button)findViewById(R.id.btn_first);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //FirstFragment f = new FirstFragment(); //첫번째 프레그먼트
                FirstFragment f = FirstFragment.newInstance("change");  //액티비티에서 프레그먼트로 값전달
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

        FirstFragment f = FirstFragment.newInstance("first"); //액티비티에서 프레그먼트로 값전달
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container,f);
        ft.commit();

        Button btn3 = (Button)findViewById(R.id.btn_other);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                startActivity(intent);
            }
        });
    }

    public void receiveText(String text){ // 프레그먼트로부터 값 받음 - . 확장성 떨어짐
        Toast.makeText(this, "text : " +text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void receiveMessage(String message) { //프레그먼트로부터 값 받음 -> 인터페이스 상속하여 메소드 오버라이딩
        Toast.makeText(this, "callBack : " +message, Toast.LENGTH_SHORT).show();

    }
}
