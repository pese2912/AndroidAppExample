package com.example.tacademy.sampleapplicationcomponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) { //문자 서비스 받을 경우
       // Toast.makeText(context,"SMS Receive",Toast.LENGTH_SHORT).show(); //toast 출력하면 종료됨
        context.startService(new Intent(context,MyService.class)); // SMS 받으면 서비스 실행

    }
}
