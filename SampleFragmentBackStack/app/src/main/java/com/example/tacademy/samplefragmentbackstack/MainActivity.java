package com.example.tacademy.samplefragmentbackstack;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int count= 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(savedInstanceState ==null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, CountFragment.newInstance("base")) //처음 정상 구동 시 컨테이너에 base값 전달
                    .commit();
        }

        Button btn = (Button)findViewById(R.id.btn_prev); //이전 버튼
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack(); //backStack에서 pop 연산
            }
        });

        btn = (Button)findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener() { // 다음 버튼
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, CountFragment.newInstance("count" + count))// 이전 프레그먼트 제거하고 뷰 배치 count 값 전달
                        .addToBackStack("entry" +count) //이름 지정해주고 백스택에 저장
                        .commit();

                count++; //증가
            }
        });

        btn = (Button)findViewById(R.id.btn_allpop);
        btn.setOnClickListener(new View.OnClickListener() { //모두 팝
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);// 모두 pop 해라

            }
        });
    }
}
