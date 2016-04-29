package com.example.tacademy.samplemediaplayer;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    enum PlayState {//플레이어 상태
        IDLE,
        INITIALIZED,
        PREPARED,
        STARTED,
        PAUSED,
        STOPPED,
        ERROR,
        END
    }

    PlayState mState = PlayState.IDLE; //초기값

    SeekBar progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView = (SeekBar)findViewById(R.id.seek_progress);

//        mPlayer = new MediaPlayer();
//        mState = PlayState.IDLE;
//
//        AssetFileDescriptor afd = getResources().openRawResourceFd(R.raw.winter_blues);
//        try {
//            mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
//            mState = PlayState.INITIALIZED;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (mState == PlayState.INITIALIZED) {
//            try {
//                mPlayer.prepare();
//                mState = PlayState.PREPARED;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        mPlayer = MediaPlayer.create(this, R.raw.winter_blues);
        mState = PlayState.PREPARED; //준비상태

        progressView.setMax(mPlayer.getDuration());

        progressView.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = -1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    this.progress = progress;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                progress = -1;
                isSeeking = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mState == PlayState.STARTED && progress != -1) {
                    mPlayer.seekTo(progress);
                }
                isSeeking = false;
            }
        });

        Button btn = (Button)findViewById(R.id.btn_play);
        btn.setOnClickListener(new View.OnClickListener() { //재생
            @Override
            public void onClick(View v) {
                if (mState == PlayState.INITIALIZED || mState == PlayState.STOPPED) { //초기상태 또는 정지일경우
                    try {
                        mPlayer.prepare();
                        mState = PlayState.PREPARED; //준비상태
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (mState == PlayState.PREPARED || mState == PlayState.PAUSED) { //준비 또는 일시정지 경우
                    mPlayer.seekTo(progressView.getProgress());
                    mPlayer.start(); //재생
                    mState = PlayState.STARTED;
                    mHandler.post(progressRunnable);
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_pause);
        btn.setOnClickListener(new View.OnClickListener() { //일시정지
            @Override
            public void onClick(View v) {
                if (mState == PlayState.STARTED) { // 재생일 경우에만
                    mPlayer.pause();
                    mState = PlayState.PAUSED; // 일시정지
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_stop);
        btn.setOnClickListener(new View.OnClickListener() { // 정지
            @Override
            public void onClick(View v) {
                if (mState == PlayState.STARTED || mState == PlayState.PAUSED) { // 재생중이거나 일시정지일 경우
                    mPlayer.stop();
                    mState = PlayState.STOPPED; // 정지
                }
            }
        });
    }

    boolean isSeeking = false;

    Handler mHandler = new Handler();

    Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (mState == PlayState.STARTED) {
                if (!isSeeking) {
                    int position = mPlayer.getCurrentPosition();
                    progressView.setProgress(position);
                }
                mHandler.postDelayed(this, 100);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.release();
        mState = PlayState.END;
        mPlayer = null;
    }
}