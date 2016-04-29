package com.example.tacademy.samplemediaplayer;


import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    enum PlayState{
        IDLE,
        INITIALIZED,
        PREPARED,
        STARTED,
        PAUSED,
        STOPPED,
        ERROR,
        END
    }
    PlayState mState = PlayState.IDLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayer = new MediaPlayer();
        mState = PlayState.IDLE;

        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.winter_blues);
        try {
            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mState = PlayState.INITIALIZED;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(mState == PlayState.INITIALIZED){
            try {
                mPlayer.prepare();
                mState = PlayState.PREPARED;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        Button btn = (Button)findViewById(R.id.btn_play);
        btn.setOnClickListener(new View.OnClickListener() { //음악 재생
            @Override
            public void onClick(View v) {
                if(mState == PlayState.INITIALIZED || mState == PlayState.STOPPED){ //초기화이거나 스탑상태일경우
                    try {
                        mPlayer.prepare(); //준비
                        mState = PlayState.PREPARED;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                if(mState == PlayState.PREPARED || mState == PlayState.PAUSED){ //준비 거나 잠시 멈춤
                    mPlayer.start();
                    mState = PlayState.STARTED; //재생
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_pause);
        btn.setOnClickListener(new View.OnClickListener() { //일시정지
            @Override
            public void onClick(View v) {
                if(mState == PlayState.STARTED ){ //재생일경우에만
                    mPlayer.pause();
                    mState = PlayState.PAUSED;
                }
            }
        });


        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() { // 정지
            @Override
            public void onClick(View v) {
               if(mState == PlayState.STARTED || mState == PlayState.PAUSED){ //재생이거나 일시정지일경우에만
                   mPlayer.stop();
                   mState = PlayState.STOPPED;
               }
            }
        });

        //두번째 방법
       // mPlayer = MediaPlayer.create(this,R.raw.winter_blues);
       // mState = PlayState.PREPARED;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
        mState = PlayState.END;
        mPlayer = null;
    }
}
