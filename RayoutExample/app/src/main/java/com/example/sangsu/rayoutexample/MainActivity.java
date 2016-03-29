package com.example.sangsu.rayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int index=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //리니어 레이아웃
        //setContentView(R.layout.activity_main2); //릴레티브 레이아웃
       // setContentView(R.layout.activity_main3); //그리드 레이아웃
        setContentView(R.layout.activity_main4); //프레임 레이아웃

        Button btn = (Button)findViewById(R.id.btn_change);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView img1 = (ImageView)findViewById(R.id.img1);
                ImageView img2 = (ImageView)findViewById(R.id.img2);

                if(index==0)
                {
                    img1.setVisibility(View.VISIBLE);
                    img2.setVisibility(View.INVISIBLE);
                    index=1;
                }
                else{
                    img2.setVisibility(View.VISIBLE);
                    img1.setVisibility(View.INVISIBLE);
                    index =0;
                }

            }
        });
    }
}
