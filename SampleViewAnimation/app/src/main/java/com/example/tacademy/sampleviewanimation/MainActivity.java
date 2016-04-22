package com.example.tacademy.sampleviewanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.translate); //translate 애니메이션 생성
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });

        btn = (Button)findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale); //scale 애니메이션 생성
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });
        btn = (Button)findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate); //rotate 애니메이션 생성
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });
        btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.alpha); //alpha 애니메이션 생성
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });
        btn = (Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.set); //set 애니메이션 생성
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });
        btn = (Button)findViewById(R.id.leftOut);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_out); //leftIOut 애니메이션 생성
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                            imageView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });

        btn = (Button)findViewById(R.id.leftIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.left_in); //leftIn 애니메이션 생성

                imageView.setVisibility(View.VISIBLE);
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });

        btn = (Button)findViewById(R.id.custom);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation anim = new CustomAnimation(); //custom 애니메이션 생성
                anim.setDuration(1000);
                imageView.startAnimation(anim); //이미지뷰에 적용
            }
        });
    }
}
