package com.example.sangsu.thread;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView messageView;
    public static final  int MESSAGE_PROGRESS=0;
    public static final int MESSAGE_DONE =1;
    Handler mHandler = new Handler() {

        public void handleMessage(Message msg){
            switch (msg.what){
                case MESSAGE_PROGRESS:
                    int progress = msg.arg1;
                    progressBar.setProgress(progress);
                    messageView.setText("progress :" + progress);
                    break;
                case MESSAGE_DONE:
                    progressBar.setProgress(100);
                    messageView.setText("progress done");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btnStart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int count =0;
                        while (count <20)
                        {
                            Message msg = mHandler.obtainMessage(MESSAGE_PROGRESS, count*5,0);
                            mHandler.sendMessage(msg);
                            count++;
                            try{
                                Thread.sleep(1000);

                            }catch (InterruptedException e){

                            }
                        }
                        Message msg = mHandler.obtainMessage(MESSAGE_DONE);
                        mHandler.sendMessage(msg);
                    }
                }).start();

            }
        });
    }
}
