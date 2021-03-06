package com.example.tacademy.samplethread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progressBar;
    private static final int MESSAGE_PROGRESS = 1;
    private static final int MESSAGE_COMPLETE =2;

    private static final int MESSAGE_BACKKEY = 3;
    private static final int TIMEOUT_BACKKEY = 2000;

    Handler mHandler = new Handler(Looper.getMainLooper()){  //메인쓰레드에서 메시지를 받아서 ui 갱신
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){ //메시지 상태에 따라
                case MESSAGE_PROGRESS: // 진행중
                    int progress = msg.arg1; //인자 정의
                    textView.setText("progress : " + progress);
                    progressBar.setProgress(progress);

                    break;

                case MESSAGE_COMPLETE: // 꽉찼을경우
                    textView.setText("downLoad Complete");
                    progressBar.setProgress(100);
                    break;
                case MESSAGE_BACKKEY: // 백키받으면
                    isBackPressed = false; //다시 누르라고 false로 변함
                    break;
            }
        }
    } ;//메인쓰레드

    boolean isBackPressed =false;

    @Override
    public void onBackPressed() { //한번누르고 다시 한번누르면 종료, 2초후에는 리셋되므로
        if(!isBackPressed){ // 2초후에는 리셋되므로 다시 실행됨
            Toast.makeText(this,"one more back key", Toast.LENGTH_SHORT).show();
            isBackPressed= true;

            mHandler.sendEmptyMessageDelayed(MESSAGE_BACKKEY, TIMEOUT_BACKKEY); //2초후에 백키 메시지전달
        }else {
            mHandler.removeMessages(MESSAGE_BACKKEY); //true면 백키메시지 제거
            super.onBackPressed();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text_message);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        Button btn = (Button)findViewById(R.id.btn_send);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload(); //프로그레스 시작
            }
        });
    }

    class ProgressRunnable implements Runnable{ //클래스 따로 정의

        int progress;
        public ProgressRunnable(int progress){
            this.progress = progress;
        }
        @Override
        public void run() {
            textView.setText("progress : " + progress);
            progressBar.setProgress(progress);
            //코드 받아서 ui 갱신

        }
    }

    class CompleteRunnable implements Runnable{ //클래스 따로 정의

        @Override
        public void run() {
            textView.setText("downLoad Complete");
            progressBar.setProgress(100);
            //코드 받아서 ui 갱신
        }
    }

    class MyTask extends AsyncTask<String,Integer, Boolean> { // AsyncTask 사용
        @Override
        protected void onProgressUpdate(Integer... values) { //메인쓰레드 업데이트
            super.onProgressUpdate(values);
            int progress = values[0]; //배열로 받기 때문에
            textView.setText("progress : " + progress);
            progressBar.setProgress(progress);
        }

        @Override
        protected void onPreExecute() { //실행 끝난 후 메인쓰레드에서
            super.onPreExecute();
            textView.setText("downLoad Complete");
            progressBar.setProgress(100);

        }


        @Override
        protected Boolean doInBackground(String... params) { //갯수 상관없이 ,배열
            int progress = 0;
            while (progress <= 100) { // 루프
                publishProgress(progress); //onProgressUpdate 호출
                try {
                    Thread.sleep(100); //0.5초 마다
                } catch (Exception e) {
                }
                progress += 1; // 5씩 증가

            }
            return true;
        }
    }

    private void startDownload(){
        new MyTask().execute(); //AsyncTask 사용


        /* 핸들러 사용
        new Thread(new Runnable() { //워커 쓰레드 생성
            @Override
            public void run() {
                int progress=0;
                while (progress <= 100){ // 루프
                //    textView.setText("progress : " + progress);
                //    progressBar.setProgress(progress);
                    //워커쓰레드에선 ui 갱신못함

                //    Message msg = mHandler.obtainMessage(MESSAGE_PROGRESS,progress,0); //메시지 객체 생성
                //    mHandler.sendMessage(msg);//메시지 보냄

                    mHandler.post(new ProgressRunnable(progress)); //메시지가 아닌 실행할 코드를 Runnable에 담아서 보냄
                    try {
                        Thread.sleep(100); //0.5초 마다
                    }catch (Exception e){}
                    progress+=1; // 5씩 증가
                }

            //    textView.setText("downLoad Complete");
             //   progressBar.setProgress(100);
                //워커쓰레드에선 ui 갱신못함
             //   mHandler.sendEmptyMessage(MESSAGE_COMPLETE) 메시지 보냄 상태만


                mHandler.post(new CompleteRunnable()); //메시지가 아닌 실행할 코드를 Runnable에 담아서 보냄
            }
        }).start();
        */
    }

    //워커쓰레드에서 메시지를 생성해서  메인쓰레드로 보내 (sendMessage) 처리하는 경우(handleMessage)와
    //클래스를 정의하여 Runnable에 코드를 담아 보내는  post() 경우 두가지가 있다.
    // 쉬운 방법은 AsyncTask 사용
}
