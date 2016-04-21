package com.example.tacademy.samplefragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OtherActivity extends AppCompatActivity implements SecondFragment.OnMessageCallBack{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        SecondFragment f = new SecondFragment(); //두번째 프레그먼트
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();//트랜잭션(프레그먼트의 연산의 집합을 나타내는 클래스)  생성
        ft.replace(R.id.container,f);// 컨테이너안에 뷰를 제거하고 프레그먼트 배치
        ft.commit();// 갱신
    }

    public void receiveText(String text){ // 프레그먼트로부터 값 못 받음 -> 형변환 안되서
        Toast.makeText(this, "text : " +text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void receiveMessage(String message) { // 프레그먼트로부터 값 받음 -> 인터페이스 상속해서
        Toast.makeText(this, "CallBack : " +message, Toast.LENGTH_SHORT).show();
    }
}
