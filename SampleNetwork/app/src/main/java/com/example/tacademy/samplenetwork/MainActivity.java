package com.example.tacademy.samplenetwork;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_message)
    TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.text_message);
       // ButterKnife.bind(this);
        Button btn = (Button)findViewById(R.id.btn_google);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyNetworkTask().execute("http://www.google.com"); //작업쓰레드로 구글 접속
            }
        });


    }

    @OnClick(R.id.btn_google)
    public void onGoogleClick(View view){
        new MyNetworkTask().execute("http://www.google.com");


    }
    class MyNetworkTask extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) { //백그라운드 실행
            String URLText = params[0]; //첫번째 인자로 url 얻어와서

            URL url = null;
            try {
                url = new URL(URLText); //URL 객체 생성

            HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //url에 접속하여

                int code = conn.getResponseCode(); //응답코드 얻어옴
                if(code>=200 && code <=300){ //성공 일 경우에만
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = br.readLine()) != null){
                        sb.append(line).append("\n\r");
                    }
                    return sb.toString();
                }

            }catch (MalformedURLException e) {
            e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            messageView.setText(s);

        }
    }
}
