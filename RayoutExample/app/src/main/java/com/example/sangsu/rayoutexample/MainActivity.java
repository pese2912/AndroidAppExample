package com.example.sangsu.rayoutexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //리니어 레이아웃
        setContentView(R.layout.activity_main); //릴레티브 레이아웃
    }
}
