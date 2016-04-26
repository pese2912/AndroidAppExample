package com.example.tacademy.samplewebview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    WebView webView;
    EditText urlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        urlView = (EditText)findViewById(R.id.edit_url);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){ //반드시 설정

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) { // url인 페이지에 접속할것인지 물어봄
                if(url.startsWith("market://")) { //웹뷰가 처리못함
                    Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                    return  true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient());
    //    webView.addJavascriptInterface(new MyObject(), "myobject");
        webView.loadUrl("http://www.google.com"); //구글 접속

        Button btn = (Button)findViewById(R.id.btn_go); //작성한 url로 접속 할 수있으면 접속
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlView.getText().toString();
                if(!TextUtils.isEmpty(url)){
                    if(!url.startsWith("http://") && !url.startsWith("https://")){
                        url = "http://"+url;
                    }
                    webView.loadUrl(url);
                }
            }
        });


        btn = (Button)findViewById(R.id.btn_prev); //이전페이지
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoBack()){
                    webView.goBack();
                }
            }
        });

        btn = (Button)findViewById(R.id.btn_next); //다음 페이지
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(webView.canGoForward()){
                    webView.goForward();

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.resumeTimers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.pauseTimers();
    }

    class MyObject {
        int count;
        public int getCount(){
            return  count;
        }
        public void setCount(int cnt) {
         this.count = cnt;
        }
    }
}
