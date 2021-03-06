package com.example.tacademy.sampleapplicationcomponent;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {

    }
    boolean isRunning = false;

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    int count = 0;

    private  static final  String TAG="count";
    @Override
    public void onCreate() { //서비스 객체 생성 시
        super.onCreate();
        Toast.makeText(this, "onCreate..", Toast.LENGTH_SHORT).show();

        isRunning = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning)
                {
                    count++;
                    Log.i(TAG, "count : " + count); //서비스 시작시 쓰레드 돌아감
                }
            }
        }).start();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver,filter); //리시버를 등록

    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() { //이벤트 를  받아 처리할 리시버
        @Override
        public void onReceive(Context context, Intent intent) { //등록한 서비스가 들어옴

                if(Intent.ACTION_SCREEN_ON.equals(intent.getAction()))
                {
                    Toast.makeText(context, "ACTION_SCREEN_ON..", Toast.LENGTH_SHORT).show();

                }else if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction()))
                {
                    Log.i(TAG,"ACTION_SCREEN_OFF");

                }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) { //  start서비스 호출 시
        Toast.makeText(this, "onStartCommand..", Toast.LENGTH_SHORT).show();
        return Service.START_NOT_STICKY; // 죽으면 다시 실행하지 않음

    }

    @Override
    public void onDestroy() { //서비스 멈춰도 쓰레드는 계속 돌아감
        super.onDestroy();
        Toast.makeText(this, "onDestroy..", Toast.LENGTH_SHORT).show();
        isRunning =false;//쓰레드 멈추기 위해서
        unregisterReceiver(mReceiver); // 리시버 해제
    }

}
